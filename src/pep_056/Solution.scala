package pep_056

object Solution {

  def solve(max: Int = 100) = {
    val digitSum = for {
      a <- 1 until max
      b <- 1 until max
      ab = BigInt(a).pow(b)
    } yield ab.toString().map(_.toInt - '0'.toInt).sum

    digitSum.max
  }
}
