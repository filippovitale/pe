package pep_076

import scalaz._, Scalaz._

object Wip {

  def ways(n: Int): Seq[(Int, Int)] = {
    for {
      a <- 1 until n
      b = n - a
    } yield (a, b)
  }

  // Seq("aaa bbb", "ccc").flatMap(_.split("\\W+"))
  // List(aaa, bbb, ccc)

  // let's try with oedis for 1 -> 0, 2 -> 1, 3 -> 2, 4 -> 5, 5 -> 6
  // http://oeis.org/A002133

  // sum(
  //     sum(
  //         x^(i+j) / [(1-x^i)(1-x^j)],
  //         j=1..i-1
  //     ), i=1..infinity)

  def parts(n: Int): Seq[Seq[Int]] = {
    val l1 = for {
      a <- 1 until n
      b = n - a
    } yield Seq(a, b)

    l1.map(l => (l, l.tail).zipped.forall(_ <= _))
    //...
    Seq()
  }

  // http://oeis.org/A002620
  //  (2*n^2-1+(-1)^(n))/8
  //  x^2/((1-x)^2*(1-x^2))
  //  a(n) = 2*a(n-1) - 2*a(n-3) + a(n-4)
  //  a(n) = a(n-1) + a(n-2) - a(n-3) + 1 [with a(-1) = a(0) = a(1) = 0]
  //  a(2k) = k^2, a(2k-1) = k(k-1).

  // a(2 * 50) = 50 * 50

  def wip1() = {
    // works for 1 to 2
    val n = 5

    val l = for {
      a <- 1 until n
      b = n - a
    } yield Seq(a, b)

    l.partition(_.head < 3)
    // Seq(Vector(List(1, 4), List(2, 3)),Vector(List(3, 2), List(4, 1)))

    l.flatMap(_.tail)
    // Seq(Vector(List(1, 4), List(2, 3)),Vector(List(3, 2), List(4, 1)))
  }

  def wip2(n: Int) = {
    val l = for {
      b <- 1 until n
      a = n - b
    } yield Seq(a, b)
    // l works for 1 to 2

    // partition
    l.partition(xs => xs.head < xs.last)

    // TODO

    // for every b > 1 recur
    // eg (1,2) -> [(1,1,1)]

    // better than (filter not monotonic List) is

    //.flatMap(_.tail)
  }

  type Way = List[Int] // in decreasing order

  // TODO from Way to Tuples
  def aaa(x: Int): Set[Way] =
    (math.ceil(x / 2.0).toInt until x).map(i => List(i, x - i)).toSet

  // TODO memoize
  def bbb(xs: Way): Set[Way] = {
    def ccc(i: Int): Set[Way] =
      aaa(xs(i)).map(xs.patch(i, _, 1).sorted.reverse)

    Set(xs) ++ xs.indices.toSet.flatMap(ccc)
  }

  def show(ws: Set[Way]): List[String] =
    ws.map(_.mkString(" + ")).toList.sorted.reverse

  def solve(x: Int): List[String] = aaa(x).flatMap(bbb) |> show


}
