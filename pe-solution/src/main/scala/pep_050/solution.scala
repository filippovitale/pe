package pep_050

import scala.collection.mutable
import scala.collection.immutable.IndexedSeq

case class Memo[A, B](f: A => B) extends (A => B) {
  private val cache = mutable.Map.empty[A, B]

  def apply(x: A) = cache getOrElseUpdate(x, f(x))
}

object PrimesSeq {
  def apply(maxPrime: Int) = 2 until maxPrime filter (isPrime(_))

  def isPrime(n: Long): Boolean = BigInt(n).isProbablePrime(5)
}

object solution {

  val primes = PrimesSeq(10000).to[Array]

  lazy val sumDP: Memo[(Int, Int), Long] = Memo {
    case (d, 1) => primes(d)
    case (d, t) => primes(d) + sumDP((d + 1, t - 1))
  }

  def findLongest(dt: IndexedSeq[(Int, Int)]) = (dt
    filter {
    case (_, t) => t > 21
  } map {
    case (d, t) => (t, sumDP((d, t)))
  } filter {
    case (_, s: Long) => s < 1000000
  } filter {
    case (_, s: Long) => PrimesSeq.isPrime(s)
  } sortBy {
    case (t, _) => t
  }).last // (t, s)

  def solve(): String = {
    val dt: IndexedSeq[(Int, Int)] = for {
      d <- 0 until primes.size
      r <- d + 1 to primes.size
      t = r - d
    } yield (d, t)

    findLongest(dt)._2.toString
  }
}
