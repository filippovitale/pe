package pep_076

import common.Memo
import Stream._
import scala.collection.mutable

object Wip {

  object Solve1 {

    type Way = List[Int] // in decreasing order

    // (math.ceil(x / 2.0).toInt until x).map(i => List(i, x - i)).toSet
    def pairSet(x: Int): Set[Way] = for {
      h <- (math.ceil(x / 2.0).toInt until x).toSet[Int]
      l = x - h
    } yield List(h, l)

    def solveOneLevel(xs: Way): Set[Way] = for {
      i <- xs.indices.toSet[Int]
      k <- solve(xs(i))
    } yield xs.patch(i, k, 1).sorted.reverse

    lazy val solveRecursively: Memo[Set[Way], Set[Way]] = Memo { case xs =>
      val b = xs.flatMap(solveOneLevel) -- xs
      xs ++ (if (b.nonEmpty) solveRecursively(b) else Set())
    }

    lazy val solve: Memo[Int, Set[Way]] = Memo { case x => solveRecursively(pairSet(x)) }

    def show(ws: Set[Way]): List[String] =
      ws.map(_.mkString(" + ")).toList.sorted.reverse

    def count(x: Int): Int = solve(x).size
  }

  object Solve2 {

    def combination(a: Int, b: Int): Int =
      if (a == 1 || b == 1) 1
      else (0 /: (0 until a / b + 1)) { case (acc, i) => acc + combination(a - b * i, b - 1) }

    def count(x: Int): Int = combination(x, x - 1)
  }

  object Solve3 {
    val n = 100
    val way = mutable.IndexedSeq.fill(n + 1)(1)

    for {
      i <- 2 to n
      j <- i to n
    } way(j) += way(j - i)

    def count(x: Int): Int = way(x) - 1
  }

  object Solve4 {

    lazy val seq: Stream[Int] = 0 #:: 0 #:: seq map {
      case n => n + 1 // TODO
    }

    def count(x: Int): Int = seq(x)
  }

}
