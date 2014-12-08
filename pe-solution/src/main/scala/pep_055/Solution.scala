package pep_055

object Solution {

  val MaxIterations = 50

  def digitReverse(n: BigInt) = BigInt(n.toString().toSeq.reverse.mkString)

  def isPalindrome(n: BigInt): Boolean = {
    val ns = n.toString().toSeq
    ns == ns.reverse
  }

  def isLychrel(n: Int): Boolean = {
    var acc: BigInt = n
    for (_ <- 1 to MaxIterations; if !isPalindrome(acc)) acc += digitReverse(acc)
    !isPalindrome(acc)
  }

  def solve(max: Int = 10000) = 0 until max count isLychrel

}
