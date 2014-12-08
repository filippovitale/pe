package pep_073

// http://en.wikipedia.org/wiki/Farey_sequence
case class Farey(n: Long, a: Long, b: Long, c: Long, d: Long) {
  def next = {
    val k = (n + b) / d
    val p = (k * c) - a
    val q = (k * d) - b
    Farey(n, c, d, p, q)
  }

  val value: Double = a.toDouble / b.toDouble
}

object FareySeq {
  def apply(n: Long) = {
    lazy val fareySeq: Stream[Farey] = Farey(n, 0, 1, 1, n) #:: fareySeq map (_.next)
    fareySeq
  }
}


object Solution {

  def solve(max: Int = 12000) = FareySeq(max).dropWhile(_.value <= 1D / 3D).takeWhile(_.value < 1D / 2D).length

}
