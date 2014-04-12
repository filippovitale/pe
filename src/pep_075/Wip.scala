package pep_075

object Wip {

  // http://en.wikipedia.org/wiki/Pythagorean_triple
  // a * a + b * b = c * c
  // p = a + b + c
  // p is always even
  // a < b < c
  // a < p / 3

  def isPerimiterOfHowManyRightAngleTriangle(p: Int): Long = {

    if (p % 2 == 0) {
      val n = for {
        a <- 2 until p / 3
        if p * (p - 2 * a) % (2 * (p - a)) == 0
      } yield a
      n.length
    } else {
      0
    }
  }

  def solve(max: Int = 1500000) = (1 to max).par.map(isPerimiterOfHowManyRightAngleTriangle).count(_ == 1)
}
