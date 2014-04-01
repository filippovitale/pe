package pep_061


object Wip {

  val Digit = 4
  val (min, max) = (scala.math.pow(10, Digit - 1).toInt, scala.math.pow(10, Digit).toInt)


  case class Formulæ(f: Int => Int) {
    val formulæ: Stream[String] = Stream.from(1).map(f).dropWhile(_ < min).takeWhile(_ < max).map(_.toString)

    val groupByHead: Map[String, Stream[String]] = formulæ.groupBy(_.take(Digit / 2))
    val groupByTail: Map[String, Stream[String]] = formulæ.groupBy(_.drop(Digit / 2))
  }

  val f3 = (n: Int) => (n * (n + 1)) / 2
  val f4 = (n: Int) => n * n
  val f5 = (n: Int) => (n * (3 * n + 1)) / 2
  val f6 = (n: Int) => n * (2 * n + 1)
  val f7 = (n: Int) => (n * (5 * n - 3)) / 2
  val f8 = (n: Int) => n * (3 * n - 2)

  val f3h = new Formulæ(f3).groupByHead
  val f3t = new Formulæ(f3).groupByTail
  val f4h = new Formulæ(f4).groupByHead
  val f4t = new Formulæ(f4).groupByTail
  val f5h = new Formulæ(f5).groupByHead
  val f5t = new Formulæ(f5).groupByTail

  def th(t: Map[String, Stream[String]], h: Map[String, Stream[String]]) = for {
    (tk, ts) <- t
    hs <- h.get(tk)
  } yield (ts, hs)

  val atbh = th(f3t, f4h)
  val btch = th(f4t, f5h)
  val ctah = th(f5t, f3h)

  for {
    (abk, abs) <- atbh
    a <- abk
    b <- abs
    (bck, bcs) <- btch
    (cak, cas) <- ctah
  } yield ???

  /*

  1)
    ct == ah
    at == bh
    bt == ch

  2)
    bt == ah
    at == ch
    ct == bh

  http://mathworld.wolfram.com/CircularPermutation.html

  n = 3 --> 2 free circular permutations
  n = 4 --> ? free circular permutations
  n = 5 --> ? free circular permutations
  n = 6 --> ? free circular permutations

  1)
    ft == ah
    at == bh
    bt == ch
    ct == dh
    dt == eh
    et == fh


   */


  // p3.size *  p4.size *  p5.size *  p6.size *  p7.size * p8.size = 116523008

  def areCyclic(s1: Int, s2: Int): Boolean = s1.toString.drop(Digit / 2) == s2.toString.take(Digit / 2)

  def solve() = {
    //    val wip1 = for {
    //      a <- p3.dropWhile(_ < min).takeWhile(_ < max)
    //      as = a.toString
    //      b <- p4.dropWhile(_ < min).takeWhile(_ < max)
    //      bs = b.toString
    //
    //      if areCyclic(as, bs)
    //
    //      c <- p5.dropWhile(_ < min).takeWhile(_ < max)
    //      cs = c.toString
    //
    //      if areCyclic(bs, cs)
    //
    //      d <- p6.dropWhile(_ < min).takeWhile(_ < max)
    //      ds = d.toString
    //
    //      if areCyclic(cs, ds)
    //
    //      e <- p7.dropWhile(_ < min).takeWhile(_ < max)
    //      es = e.toString
    //
    //      if areCyclic(ds, es)
    //
    //      f <- p8.dropWhile(_ < min).takeWhile(_ < max)
    //      fs = f.toString
    //
    //      if areCyclic(es, fs)
    //      if areCyclic(fs, as)
    //
    //    } yield (a, b, c, d, e, f)
    //    // None

    /*
        val wip2 = for {
          a <- p3
          b <- p4
          if areCyclic(a, b)
          c <- p5
          if areCyclic(b, c)
          if areCyclic(c, a)

        //      d <- p6
        //      e <- p7
        //      f <- p8
        } yield (a, b, c) //, d, e, f)
        // looking for 8128, 2882, 8281
        // None
    */

  }
}
