package pep_084

import scala.util.Random
import scalaz._, Scalaz._

object Solution {

  val DICE_SIZE = 4
  val DOUBLE = DICE_SIZE * 2

  type Pos = String

  val board: Vector[Pos] = Vector(
    "GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3", "JAIL",
    "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3", "FP",
    "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3", "G2J",
    "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2")

  val posStream = Stream.continually(board).flatten

  val cc: Vector[Option[(Pos) => Pos]] = Vector(
    (_: Pos) => "GO", (_: Pos) => "JAIL"
  ).map(_.some) ++ Vector.fill(14)(none[Pos => Pos])

  val ch: Vector[Option[(Pos) => Pos]] = Vector(
    (_: Pos) => "GO", (_: Pos) => "JAIL", (_: Pos) => "C1",
    (_: Pos) => "E3", (_: Pos) => "H2", (_: Pos) => "R1",
    (p: Pos) => posStream.dropWhile(_ != p).dropWhile(!_.startsWith("R")).head,
    (p: Pos) => posStream.dropWhile(_ != p).dropWhile(!_.startsWith("R")).head,
    (p: Pos) => posStream.dropWhile(_ != p).dropWhile(!_.startsWith("U")).head,
    (p: Pos) => posStream.dropWhile(_ != p).drop(board.size - 3).head
  ).map(_.some) ++ Vector.fill(6)(none[Pos => Pos])

  // this is random, not "back to the bottom"
  def nextPos(c: Vector[Option[(Pos) => Pos]], p: Pos): Pos =
    Random.nextInt(c.size) |> c some (_(p)) none p

  def nextPos(roll: Int, p: Pos): Pos = {
    val next = posStream.dropWhile(_ != p).drop(roll).head
    if (next.startsWith("CC")) nextPos(cc, next)
    else if (next.startsWith("CH")) nextPos(ch, next)
    else next
  }

  def roll(): Int = Random.nextInt(DICE_SIZE) + Random.nextInt(DICE_SIZE) + 2

  val stats: Map[Pos, Int] = board.map(p => p -> 0).toMap

  def incStat(s: Map[Pos, Int], p: Pos): Map[Pos, Int] = s + (p -> (s(p) + 1))

  def collectStats(sampleSize: Int) = (("GO", 0, stats) /: Stream.continually(roll()).take(sampleSize)) {
    case ((p, 2, s), DOUBLE) => ("JAIL", 0, incStat(s, p))
    case ((p, d, s), DOUBLE) => (nextPos(DOUBLE, p), d + 1, incStat(s, p))
    case ((p, _, s), r)      => (nextPos(r, p), 0, incStat(s, p))
  }._3

  def solve(): String =
    collectStats(1000000).toList.sortBy(_._2).reverse
      .map(_._1).take(3).map(board.indexOf(_)).mkString
}
