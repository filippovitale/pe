package pep_047

object solution {

  def solve(n: Int = 4) = (1 to 1000000)
    .map(PrimeFactors.factorize(_).distinct.size)
    .sliding(n)
    .zipWithIndex
    .filter(a => a._1 == Vector.fill(n)(n))
    .next()
    ._2 + 1

}

// Source: http://rosettacode.org/wiki/Prime_decomposition#Scala
object PrimeFactors {

  import annotation.tailrec
  import collection.parallel.mutable.ParSeq

  def factorize(n: Long): List[Long] = {
    @tailrec
    def factors(tuple: (Long, Long, List[Long], Int)): List[Long] = tuple match {
      case (1, _, acc, _) => acc
      case (n, k, acc, _) if n % k == 0 => factors((n / k, k, acc ++ ParSeq(k), Math.sqrt(n / k).toInt))
      case (n, k, acc, sqr) if k < sqr => factors((n, k + 1, acc, sqr))
      case (n, k, acc, sqr) if k >= sqr => factors((1, k, acc ++ ParSeq(n), 0))
    }

    factors((n, 2, List[Long](), Math.sqrt(n).toInt))
  }

  def sieve(nums: Stream[Int]): Stream[Int] = Stream.cons(nums.head, sieve(nums.tail filter (_ % nums.head != 0)))

  // An infinite stream of primes, lazy evaluation and memo-ized
  val oddPrimes = sieve(Stream.from(3, 2))

  def primes = sieve(2 #:: oddPrimes)
}
