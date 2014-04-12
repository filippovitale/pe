package pep_074

import pep_050.Memo

object Wip {

  val fact = Map[Char, Long](
    '0' -> 1,
    '1' -> 1,
    '2' -> 2,
    '3' -> 6,
    '4' -> 24,
    '5' -> 120,
    '6' -> 720,
    '7' -> 5040,
    '8' -> 40320,
    '9' -> 362880
  )

  def sumFact(s: String): String = s.map(fact).sum.toString

  //  def factChainLength(s: String): Int = Iterator.iterate(s)(sumFact).drop(1).takeWhile(_ != s).length + 1

  lazy val factChainLength: Memo[String, Int] = Memo {
    s => Iterator.iterate(s)(sumFact).drop(1).takeWhile(_ != s).length + 1
  }

}
