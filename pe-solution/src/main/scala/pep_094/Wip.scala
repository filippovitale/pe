package pep_094

object Wip {

  object Attempt1 {
    val MAX = 1000000000

    val ats = for {
      a <- Iterator.from(2).takeWhile(_ < MAX / 3)
      b <- Iterator(a - 1, a, a + 1)
      perimeter = a + a + b
      s = perimeter.toDouble / 2
      area = math.sqrt(s * (s - a) * (s - a) * (s - b))
      if area.isWhole()
    } yield perimeter

    def solve() = 4 + ats.takeWhile(_ < MAX).sum
  }

  object Attempt2 {
    val MAX = 1000000000

    val ats = for {
      a <- Iterator.from(2).takeWhile(_ < MAX / 3)
      b <- Iterator(a - 1, a, a + 1)
      if b % 2 == 0
      b2 = b.toDouble / 2
      hh = a * a - b2 * b2
      h = math.sqrt(hh)
      if h.isWhole()
      area = b2 * h
      if area.isWhole()
    } yield a + a + b

    def solve() = 4 + ats.takeWhile(_ < MAX).sum
  }

}
