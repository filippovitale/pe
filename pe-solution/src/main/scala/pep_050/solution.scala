package pep_050

import common.{IntOps, LongOps}
import scalaz.Memo

object solution {
  val primes = (2 until 10000).filter(IntOps.isPrime).to[Array]

  val sumDP: ((Int, Int)) => Long = Memo.mutableHashMapMemo {
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
    case (_, s: Long) => LongOps.isPrime(s)
  } sortBy {
    case (t, _) => t
  }).last // (t, s)

  def solve(): String = {
    val dt: IndexedSeq[(Int, Int)] = for {
      d <- primes.indices
      r <- d + 1 to primes.length
      t = r - d
    } yield (d, t)

    findLongest(dt)._2.toString
  }
}
