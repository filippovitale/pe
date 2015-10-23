package common

import scala.math.pow

// Implemented for pep_051
object Prime {
  val intStream = Stream.from(2) filter IntOps.isPrime

  def withNumberOfDigits(digit: Int): IndexedSeq[Int] =
    (pow(10, digit - 1).toInt to pow(10, digit).toInt) filter IntOps.isPrime
}
