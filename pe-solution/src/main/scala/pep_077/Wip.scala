package pep_077

import pep_050.Memo
import pep_051.PrimesSeq

object Wip {

  val primes = PrimesSeq(2, 1000)

  object Solve1 {

    def combination(leftover: Int, prime: Int): Int =
      if (leftover == 1 || prime == 1) {
        //println(s"1) leftover=$leftover\tprime=$prime")
        1
      }
      else {
        //println(s"-) leftover=$leftover\tprime=$prime")
        val previousPrime = primes.takeWhile(_ < prime).lastOption.getOrElse(1)
        //println(s"-) previousPrime=$previousPrime")
        val is = 0 until leftover / prime + 1
        //println(s"-) is=$is")
        (0 /: is) { case (acc, i) => acc + combination(leftover - prime * i, previousPrime) }
      }

    def count(x: Int): Int = {
      val y = x - 1
      combination(x, y)
    }
  }

  object Solve2 {

    lazy val count: Memo[Int, Int] = Memo { n: Int =>
      if (n == 0) 1
      else {

        val candidates = primes.takeWhile(_ <= n)
        if (candidates.nonEmpty) {
          val counts = for {
            c <- candidates
            i <- (1 to n / c).toIndexedSeq
          } yield count(n - c * i)

          counts.sum
        } else {
          0
        }
      }
    }

  }

  object Solve3 {

    lazy val count: Memo[(Int, Int), Int] = Memo { case (leftover: Int, limit: Int) =>
      //println(s"-) leftover=$leftover\tlimit=$limit")
      if (leftover == 0) 1
      else if (leftover == 1 || limit == 0) 0
      else {
        val branches = for {
          p <- primes.takeWhile(_ <= limit)
          i <- (1 to leftover / p).toIndexedSeq
        } yield count((leftover - i * p, primes.takeWhile(_ < p).lastOption.getOrElse(0)))
        //println(s"s) branches.sum=${branches.sum}")
        branches.sum
      }
    }

    def count(x: Int): Int = primes
      .takeWhile(_ <= x)
      .lastOption
      .map(p => count((x, p)))
      .getOrElse(0)

    def solve(ways: Int): Int =
      (0 to 1000)
        .map((x: Int) => count(x))
        .zipWithIndex
        .dropWhile(_._1 <= ways).head._2
  }

}
