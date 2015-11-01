package common

import spire.implicits._
import scalaz.syntax.id._

object Fibonacci {
  val tenDigit = ("1" + "0" * 10).toLong
  lazy val mod10Stream: Stream[Long] =
    0 #:: 1 #:: (mod10Stream zip mod10Stream.tail map { case (n0, n1) => (n0 + n1) % tenDigit })


  // https://en.wikipedia.org/wiki/Fibonacci_number#Magnitude
  val φLog10 = ((1D + 5D.sqrt()) / 2D).log(10)

  def FnDigits(n: Int) = n * φLog10

  // https://en.wikipedia.org/wiki/Fibonacci_number#Closed-form_expression
  // F(n) ~ φPow(n) / Sq5
  // F(n).log ~ φPow(n).log - Sq5.log
  // F(n).log ~ n * φ.log - Sq5.log
  // F(n).log ~ F(n)#digits - Sq5.log
  val Sq5Log10 = 5D.sqrt().log(10)

  def FnLog10(n: Int): Double = FnDigits(n) - Sq5Log10

  //  scala> approximateF(10)
  //  res9: Double = 55.003636123247446
  //
  //  scala> approximateF(10000)
  //  res10: Double = Infinity
  def approximateF(n: Int) = n |> FnLog10 |> 10D.pow

  // https://en.wikipedia.org/wiki/Significant_figures
  def FUpper9Digit(n: Int): String = {
    val fn10 = FnLog10(n)
    val res = 10D.pow(fn10 - fn10.floor) * tenDigit
    res.toLong.toString.take(9)
  }

}
