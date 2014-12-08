package pep_060

import pep_050.Memo
import pep_051.PrimesSeq

object Solution {

  val primes = PrimesSeq(2, 10000)

  lazy val areConcatenationsPrime: Memo[(Int, Int), Boolean] = Memo {
    case (p1, p2) => Seq((p1, p2), (p2, p1)).map(t => s"${t._1}${t._2}").map(_.toLong).forall(PrimesSeq.isPrime)
  }

  def solve() = {
    for {
      a <- primes
      b <- primes
      if a < b
      if areConcatenationsPrime((a, b))
      c <- primes
      if b < c
      if areConcatenationsPrime((a, c))
      if areConcatenationsPrime((b, c))
      d <- primes
      if c < d
      if areConcatenationsPrime((a, d))
      if areConcatenationsPrime((b, d))
      if areConcatenationsPrime((c, d))
      e <- primes
      if d < e
      if areConcatenationsPrime((a, e))
      if areConcatenationsPrime((b, e))
      if areConcatenationsPrime((c, e))
      if areConcatenationsPrime((d, e))
    } yield a + b + c + d + e
  }.head
}
