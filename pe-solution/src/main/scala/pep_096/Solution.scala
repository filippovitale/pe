package pep_096

import scala.collection.immutable._

object Solution extends App {

  import Sudoku._

  val stream = getClass.getResourceAsStream("/pep_096/sudoku.txt")
  val lines = scala.io.Source.fromInputStream(stream).getLines()
  val boards: Iterator[Board] = lines.grouped(10).map(_.tail.flatten.map(_ - '0').toIndexedSeq)

  println(show(boards.next()))
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
}
