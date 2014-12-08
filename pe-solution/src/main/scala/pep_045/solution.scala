package pep_045

import scala.collection.immutable._
import scala.annotation.tailrec

object solution {

  @deprecated("hexagonalNumbers are also triangleNumbers", "ever")
  val triangleNumbers = {
    def loop(n: Long): Stream[Long] = ((n * (n + 1)) / 2) #:: loop(n + 1)
    loop(1)
  }

  val pentagonalNumbers = {
    def loop(n: Long): Stream[Long] = ((n * (3 * n - 1)) / 2) #:: loop(n + 1)
    loop(1)
  }

  val hexagonalNumbers = {
    def loop(n: Long): Stream[Long] = (n * (2 * n - 1)) #:: loop(n + 1)
    loop(1)
  }

  @tailrec
  def findNext(hs: Stream[Long] = hexagonalNumbers, ps: Stream[Long] = pentagonalNumbers): Long = {

    val n = hs.head
    val p = ps.dropWhile(_ < n)

    if (n == p.head) n
    else findNext(hs.tail, p.tail)
  }

  def solve() = findNext(hexagonalNumbers.dropWhile(n => n < 40755).tail, pentagonalNumbers.tail)

}
