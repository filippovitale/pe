package pep_080

import java.math.{RoundingMode, MathContext}

import spire.implicits._

object Solution {

  val ctx = new MathContext(100, RoundingMode.DOWN)

  def decimalDigits(i: Int): List[Int] = {
    val sqrt = BigDecimal(i, ctx).sqrt()
    if (sqrt.isWhole()) List.empty
    else sqrt.toString().toList.filter(_ > '.').map(_ - '0')
  }

  def solve(): String = (1 until 100).flatMap(decimalDigits).sum.toString
}
