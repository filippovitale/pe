package pep_060

import pep_051.PrimesSeq

object Solution {

  val primes = PrimesSeq(2, 10000)

  def areConcatenationsPrime(p1: Int, p2: Int): Boolean =
    PrimesSeq.isPrime((p1.toString + p2.toString).toLong) &&
      PrimesSeq.isPrime((p2.toString + p1.toString).toLong)

  def solve() = {
    for {
      a <- primes
      b <- primes
      if a < b
      if areConcatenationsPrime(a, b)
      c <- primes
      if b < c
      if areConcatenationsPrime(a, c)
      if areConcatenationsPrime(b, c)
      d <- primes
      if c < d
      if areConcatenationsPrime(a, d)
      if areConcatenationsPrime(b, d)
      if areConcatenationsPrime(c, d)
      e <- primes
      if d < e
      if areConcatenationsPrime(a, e)
      if areConcatenationsPrime(b, e)
      if areConcatenationsPrime(c, e)
      if areConcatenationsPrime(d, e)
    } yield a + b + c + d + e
  }.head
}
