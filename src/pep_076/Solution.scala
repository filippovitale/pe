package pep_076

object Solution {

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
}
