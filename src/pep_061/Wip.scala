package pep_061

import scala.math._

object Wip {

  val Digit = 4
  val (min, max) = (pow(10, Digit - 1).toInt, pow(10, Digit).toInt)

  val p3 = Stream.from(1).map(n => (n * (n + 1)) / 2).dropWhile(_ < min).takeWhile(_ < max)
  val p4 = Stream.from(1).map(n => n * n).dropWhile(_ < min).takeWhile(_ < max)
  val p5 = Stream.from(1).map(n => (n * (3 * n + 1)) / 2).dropWhile(_ < min).takeWhile(_ < max)
  val p6 = Stream.from(1).map(n => n * (2 * n + 1)).dropWhile(_ < min).takeWhile(_ < max)
  val p7 = Stream.from(1).map(n => (n * (5 * n - 3)) / 2).dropWhile(_ < min).takeWhile(_ < max)
  val p8 = Stream.from(1).map(n => n * (3 * n - 2)).dropWhile(_ < min).takeWhile(_ < max)

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

  }
}
