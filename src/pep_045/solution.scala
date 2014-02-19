package pep_045

import scala.collection.immutable._
import scala.annotation.tailrec

object solution {

  @deprecated("hexagonalNumbers are also triangleNumbers", "ever")
  val triangleNumbers: Stream[Long] = {
    def loop(n: Long): Stream[Long] = ((n * (n + 1)) / 2) #:: loop(n + 1)
    loop(1)
  }

  val pentagonalNumbers: Stream[Long] = {
    def loop(n: Long): Stream[Long] = ((n * (3 * n - 1)) / 2) #:: loop(n + 1)
    loop(1)
  }

  val hexagonalNumbers: Stream[Long] = {
    def loop(n: Long): Stream[Long] = (n * (2 * n - 1)) #:: loop(n + 1)
    loop(1)
  }

  @tailrec
  def findNext(hs: Stream[Long] = hexagonalNumbers,
               ps: Stream[Long] = pentagonalNumbers): Long = {

    val hh = hs.head
    val p = ps.dropWhile(n => n < hh)

    val ph = p.head

    if (hh == ph) hh
    else findNext(hs.tail, p.tail)
  }

  def solve() = findNext(hexagonalNumbers.dropWhile(n => n < 40755).tail, pentagonalNumbers.tail)

}
