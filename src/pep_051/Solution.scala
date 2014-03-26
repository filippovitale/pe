package pep_051

import scala.collection.immutable.IndexedSeq
import scala.math.pow

object PrimesSeq {
  def apply(minPrime: Int, maxPrime: Int): IndexedSeq[Int] = minPrime until maxPrime filter (isPrime(_))

  def apply(digit: Int): IndexedSeq[Int] = apply(pow(10, digit - 1).toInt, pow(10, digit).toInt)

  def isPrime(n: Long): Boolean = BigInt(n).isProbablePrime(5)
}

object Solution {

  val primes6digit = pep_051.PrimesSeq(6).map(_.toString)

  val pattern = for {
    a <- 0 to 5
    b <- 0 to 5
    if b > a
    c <- 0 to 5
    if c > b
    s = (0 to 5).to[Set] - a - b - c
    x = s.head
    y = (s - x).head
    z = (s - x - y).head
  } yield ((n: String) => (n(a), n(b), n(c)), (n: String) => (n(x), n(y), n(z)))

  def solve() = {
    val maps = for {
      p <- pattern
      aaa <- pep_051.PrimesSeq(6).map(_.toString).groupBy(p._1)
      bbb = aaa._2
      ddd = bbb.groupBy(p._2).filter {
        case (d, _) => d._1 == d._2 && d._2 == d._3
      }
      ccc = ddd.size
    } yield (ddd, ccc)

    maps.filter(_._2 == 8).head._1.values.flatten.min
  }
}
