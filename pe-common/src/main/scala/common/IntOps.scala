package common

object IntOps {
  //from pep-010
  def isPrime(n: Int): Boolean = BigInt(n) isProbablePrime 5

  //from pep-021
  def isPowerOf2(n: Int): Boolean = (n > 0) && (n & (n - 1)) == 0

  val properDivisorsSum: Int => Int = scalaz.Memo.mutableHashMapMemo {
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
