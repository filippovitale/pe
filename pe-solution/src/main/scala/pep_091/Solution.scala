package pep_091

import pep_075.GCD.gcd

object Solution {
  val MAX = 50
  val candidates = for {
    p <- 1 to MAX
    q <- 1 to MAX
    g = gcd(p, q)
    pp = q * g
    qq = (MAX - p) * g
  } yield (pp / p, qq / q)

  def solve() = 2 * candidates.map((math.min _).tupled).sum + 3 * MAX * MAX

}
