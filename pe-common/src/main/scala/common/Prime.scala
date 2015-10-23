package common

import scala.math.pow

// Implemented for pep_051
object Prime {
  def isPrime(n: Long): Boolean = BigInt(n) isProbablePrime 5

  val stream = Stream.from(2) filter (isPrime(_))

  def withNumberOfDigits(digit: Int): IndexedSeq[Int] = (pow(10, digit - 1).toInt to pow(10, digit).toInt) filter (isPrime(_))
}
