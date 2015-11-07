package pep_145

import common.IntOps._

object Wip {

  object Attempt1 {
    val MAX = 1000000000

    def isReversible(ns: List[Int]): Boolean = {
      val rs = ns.reverse
      if ((ns.head + rs.head) % 2 == 0) false
      else {
        val ds = ns.zip(rs).map { case (a, b) => a + b }
        val sum = (0 /: ds)(_ * 10 + _)
        digits(sum).forall(_ % 2 == 1)
      }
    }

    def isReversible(ns: Iterator[Int]): Boolean = isReversible(ns.toList)

    def isReversible(n: Int): Boolean = {
      //val rev = Iterator.iterate(n)(_ / 10).takeWhile(_ != 0).map(_ % 10).reduce(_ * 10 + _)
      (n + n.toString.reverse.toInt).toString.forall(
        c => c == '1' || c == '3' || c == '5' || c == '7' || c == '9')
    }

    def solve() = iteratorFrom(12).take(MAX).filterNot(_ % 10 == 0).count(isReversible)
  }

}
