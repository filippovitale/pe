package pep_066

object Wip {

  def solve(max: Int = 1000) = {
    val rationalSquares = (1 to max).map(n => n * n).takeWhile(_ <= max).toSet

    val irrationalSquares = (1 to max).filterNot(rationalSquares.contains)

    val aaa = for {
      d <- irrationalSquares
    } yield (d, ???)

    aaa.sortBy(_._2).last._1
  }

}
