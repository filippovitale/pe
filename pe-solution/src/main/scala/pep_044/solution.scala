package pep_044

import scala.collection.immutable._

object solution {

  val pentagonNumbers: Stream[Int] = {
    def loop(n: Int): Stream[Int] = ((n * (3 * n - 1)) / 2) #:: loop(n + 1)
    loop(1)
  }


  def solve(): String = {
    val pentagonNumbersSet = pentagonNumbers.takeWhile(_ < 10000000).toSet

    val pair = for {
      big <- pentagonNumbers
      small <- pentagonNumbers.takeWhile(pn => pn < big)
      if pentagonNumbersSet.contains(big + small)
      if pentagonNumbersSet.contains(big - small)
    } yield big - small

    pair.head.toString
  }

}
