package pep_085

object Solution {

  val MAX = 2000000

  def innerRectangles(x: Int, y: Int) = for {
    i <- 0 until x
    j <- 0 until y
  } yield (x - i) * (y - j)

  def solve(): String = {
    val candidates = for {
      x <- 10 to 100
      y <- 10 to 100
      r = innerRectangles(x, y).sum
      if r < MAX
      a = x * y
    } yield (r, a)

    candidates.sortBy(_._1).last._2.toString
  }

}
