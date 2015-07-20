package pep_096

import scala.annotation.tailrec
import scala.collection.immutable._
import scalaz._, Scalaz._

object Solution {
  val stream = getClass.getResourceAsStream("/pep_096/sudoku.txt")
  val lines = scala.io.Source.fromInputStream(stream).getLines()
  val boards = lines.grouped(10).map(_.tail.flatten.map(_ - '0').toIndexedSeq)

  def solve(): Int = {
    boards.map(Sudoku.solve).map(_.take(3).reduce(_ * 10 + _)).sum
  }
}

object Sudoku {
  val n = 9
  val s = Math.sqrt(n).toInt
  type Val = Int
  type Board = IndexedSeq[Val]
  type Pos = Int
  type Groups = IndexedSeq[Set[Val]]
  type Rows = IndexedSeq[Set[Val]]
  type Cols = IndexedSeq[Set[Val]]
  val unassignedVal = 0

  def show(board: Board): String =
    board.map(v => if (v == unassignedVal) 'ⓧ' else (v + '\u245f').toChar)
      .grouped(s).map(_.mkString(" "))
      .grouped(s).map(_.mkString("|"))
      .mkString("\n").lines
      .grouped(s).map(_.mkString("\n"))
      .mkString("\n-----------------------\n")

  private def candidates(board: Board, pos: Pos): Set[Val] = {

    // TODO find Pos programmatically
    def group(g: Groups, p: Pos): Set[Val] = p match {
      case 0 | 1 | 2 | 9 | 10 | 11 | 18 | 19 | 20 => g(0)
      case 3 | 4 | 5 | 12 | 13 | 14 | 21 | 22 | 23 => g(1)
      case 6 | 7 | 8 | 15 | 16 | 17 | 24 | 25 | 26 => g(2)
      case 27 | 28 | 29 | 36 | 37 | 38 | 45 | 46 | 47 => g(3)
      case 30 | 31 | 32 | 39 | 40 | 41 | 48 | 49 | 50 => g(4)
      case 33 | 34 | 35 | 42 | 43 | 44 | 51 | 52 | 53 => g(5)
      case 54 | 55 | 56 | 63 | 64 | 65 | 72 | 73 | 74 => g(6)
      case 57 | 58 | 59 | 66 | 67 | 68 | 75 | 76 | 77 => g(7)
      case 60 | 61 | 62 | 69 | 70 | 71 | 78 | 79 | 80 => g(8)
    }

    // TODO find Pos programmatically (zip? zipWith? outer / 3 then % ?)
    // @ def aaa(x: Int) = (x / s) * (n * s) + (x % s) * s
    // @ (0 |-> n - 1).map(aaa)
    // res38: List[Int] = List(0, 3, 6, 27, 30, 33, 54, 57, 60) // <-- first column

    def groups(b: Board): Groups = Vector(
      Set(b(0),  b(1),  b(2),  b(9),  b(10), b(11), b(18), b(19), b(20)) - unassignedVal,
      Set(b(3),  b(4),  b(5),  b(12), b(13), b(14), b(21), b(22), b(23)) - unassignedVal,
      Set(b(6),  b(7),  b(8),  b(15), b(16), b(17), b(24), b(25), b(26)) - unassignedVal,
      Set(b(27), b(28), b(29), b(36), b(37), b(38), b(45), b(46), b(47)) - unassignedVal,
      Set(b(30), b(31), b(32), b(39), b(40), b(41), b(48), b(49), b(50)) - unassignedVal,
      Set(b(33), b(34), b(35), b(42), b(43), b(44), b(51), b(52), b(53)) - unassignedVal,
      Set(b(54), b(55), b(56), b(63), b(64), b(65), b(72), b(73), b(74)) - unassignedVal,
      Set(b(57), b(58), b(59), b(66), b(67), b(68), b(75), b(76), b(77)) - unassignedVal,
      Set(b(60), b(61), b(62), b(69), b(70), b(71), b(78), b(79), b(80)) - unassignedVal
    )

    def rows(b: Board): Rows =
      (0 |-> n - 1).map(o => (0 |-> n - 1).map(_ + n * o).map(b).toSet - unassignedVal).toVector

    def cols(b: Board): Cols =
      (0 |-> n - 1).map(o => (0 |-> n - 1).map(_ * n + o).map(b).toSet - unassignedVal).toVector

    // TODO merge groups+group <-- find how pos / % to get to the right group!

    val g = groups(board) |> (group(_, pos))
    val r = rows(board) |> (_(pos / n))
    val c = cols(board) |> (_(pos % n))

    (1 |-> n).toSet &~ g &~ r &~ c
  }

  private def unassignedPos(board: Board): List[Pos] = board.zipWithIndex.filter(_._1 == unassignedVal).map(_._2).toList

  @tailrec
  private def solveInduction(board: Board): Board = {
    val ups = unassignedPos(board)
    ups.map(p => (p, candidates(board, p))).find(_._2.size == 1).map(x => (x._1, x._2.head)) match {
      case Some((p, v)) => solveInduction(board.updated(p, v))
      case None         => board
    }
  }

  def select(board: Board): List[(Board, Val)] = {
    val pos = unassignedPos(board).head
    val vals = candidates(board, pos).toList
    vals.map(v => (board.updated(pos, v), v))
  }

  def solve(board: Board): Board = {
    val inductionSolution = solveInduction(board)
    StateT(select).replicateM(unassignedPos(inductionSolution).length).exec(inductionSolution).head
  }
}
