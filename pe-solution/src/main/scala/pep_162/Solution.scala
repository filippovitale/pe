package pep_162

import common.LongOps.toHex
import scalaz.syntax.id._
import spire.implicits._

object Solution {

  import Attempt1.{s4 => s}

  def solve() = (3 to 16).map(s).sum |> toHex
}

object Attempt1 {

  val s3 = "01A".permutations.filterNot(_.startsWith("0"))

  // n=8 – 88583040 – 21132ms
  def s1(n: Int) = for {
    d <- "0123456789ABCDEF".combinations(n - 3)
    //p <- List('0','1','A', d).permutations
    p <- s"01A$d".permutations
    if p.head != '0'
  } yield p.mkString

  val digits = "0123456789ABCDEF".toList
  val a01 = "A01".toList

  // n=8 – 88583040 – 18043ms
  def s2(n: Int) = for {
    d <- digits.combinations(n - 3)
    p <- (a01 ++ d).permutations
    if p.head != '0'
  } yield ()

  // n=4..8 – 258,9264,242250,5069610,88583040 – 30703ms
  def s3(n: Int) = {
    (for {
      d <- digits.combinations(n - 3)
      p <- (a01 ++ d).permutations
      if p.head != '0'
    } yield ()).size
  }

  def s4(n: Int): Long = {
    val a = 15 * 16.toBigInt().pow(n - 1)
    val b = 43 * 15.toBigInt().pow(n - 1)
    val c = 41 * 14.toBigInt().pow(n - 1)
    val d = 13.toBigInt().pow(n)
    (a - b + c - d).toLong
  }

}
