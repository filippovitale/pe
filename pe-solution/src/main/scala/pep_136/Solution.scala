package pep_136

import scala.collection.mutable

object Solution {

  def solve() = {
    val MAX = 50000000

    val solutions = for {
      u <- Iterator.from(1).takeWhile(_ < MAX)
      v <- Iterator.from(1).takeWhile(_ * u < MAX)
      if (u + v) % 4 == 0
      if 3 * v > u
      if ((3 * v - u) % 4) == 0
    } yield u * v

    val once = mutable.Set.empty[Int]
    val mult = mutable.Set.empty[Int]
    solutions.foreach { n =>
      if (once.contains(n)) {
        once -= n
        mult += n
      } else if (!mult.contains(n)) once += n
    }

    once.size
    /*
      // 162620ms
      ((Set.empty[Int], Set.empty[Int]) /: solutions) { case((once, mult), n) =>
        if (once.contains(n)) (once - n, mult + n)
        else if (!mult.contains(n)) (once + n, mult)
        else (once, mult)
      }._1.size
     */
  }

}
