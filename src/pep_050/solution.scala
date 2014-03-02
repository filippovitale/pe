package pep_050

import scala.collection.immutable.{IndexedSeq, SortedSet}
import pep_081.Memoize1
import scala.annotation.tailrec

object solution {

  val maxPrime = 1000 * 1000
  val primes = (2 until maxPrime).filter(BigInt(_).isProbablePrime(5)).to[Array] //[SortedSet]

  def isPrime(n: Int): Boolean = primes.contains(n)

  def addToAPrime(ps: Iterator[Int], n: Int): Boolean = primes.contains(ps.take(n).sum)

  case class IndexLength(i: Int, l: Int)

  //@tailrec
  def sumIndexLength(f: IndexLength => Int)(il: IndexLength): Int = {
    println("l=%d" format il.l)
    il.l match {
      case 0 => 0
      case 1 => primes(il.i)
      case l => primes(il.i) + f(IndexLength(il.i + 1, l - 1))
    }
  }

  // iterate until primes.size - longest ?
  def sumFrom(b: Int): Seq[(Int, Int)] = for {
    l <- 1 to primes.size - b
  } yield (l, Memoize1.Y(sumIndexLength)(IndexLength(b, l)))

  def solve(ps: Array[Int]): Int = {
    var longest = (0, 0)

    @tailrec
    def findLongest(i: Int) {
      val longestForI = sumFrom(i).filter(t => isPrime(t._2)).last
      if (longest._1 < longestForI._1) longest = longestForI
      if (i < ps.size - longest._1) findLongest(i + 1) // TODO < or <=
    }
    findLongest(0)

    longest._2
  }

}

// TODO fix  def addConsecutivePrimes(ps: SortedSet[Int], n: Int): Int = ps.drop(d).take(n).sum

//  val maxPossible = 547 // primes.take(547).sum // 1001604
//  def maxNumberOfConsecutivePrimes(f: Int => Int)(p: Int): Int = {
//    val ps = primes.takeWhile(_ < p)
//    //val px = ps.takeRight(1).head // TODO sure?
//    //    f()
//    //    1 + Seq((p - px),...).max
//    val aaa = ps.map(p - _).map(f)
//    if (aaa.isEmpty) 0
//    else 1 + aaa.max
//  }
//
//  def solve() = {
//    Memoize1.Y(maxNumberOfConsecutivePrimes)(99)
//  }

// obj map(pri -> max length)


//    val pps = ps.toList
//    while (ps.hasNext) {
//      val p = ps.next()
// find longest seq
//      (22 to ps.size).map(pps.take(_))

// (2 to ps.size).map(pps.take(_)).map(_.sum).take(10).filter(primes.contains(_)) // Vector(5, 17, 41)
// (22 to pps.size).map(n => (ps.take(n).sum -> n)).filter(n => primes.contains(n._1)).sortBy(_._2).last // (958577,536) <-- solution must be >536

// (0 to 1000).toStream.takeWhile(_ => sum < 1000).foreach(i => sum+=i)
