package common

object LongOps {

  def streamFrom(n: Long): Stream[Long] = n #:: streamFrom(n + 1)

  def iteratorFrom(n: Long): Iterator[Long] = streamFrom(n).toIterator

  def streamSequence1(ab: Long, f: Long => Long): Stream[Long] = ab match {
    case a => a #:: streamSequence1(f(a), f)
  }

  def streamSequence2(ab: (Long, Long), fg: ((Long, Long) => Long, (Long, Long) => Long)): Stream[(Long, Long)] = ab match {
    case (a, b) => (a, b) #:: streamSequence2((fg._1(a, b), fg._2(a, b)), fg)
  }

  //from pep-010
  def isPrime(n: Long, c: Int): Boolean = BigInt(n) isProbablePrime c

  def isPrime(n: Long): Boolean = BigInt(n) isProbablePrime 10

  val isPrimeMemo: Long => Boolean = scalaz.Memo.mutableHashMapMemo(isPrime)

  def primeStreamFrom(n: Long): Stream[Long] = streamFrom(n).filter(isPrime)

  //from pep-021
  def isPowerOf2(n: Long): Boolean = (n > 0) && (n & (n - 1)) == 0

  val properDivisorsSum: Long => Long = scalaz.Memo.mutableHashMapMemo {
    case n if isPrime(n)    => 1
    case n if isPowerOf2(n) => n - 1
    case n                  => Iterator.from(1).takeWhile(_ <= n / 2).filter(n % _ == 0).sum
  }

  def isPerfectNumber(n: Long) = properDivisorsSum(n) == n

  def isAmicable(n: Long) = {
    val s = properDivisorsSum(n)
    s != n && properDivisorsSum(s) == n
  }

  // reverse order
  def reverseDigits(n: Long) = Iterator.iterate(n)(_ / 10L).takeWhile(_ != 0L).map(_ % 10L)

  def digits(n: Long, base: Long = 10) = Iterator.iterate(n)(_ / base).takeWhile(_ != 0L).map(_ % base)

  def toHex(n: Long): String = digits(n, 16).map(_.toInt).map("0123456789ABCDEF".toVector.apply).toList.reverse.mkString
}
