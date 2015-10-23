package pep_091

import common.GCD.gcd

object Wip {

  object Attempt1 {

    val MAX = 2
    val solutions = for {
      px <- 1 to MAX
      py <- 1 to MAX
      qx <- 1 to MAX
      qy <- 1 to MAX
      if (px, py) != ((qx, qy))
      if false // TODO
    } yield (px, py, qx, qy)

    def solve() = solutions.size + 3 * MAX * MAX
  }

  object Attempt2 {

    // TODO 50
    val MAX = 2
    val candidates = for {
      p <- 1 to MAX
      q <- 1 to MAX
      g = gcd(p, q)
      pp = q * g
      qq = (MAX - p) * g
    } yield (pp / p, qq / q)

    def solve() = 2 * candidates.map((math.min _).tupled).sum + 3 * MAX * MAX
  }

}
