package pep_043

object solution {

  @deprecated("Check not needed", "ever")
  def isPandigital09(n: Seq[Int]): Boolean = n.forall(_ < 10) && n.toSet.size == 10

  val divisors = Seq(2, 3, 5, 7, 11, 13, 17)

  def primaryProperty(n: Seq[Int]): Boolean = {
    val triplets = n.sliding(3).drop(1).map(_.mkString).map(_.toInt).toSeq
    triplets.zip(divisors).forall(v => (v._1 % v._2) == 0)
  }

  val pandigital09: Iterator[Seq[Int]] = (0 to 9).permutations.filter(s => Set(0, 5).contains(s(5))) // TODO s(5) == 5 || s(5) == 0


  val asdf: Iterator[Seq[Int]] = pandigital09.filter(primaryProperty)

  def solve(): String = pandigital09.filter(primaryProperty).map(_.mkString("")).map(_.toLong).sum.toString

}
