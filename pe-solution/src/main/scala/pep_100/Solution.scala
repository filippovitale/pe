package pep_100

import common.LongOps.streamSequence2

object Solution {

  val MAX = 1000000000000L
  val initial = (85L, 120L)

  // https://en.wikipedia.org/wiki/Pell%27s_equation

  def bb(b: Long, t: Long) = 3 * b + 2 * t - 2

  def tt(b: Long, t: Long) = 4 * b + 3 * t - 3

  def solve() = streamSequence2(initial, (bb, tt)).dropWhile(_._2 < MAX).head._1

}
