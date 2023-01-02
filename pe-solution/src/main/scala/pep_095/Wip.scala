package pep_095

<<<<<<< Updated upstream
import common.LongOps

import scala.collection.mutable

object Wip {
  val MAX = 1000000

  object Attempt1 {
    def solve(): String = {
      //      val a: Seq[(ChainLength, Long)] = (1 to MAX).map(a => (aaa(a), a * -1))
      //      val b: (ChainLength, Long) = a.max
      //      val c: Long = b._2 * -1
      //      c.toString
      ???
    }
  }

  object Attempt2 {
    type ChainLength = Int
    val map = mutable.Map.empty[Long, ChainLength]

    def aaa(n: Long): Unit = aaa(List(n))

    def aaa(ls: List[Long]): Unit = {
      //println(s"ls=$ls\tmap=$map")
      ls match {
        case l :: t if l > MAX         => map ++= t.map(_ -> 0).toMap
        case l :: t if map.contains(l) => map ++= t.map(_ -> 0).toMap
        case l :: t if t.contains(l)   =>
          val (chain, nothing) = t.splitAt(t.indexOf(l) + 1)
          println(s"l=$l\tt=$t\tchain=$chain\tnothing=$nothing")
          map ++= chain.map(_ -> chain.length).toMap ++ nothing.map(_ -> 0).toMap
        case l :: t                    => aaa(LongOps.properDivisorsSum(l) :: ls)
        case _                         => ???
      }

    }

    def solve() = {
      val ls = LongOps.streamFrom(1).take(MAX)
      ls foreach aaa
      val (len, n) = map.toIterator.map { case (k, v) => (v, -1 * k) }.max
      println(s"(len, n)=($len, $n)")
      n * -1
    }
  }

  // 15065ms
  object Attempt3 {
    type ChainLength = Int
    val map = mutable.Map.empty[Long, ChainLength]

    def findChain(ls: List[Long]): (List[Long], List[Long]) = ls match {
      case l :: t if l > MAX | map.contains(l) => (List.empty[Long], t)
      case l :: t if t.contains(l)             => t.splitAt(t.indexOf(l) + 1)
      case l :: t                              => findChain(LongOps.properDivisorsSum(l) :: ls)
    }

    def solve() = {
      val ls = LongOps.streamFrom(1).take(MAX / 50)
      ls.foreach { l =>
        val (chain, nothing) = findChain(List(l))
        map ++= chain.map(_ -> chain.length).toMap ++ nothing.map(_ -> 0).toMap
      }

      val (len, n) = map.toIterator.map { case (k, v) => (v, -1 * k) }.max
      println(s"(len, n)=($len, $n)")
      n * -1
    }
=======
import scalaz.Memo

object Wip {

  val MAX = 1000000

  // TODO move to common - BEGIN

  object IntOps {
    //from pep-010
    def isPrime(n: Int): Boolean = BigInt(n).isProbablePrime(5)

    //from pep-021
    def isPowerOf2(n: Int): Boolean = (n > 0) && (n & (n - 1)) == 0

    val properDivisorsSum: Int => Int = Memo.mutableHashMapMemo {
      case n if isPrime(n)    => 1
      case n if isPowerOf2(n) => n - 1
      case n                  => (1 to n / 2).filter(n % _ == 0).sum
    }

    def isPerfectNumber(n: Int) = properDivisorsSum(n) == n

    def isAmicable(n: Int) = {
      val s = properDivisorsSum(n)
      s != n && properDivisorsSum(s) == n
    }
  }

  object LongOps {

    def isPrime(n: Long): Boolean = BigInt(n).isProbablePrime(5)

    def isPowerOf2(n: Long): Boolean = (n > 0) && (n & (n - 1)) == 0

    val properDivisorsSum: Long => Long = Memo.mutableHashMapMemo {
      case n if isPrime(n)    => 1
      case n if isPowerOf2(n) => n - 1
      case n                  => Iterator.from(1).takeWhile(_ <= n / 2).filter(n % _ == 0).sum
    }

    def isPerfectNumber(n: Long) = properDivisorsSum(n) == n

    def isAmicable(n: Long) = {
      val s = properDivisorsSum(n)
      s != n && properDivisorsSum(s) == n
    }
  }

  // TODO move to common - END

  /*
  Interestingly the sum of the proper divisors of 220 is 284 and the sum of the proper divisors of 284 is 220, forming a chain of two numbers.
  For this reason, 220 and 284 are called an amicable pair.

  Perhaps less well known are longer chains. For example, starting with 12496, we form a chain of five numbers:

  12496 → 14288 → 15472 → 14536 → 14264 (→ 12496 → ...)

  Since this chain returns to its starting point, it is called an amicable chain.

  Find the smallest member of the longest amicable chain with no element exceeding one million.
   */
  object Attempt1 {

    type ChainLength = Int
    type Step = Int

    def aaa(n: Int): Option[ChainLength] = aaa(n, 0)

    def aaa(n: Int, i: Step): Option[ChainLength] = {
      // when > 1M     : stop [X]
      // when == start : stop [V] returning the chain length
      ???
    }

    val

    // TODO fold peraphs?
    // TODO monoid peraphs?

    def solve() = (1 to MAX).map(IntOps.properDivisorsSum).head
>>>>>>> Stashed changes
  }

}
