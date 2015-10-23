package pep_087

import common.Prime
import spire.implicits._

object Solution {
  val MAX = 50000000

  val ppts = for {
    a <- Prime.stream takeWhile (_ <= (MAX - (2 * 2 * 2 + 2 * 2 * 2 * 2)).sqrt())
    x = a.toLong * a
    b <- Prime.stream takeWhile (_ <= (MAX - (2 * 2 + 2 * 2 * 2 * 2)).sqrt())
    y = b.toLong * b * b
    if x + y < MAX
    c <- Prime.stream takeWhile (_ <= (MAX - (2 * 2 + 2 * 2 * 2)).sqrt())
    z = c.toLong * c * c * c
    xyz = x + y + z
    if xyz < MAX
  } yield xyz

  def solve() =
    ppts.toSet.size.toString

}
