package pep_104

import common.Fibonacci._
import scalaz.syntax.id._

object Solution {

  def isPanadigital(s: String): Boolean = s.sorted == "123456789"

  def isPanadigital(n: Long): Boolean = n.toString |> isPanadigital

  def solve() = mod10Stream.zipWithIndex
    .filter(_._1 |> isPanadigital).map(_._2)
    .find(_ |> FUpper9Digit |> isPanadigital)
    .mkString("\n")

}
