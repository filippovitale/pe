package pep_100

object Wip {

  object Attempt1 {
    def pbb(b: Double, t: Double) = (b / t) * ((b - 1) / (t - 1))

    assert(pbb(15, 21) == 0.5)
    assert(pbb(85, 120) == 0.5000000000000001)

    def bb(b: Long) = 2 * (b * b) - 2 * b

    def tt(t: Long) = t * t - t

    def root(b: Long, t: Long) = bb(b) - tt(t)

    for {
      i <- 10 to 20
      z = root(i, 21)
    } println(s"i=$i\t z=$z")
  }

  object Attempt2 {
    // https://en.wikipedia.org/wiki/Newton%27s_method
  }

  object Attempt3 {
    // https://en.wikipedia.org/wiki/Diophantine_equation

    def bb(b: Long, t: Long) = 3 * b + 2 * t - 2

    def tt(b: Long, t: Long) = 4 * b + 3 * t - 3

    val aaa: Stream[(Long, Long)] = (15L, 21L) #:: aaa.tail.map {
      case (b, t) => (bb(b, t), tt(b, t))
    }

  }

}
