package pep_042

import scala.collection.immutable._

object wip {

  val filename = "src/pep_042/words.txt"
  val words = io.Source.fromFile(filename).mkString.trim.split(",").map(_.drop(1).dropRight(1)).toStream

  val charToNumber: Map[Char, Int] = ('A' to 'Z').map(c => (c, c.toInt - '@'.toInt)).toMap

  def wordValue(word: String): Int = word.map(charToNumber).sum

  val triangleNumbers: Stream[Int] = {
    def loop(v: Int): Stream[Int] = {
      val n = v + 1
      ((v * n) / 2) #:: loop(n)
    }
    loop(1)
  }

  def solve(words: Seq[String] = words): Int = {
    val triangleNumbersMap = triangleNumbers.takeWhile(_ <= words.map(wordValue).max).toSet
    words.map(wordValue).count(triangleNumbersMap.contains)
  }

}
