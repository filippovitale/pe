package pep_043

object solution {

  @deprecated("Check not needed", "ever")
  def isPandigital09(n: Seq[Int]): Boolean = n.forall(_ < 10) && n.toSet.size == 10


  def primaryProperty(n: Seq[Int]): Boolean = {
    val divisors = Seq(2, 3, 5, 7, 11, 13, 17)
    val triplets = n.sliding(3).drop(1).map(_.mkString).map(_.toInt).toSeq
    triplets.zip(divisors).forall(v => (v._1 % v._2) == 0)
  }

  val pandigital09 = (0 to 9).permutations.filter(s => s(5) == 5 || s(5) == 0)

  def solve() = pandigital09.filter(primaryProperty).map(_.mkString("")).map(_.toLong).sum

}
