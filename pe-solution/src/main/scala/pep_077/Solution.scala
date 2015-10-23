package pep_077

import common.Memo
import common.IntOps

object Solution {

  val primes = (2 to 10000) filter IntOps.isPrime

  lazy val count: Memo[(Int, Int), Int] = Memo { case (leftover: Int, limit: Int) =>
    if (leftover == 0) 1
    else if (leftover == 1 || limit == 0) 0
    else {
      val branches = for {
        p <- primes.takeWhile(_ <= limit)
        i <- (1 to leftover / p).toIndexedSeq
      } yield count((leftover - i * p, primes.takeWhile(_ < p).lastOption.getOrElse(0)))
      branches.sum
    }
  }

  def count(x: Int): Int = primes
    .takeWhile(_ <= x)
    .lastOption
    .map(p => count((x, p)))
    .getOrElse(0)

  def solve(ways: Int): String =
    (0 to 1000)
      .map((x: Int) => count(x))
      .zipWithIndex
      .dropWhile(_._1 <= ways)
      .head._2
      .toString

  def solve(): String = solve(5000)

}
