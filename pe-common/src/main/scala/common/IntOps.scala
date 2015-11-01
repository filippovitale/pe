package common

object IntOps {

  def streamFrom(n: Int): Stream[Int] = Stream from n

  def iteratorFrom(n: Int): Iterator[Int] = Iterator from n

  def streamSequence1(a: Int, f: Int => Int): Stream[Int] = a #:: streamSequence1(f(a), f)

  def streamSequence2(ab: (Int, Int), fg: ((Int, Int) => Int, (Int, Int) => Int)): Stream[(Int, Int)] = ab match {
    case (a, b) => (a, b) #:: streamSequence2((fg._1(a, b), fg._2(a, b)), fg)
  }

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
