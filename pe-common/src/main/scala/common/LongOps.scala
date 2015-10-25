package common

object LongOps {

  def streamFrom(n: Long): Stream[Long] = n #:: streamFrom(n + 1)

  def iteratorFrom(n: Long): Iterator[Long] = streamFrom(n).toIterator

  //from pep-010
  def isPrime(n: Long): Boolean = BigInt(n) isProbablePrime 5

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
}
