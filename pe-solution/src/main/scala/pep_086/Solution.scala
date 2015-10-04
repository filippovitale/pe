package pep_086

import spire.implicits._

object Solution {

  val MAX = 1000000

  def solve(): String = {

    // http://oeis.org/A143714
    // (PARI) A143714(M)=sum(a=1, M, sum(b=a, M, issquare((a+b)^2+M^2)))

    lazy val a143714: Stream[(Int, Int)] = (0, 2) #:: a143714.map { case (c, a) =>
      val m = a + 1
      val counts = for {
        ab <- 3 to 2 * m
        if (ab * ab + m * m).toDouble.sqrt().isWhole()
      } yield math.min(ab, m + 1) - (ab + 1) / 2

      (c + counts.sum, m)
    }

    a143714.dropWhile(_._1 < MAX).head._2.toString
  }
}
