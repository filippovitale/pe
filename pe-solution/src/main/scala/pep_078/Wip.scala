package pep_078

import common.Memo
import scala.collection.mutable

object Wip {

  // adapted from pep_077
  object Attempt1 {

    lazy val count: Memo[(Int, Int, Int), Int] = Memo { case (leftover: Int, limit: Int, level: Int) =>
      //if (leftover == 0 || limit == 0) {
      //if (leftover == 1 || limit == 1) {
      if (leftover == 0) {
        //println("\t" * level + s"1) leftover=$leftover\tlimit=$limit")
        1
      }
      else if (limit == 0) {
        //println("\t" * level + s"0) leftover=$leftover\tlimit=$limit")
        0
      }
      else {
        val branches = for {
          k <- 1 to leftover
          i <- 0 to limit / k
        //_ = println("\t" * level + s"k=$k\ti=$i")
        } yield count((leftover - i * k, limit - 1, level + 1))
        //println("\t" * level + s"-) leftover=$leftover\tlimit=$limit\tbranches.sum=${branches.sum}")
        branches.sum
      }
    }

    def p(n: Int): Int = count((n, n, 0))

    def solve(ways: Int): Int = ???
  }

  // adapted from pep_076
  object Attempt2 {

    lazy val count: Memo[(Int, Int), Int] = Memo { case (leftover: Int, limit: Int) =>
      if (leftover == 1 || limit == 1) 1
      else (0 /: (0 until leftover / limit + 1)) { case (acc, i) =>
        acc + count((leftover - limit * i, limit - 1))
      }
    }

    def p(x: Int): Int = count((x, x))

    // stackoverflow
    def solve(k: Int): Int = (1 to 10000)
      .map(n => (n, p(n)))
      .dropWhile(_._2 % k != 0)
      .head._1
  }

  // adapted from pep_076
  object Attempt3 {
    val max = 1000
    val way = mutable.IndexedSeq.fill(max + 1)(1L)

    for {
      i <- 2 to max
      j <- i to max
    } way(j) += way(j - i)

    def p(n: Int): Long = way(n)

    def solve(k: Int): (Int, Long) = (1 to max)
      .map(n => (n, p(n)))
      .dropWhile(_._2 % k != 0)
      .head
  }

  object Attempt4 {
    val max = 1000

    // https://en.wikipedia.org/wiki/Partition_(number_theory)
    // https://en.wikipedia.org/wiki/Pentagonal_number
    val penta = Stream.from(1).flatMap { n => List(n, -1 * n) }.map(n => (3L * n * n - n) / 2)

    val partitions = 1 #:: Stream.from(1).map(_.toLong).map(partition)

    def partition(n: Long): Long =
      penta.takeWhile(_ <= n)
        .map(n - _).map(i => partitions(i.toInt))
        .grouped(2).map(_.sum)
        .grouped(2).map {
        case a :: b :: Nil => a - b
        case a :: Nil      => a
      }.sum

    def p(n: Int): Long = partitions(n)

    def solve(k: Long): Option[(Int, Long)] = (2 to max)
      .map(n => (n, p(n)))
      .dropWhile(_._2 % k != 0)
      .headOption

  }

  object Attempt5 {
    val penta = Stream.from(1).flatMap { n => List(n, -1 * n) }.map(n => (3 * n * n - n) / 2)

    def solve(k: Int): Option[(Int, Int)] = {

      lazy val partitions = 1 #:: Stream.from(1).map(p)

      def p(n: Int): Int = penta
        .takeWhile(_ <= n)
        .map(n - _).map(partitions)
        .grouped(2).map(_.sum)
        .grouped(2).map {
        case a :: b :: Nil => a - b
        case a :: Nil      => a
      }.foldLeft(0L) { case (a, b) => (a + b) % k }
        .toInt

      Stream.from(2)
        .map(n => (n, p(n)))
        .dropWhile(_._2 != 0)
        .headOption
    }

    // 95% of the CPU time on `Stream.drop`
    //  4% of the CPU time on `Stream$Cons.tail`
  }

}
