package pep_074

import pep_050.Memo
import scala.collection.mutable

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

  //  def sumFact(s: String, set: Set[String]): (String, Set[String]) = {
  //    val next = s.map(fact).sum.toString
  //    (next, set + s + next)
  //  }

  //  def factChainLength(s: String): Int = Iterator.iterate(s)(sumFact).drop(1).takeWhile(_ != s).length + 1

  //  lazy val factChainLength: Memo[(String, Set[String]), Int] = Memo {
  //    s => Iterator.iterate((s, Set[String]()))(sumFact).drop(1).takeWhile(_ != s).length + 1
  //  }

  lazy val nextFact: Memo[String, String] = Memo(_.map(fact).sum.toString)

  // http://en.wikipedia.org/wiki/Cycle_detection#Brent.27s_algorithm
  def brent(f: (String) => String = nextFact, x0: String) = {
    var power = 1
    var λ = 1

    var tortoise = x0
    var hare = f(x0)

    while (tortoise != hare) {
      if (power == λ) {
        tortoise = hare
        power *= 2
        λ = 0
      }
      hare = f(hare)
      λ += 1

    }

    var μ = 0
    tortoise = x0
    hare = x0
    for (i <- 1 until λ) {
      hare = f(hare)
    }
    // The distance between the hare and tortoise is now λ

    // Next, the hare and tortoise move at same speed till they agree
    while (tortoise != hare) {
      tortoise = f(tortoise)
      hare = f(hare)
      μ += 1
    }

    (λ, μ)
  }

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

  // Tests

  // 145 → 145
  cycleCount("145") == Seq("145" -> 1)

  // 169 → 363601 → 1454 (→ 169)
  cycleCount("169") == cycleCount("363601", Set("169"), List("169"))
  cycleCount("169") == cycleCount("1454", Set("169", "363601"), List("363601", "169"))
  cycleCount("169") == Seq("169" -> 3, "1454" -> 3, "363601" -> 3)

  // 69 → 363600 → 1454 → 169 → 363601 (→ 1454)
  cycleCount("69") == List(("1454", 3), ("363601", 3), ("169", 3), ("363600", 4), ("69", 5))

  // 78 → 45360 → 871 → 45361 (→ 871)
  cycleCount("78") == List(("871", 2), ("45361", 2), ("45360", 3), ("78", 4))

  // 540 → 145 (→ 145)
  cycleCount("540") == List(("145", 1), ("540", 2))

  //def factChainLength(s: String): Int = Iterator.iterate(s)(sumFact).drop(1).takeWhile(_ != s).length + 1

  val factChainLengths = mutable.Map.empty[String, Int]

  def solve() = {
    for (i <- 1 to 10; s = i.toString) {
      if (!factChainLengths.contains(s)) { // TODO use getOrElseUpdate
        cycleCount(s).foreach(factChainLengths += _)
      }
    }

    factChainLengths.count(_._2 == 60)
  }


}
