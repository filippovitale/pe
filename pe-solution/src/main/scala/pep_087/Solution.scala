package pep_087

import pep_051.PrimesSeq.isPrime
import spire.implicits._

object Solution {

  val primes = Stream.from(2) filter (isPrime(_))

  val MAX = 50000000

  val ppts = for {
    a <- primes takeWhile (_ <= (MAX - (2 * 2 * 2 + 2 * 2 * 2 * 2)).sqrt())
    x = a.toLong * a
    b <- primes takeWhile (_ <= (MAX - (2 * 2 + 2 * 2 * 2 * 2)).sqrt())
    y = b.toLong * b * b
    if x + y < MAX
    c <- primes takeWhile (_ <= (MAX - (2 * 2 + 2 * 2 * 2)).sqrt())
    z = c.toLong * c * c * c
    xyz = x + y + z
    if xyz < MAX
  } yield xyz

  def solve() =
    ppts.toSet.size.toString

}
