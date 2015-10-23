package pep_051

import common.Prime.withNumberOfDigits

object Solution {
  val primes6digit = withNumberOfDigits(6).map(_.toString)

  val pattern3x3 = for {
    a <- 0 to 5
    b <- 0 to 5
    if b > a
    c <- 0 to 5
    if c > b
    s = (0 to 5).to[Set] - a - b - c
    x = s.head
    y = (s - x).head
    z = (s - x - y).head
  } yield ((n: String) => (n(a), n(b), n(c)), (n: String) => (n(x), n(y), n(z)))

  def solve() = {
    val maps = for {
      (p, m) <- pattern3x3
      (_, g) <- primes6digit.groupBy(p)
      sameDigits = g.groupBy(m).filter {
        case (d, _) => d._1 == d._2 && d._2 == d._3
      }
      if sameDigits.size == 8
    } yield sameDigits

    maps.head.values.flatten.min
  }
}
