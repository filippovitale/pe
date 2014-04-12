package pep_075

object GCD {
  def gcd(a: Int, b: Int): Int = {
    var y = 0
    var x = 0

    if (a > b) {
      x = a
      y = b
    } else {
      x = b
      y = a
    }

    while (x % y != 0) {
      val temp = x
      x = y
      y = temp % x
    }
    y
  }
}

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
          if (p / (2 * m) % k == 0 && GCD.gcd(k, m) == 1) {
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
