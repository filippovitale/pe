package pep_074

import pep_050.Memo

object Solution {

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

  lazy val nextFact: Memo[String, String] = Memo(_.map(fact).sum.toString)

  def cycleCount(s: String, set: Set[String] = Set(), list: List[String] = List()): Seq[(String, Int)] = {
    val next = nextFact(s)
    val nextSet = set + s
    val nextList = s :: list

    if (nextSet.contains(next)) {
      val cycle = next :: nextList.takeWhile(_ != next)
      val cycleLength = cycle.length
      val rest = nextList.drop(cycleLength).zipWithIndex.map {
        case (k, l) => (k, l + cycleLength + 1)
      }
      cycle.map(_ -> cycleLength) ::: rest
    } else {
      cycleCount(next, nextSet, nextList)
    }
  }

  val factChainLengths = scala.collection.mutable.Map.empty[String, Int]

  def solve() = {
    // TODO use getOrElseUpdate
    for (i <- 1 to 1000 * 1000; s = i.toString) {
      if (!factChainLengths.contains(s)) {
        cycleCount(s).foreach(factChainLengths += _)
      }
    }

    factChainLengths.count(_._2 == 60)
  }


}
