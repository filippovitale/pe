package pep_075

import common.GCD.gcd

object Solution {

  def isPerimiterOfOnlyOneRightAngleTriangle(p: Int): Boolean = {
    var (t, k, overflow) = (0, 0, false)

    for (m <- 2 to math.floor(math.sqrt(p / 2)).toInt; if !overflow) {
      if ((p / 2) % m == 0) {
        if (m % 2 == 0) {
          k = m + 1
        } else {
          k = m + 2
        }
        while (!overflow && (k < 2 * m && k <= p / (2 * m))) {
          if (p / (2 * m) % k == 0 && gcd(k, m) == 1) {
            t += 1
            overflow = t == 2
          }
          k += 2
        }
      }
    }
    t == 1
  }

  def solve(max: Int = 1500000) = (1 to max).par.count(isPerimiterOfOnlyOneRightAngleTriangle) / 2
}
