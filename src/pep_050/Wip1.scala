package pep_050

import scala.collection.immutable.IndexedSeq
import scala.collection.mutable
import scala.annotation.tailrec

object Wip1 {
  def main(args: Array[String]) {

    val maxPrime = 100 // TODO 0 * 1000
    val primes = (2 until maxPrime).filter(BigInt(_).isProbablePrime(5)).to[IndexedSeq]

    def isPrime(n: Int): Boolean = primes.contains(n)

    case class Memo[A, B](f: A => B) extends (A => B) {
      private val cache = mutable.Map.empty[A, B]

      def apply(x: A) = {
        println("x found: " + cache.contains(x))
        cache getOrElseUpdate(x, f(x))
      }
    }

    case class IndexLength(i: Int, l: Int)

    val sumIndexLength: Memo[IndexLength, Int] = Memo {
      case IndexLength(_, 0) => 0 // TODO remove at the end
      case IndexLength(i, 1) => primes(i)
      case IndexLength(i, l) => primes(i) + sumIndexLength(IndexLength(i + 1, l - 1))
    }

    val primesSums = for {
      b <- 0 until primes.size // TODO - longest
      l <- 1 to primes.size - b
      primesSum = sumIndexLength(IndexLength(b, l))
      if isPrime(primesSum)
    } yield (l, primesSum)




    // TODO iterate until primes.size - longest ?
    def sumFrom(b: Int): Seq[(Int, Int)] = for {
      l <- 1 to primes.size - b
    } yield (l, sumIndexLength(IndexLength(b, l)))

    def addToAPrime(ps: Iterator[Int], n: Int): Boolean = primes.contains(ps.take(n).sum)


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
}

object Wip2 {
  def main(args: Array[String]) {
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
  }
}

object Wip3 {
  def main(args: Array[String]) {
    //    val pps = ps.toList
    //    while (ps.hasNext) {
    //      val p = ps.next()
    //      find longest seq
    //      (22 to ps.size).map(pps.take(_))
    //
    //      (2 to ps.size).map(pps.take(_)).map(_.sum).take(10).filter(primes.contains(_)) // Vector(5, 17, 41)
    //      (22 to pps.size).map(n => (ps.take(n).sum -> n)).filter(n => primes.contains(n._1)).sortBy(_._2).last // (958577,536) <-- solution must be >536
    //
    //      (0 to 1000).toStream.takeWhile(_ => sum < 1000).foreach(i => sum += i)
    //    }
  }
}

object Wip4 {
  def main(args: Array[String]) {
    val maxPrime = 40
    val primes = (2 until maxPrime).filter(BigInt(_).isProbablePrime(5)).to[scala.collection.immutable.IndexedSeq]

    val comb = for {
      d <- 0 until primes.size
      r <- d + 1 to primes.size
      t = r - d
    } yield ((d, r, t), primes.drop(d).take(t))

    comb.map(x => x._1.toString() + " " + x._2.mkString(" ")).foreach(println)
  }
}
