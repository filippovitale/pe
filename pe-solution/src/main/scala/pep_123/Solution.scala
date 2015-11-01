package pep_123

import common.LongOps._

object Solution {
  val MAX = 10000000000L
  val pns = primeStreamFrom(2) zip streamFrom(1)

  def r(pn: Long, n: Long): Long = {
    val pn2 = pn * pn
    val a = BigInt(pn - 1)
    val b = BigInt(pn + 1)
    val res = a.modPow(n, pn2) + b.modPow(n, pn2)
    res.longValue() % pn2
  }

  def solve() = pns
    .map { case (pn, n) => (n, r(pn, n)) }
    .dropWhile(_._2 < MAX)
    .head._1

}
