package pep_094

object Solution {
  val MAX = 1000000000

  val ats = for {
    a <- Iterator.from(3, 2).takeWhile(_ < MAX / 3).map(_.toLong)
    b <- Iterator(a - 1, a + 1)
    b2 = b / 2
    hh = a * a - b2 * b2
    h = math.sqrt(hh)
    if h.isWhole()
    if h.toLong * h.toLong == hh // NEVER trust a Double with more than 15 digits
  } yield a + a + b

  def solve() = ats.takeWhile(_ < MAX).sum
}
