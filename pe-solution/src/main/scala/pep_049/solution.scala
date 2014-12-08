package pep_049

import scala.collection.immutable.SortedSet

object solution {

  val minPrime = 1000
  val maxPrime = 10000
  val primes = (minPrime until maxPrime).filter(BigInt(_).isProbablePrime(5)).to[SortedSet]

  def isPermutation(a: Int, b: Int): Boolean = a.toString.sorted == b.toString.sorted

  def solve() = {
    val solutions = for {
      n1 <- primes.takeWhile(_ < (2 * minPrime + maxPrime) / 3)
      n2 <- primes.dropWhile(_ <= n1).takeWhile(_ <= n1 + ((maxPrime - n1) / 2))
      n3 = 2 * n2 - n1
      if primes.contains(n3)
      if isPermutation(n1, n2)
      if isPermutation(n1, n3)
    } yield (n1, n2, n3)

    solutions.map(_.productIterator.mkString).filter(_ != "148748178147").head
  }

}
