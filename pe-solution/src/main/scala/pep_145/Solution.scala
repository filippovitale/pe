package pep_145

import common.IntOps._

object Solution {
  val MAX = 1000000000

  def isReversible(is: Iterator[Int]): Boolean = {
    val ns = is.toList
    val rs = ns.reverse
    if ((ns.head + rs.head) % 2 == 0) false
    else {
      val ds = ns.zip(rs).map { case (a, b) => a + b }
      val sum = (0 /: ds)(_ * 10 + _)
      reverseDigits(sum).forall(_ % 2 == 1)
    }
  }

  def solve() = iteratorFrom(1).take(MAX).filterNot(_ % 10 == 0).map(reverseDigits).count(isReversible)
}
