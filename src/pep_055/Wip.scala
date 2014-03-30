package pep_055

import pep_050.Memo
import scala.collection.mutable

object Wip {

  val MaxIterations = 50
//  val lychrelNumbers = mutable.Map.empty[Long, Boolean]
//  def check(x: Long) = lychrelNumbers getOrElseUpdate(x, f(x))
//  def add(x: Long) = lychrelNumbers getOrElseUpdate(x, f(x))

  def isLychrel(n: Int): Boolean = {
    var acc: Long = n
    for (i <- 1 to MaxIterations; if !isPalindrome(acc)) acc += digitReverse(acc)
    isPalindrome(acc)
  }

  def digitReverse(n: Long) = n.toString.toSeq.reverse.mkString.toInt

  def isPalindrome(n: Long): Boolean = {
    val ns = n.toString.toSeq
    ns == ns.reverse
  }

  //  lazy val aaa: Memo[(Long, Int), Boolean] = Memo {
  //    case (n, MaxIterations) => // isPalindrome(n)
  //    case (n, i) => // TODO
  //  }

  def solve(max: Int = 10000) = 0 until max count isLychrel

}
