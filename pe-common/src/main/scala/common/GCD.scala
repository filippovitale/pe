package common

import scala.annotation.tailrec

// Implemented for pep_075
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

  @tailrec
  def gcdR(m: Int, n: Int): Int = if (n > m) gcdR(n, m)
  else {
    if (m % n == 0) n
    else gcdR(n, m - n * (m / n))
  }
}
