package pep_135

import scalaz.std.list._
import scalaz.std.map._
import scalaz.std.anyVal._
import scalaz.syntax.foldable._

object Solution {

  def solve() = {
    val MAX = 1000000

    val solutions = for {
      u <- Iterator.from(1).takeWhile(_ < MAX).toList
      v <- Iterator.from(1).takeWhile(_ * u < MAX).toList
      if (u + v) % 4 == 0
      if 3 * v > u
      if ((3 * v - u) % 4) == 0
    } yield u * v

    solutions.foldMap(x => Map(x -> 1)).count(_._2 == 10)
  }

}
