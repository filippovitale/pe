package pep_051

import scala.collection.immutable.IndexedSeq
import scala.math.pow

object Solution {

  def solve() = "TODO"

}

object PrimesSeq {
  def apply(minPrime: Int, maxPrime: Int): IndexedSeq[Int] = minPrime until maxPrime filter (isPrime(_))

  def apply(digit: Int): IndexedSeq[Int] = apply(pow(10, digit - 1).toInt, pow(10, digit).toInt)

  def isPrime(n: Long): Boolean = BigInt(n).isProbablePrime(5)
}
