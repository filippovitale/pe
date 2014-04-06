package pep_064

object Solution {

  def continuedFractionPeriod(n: Int): Seq[Int] = {

    val fSqrt = math.floor(math.sqrt(n)).toInt
    val lastContinuedFraction = fSqrt * 2

    lazy val cfStream: Stream[(Int, Int, Int)] = (0, 1, fSqrt) #:: (cfStream map {
      case (m: Int, d: Int, a: Int) =>
        val mn: Int = d * a - m
        val dn: Int = (n - mn * mn) / d
        val an: Int = (fSqrt + mn) / dn
        (mn, dn, an)
    })

    // The period doesn't include a0
    (cfStream.drop(1) map {
      case (_, _, a) => a
    } takeWhile (_ != lastContinuedFraction)).toSeq ++ Seq(lastContinuedFraction)
  }

  def solve(max: Int = 10000) = {
    val rationalSquares = (1 to max).map(n => n * n).takeWhile(_ <= max).toSet

    val irrationalSquares = (1 to max).filterNot(rationalSquares.contains)

    val continuedFractionPeriodLengths = for {
      n <- irrationalSquares
    } yield continuedFractionPeriod(n).length

    continuedFractionPeriodLengths.count(_ % 2 == 1)
  }


}
