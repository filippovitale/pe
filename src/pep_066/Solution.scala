package pep_066

object Solution {

  // http://en.wikipedia.org/wiki/Pell%27s_equation#Additional_solutions_from_the_fundamental_solution
  // http://mathworld.wolfram.com/PellEquation.html

  def solveX(ddd: Int, a0: Int): BigInt = {

    var (m, d, a) = (BigInt(0), BigInt(1), BigInt(a0))
    var (x, x1, y, y1) = (a, d, d, m)

    while (x * x - BigInt(ddd) * y * y != BigInt(1)) {
      m = d * a - m
      d = (ddd - m * m) / d
      a = (a0 + m) / d

      val x2: BigInt = x1
      x1 = x
      val y2: BigInt = y1
      y1 = y

      x = a * x1 + x2
      y = a * y1 + y2
    }

    x
  }

  def solve(max: Int = 1000) = {
    val rationalSquares = (1 to max).map(n => n * n).takeWhile(_ <= max).toSet

    val irrationalSquares = (1 to max).filterNot(rationalSquares.contains)

    val dAndX = for {
      d <- irrationalSquares
      a0 = math.floor(math.sqrt(d)).toInt
    } yield (d, solveX(d, a0))

    dAndX.sortBy(_._2).last._1
  }
}
