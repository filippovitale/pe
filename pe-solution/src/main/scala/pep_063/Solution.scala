package pep_063

object Solution {

  def expSeq(a: Int, b: Int): (Int, Int) = (b, BigInt(a).pow(b).toString().size)

  val sizes = for {
    a <- 1 to 10
  } yield (1 to 30).map(expSeq(a, _)).dropWhile(t => t._1 < t._2).takeWhile(t => t._1 == t._2).size

  def solve = sizes.sum

}
