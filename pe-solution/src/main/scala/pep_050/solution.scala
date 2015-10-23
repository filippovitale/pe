package pep_050

import common.Memo
import common.Prime.isPrime

object solution {
  val primes = (2 until 10000).filter(isPrime(_)).to[Array]

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
    case (_, s: Long) => isPrime(s)
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
