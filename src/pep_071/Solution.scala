package pep_071

import Stream._

object Solution {

  def solve(max: Int = 8): Int = {
    val (lowN, lowD) = (2, 5)
    val (highN, highD) = (3, 7)

    lazy val lower: Stream[(Int, Int)] = (lowN, lowD) #:: lower map {
      case (n, d) => (n + highN, d + highD)
    }

    lower.dropWhile(_._2 + highD < max).head._1
  }
}
