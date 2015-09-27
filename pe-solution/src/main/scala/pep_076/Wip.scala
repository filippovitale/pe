package pep_076

import pep_050.Memo

object Wip {

  type Way = List[Int] // in decreasing order

  // (math.ceil(x / 2.0).toInt until x).map(i => List(i, x - i)).toSet
  def aaa(x: Int): Set[Way] = for {
    h <- (math.ceil(x / 2.0).toInt until x).toSet[Int]
    l = x - h
  } yield List(h, l)

  def bbb(xs: Way): Set[Way] = for {
    i <- xs.indices.toSet[Int]
    k <- fff(xs(i))
  } yield xs.patch(i, k, 1).sorted.reverse

  lazy val eee: Memo[Set[Way], Set[Way]] = Memo { case xs =>
    val b = xs.flatMap(bbb) -- xs
    xs ++ (if (b.nonEmpty) eee(b) else Set())
  }

  lazy val fff: Memo[Int, Set[Way]] = Memo { case x => eee(aaa(x)) }

  def show(ws: Set[Way]): List[String] =
    ws.map(_.mkString(" + ")).toList.sorted.reverse

  import scalaz._, Scalaz._

  def solve1(x: Int): Set[Way] = aaa(x).flatMap(bbb) |> eee

  def solve2(x: Int): Set[Way] = aaa(x) |> eee

  def solve3(x: Int): Set[Way] = fff(x)
}
