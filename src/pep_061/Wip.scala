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

  val atch = th(f3t, f5h)
  val ctbh = th(f5t, f4h)
  val btah = th(f4t, f3h)

  for {
    (ack, acs) <- th(f3t, f5h)
    a1 <- ack
    c2 <- acs
    (cbk, cbs) <- th(f5t, f4h)
    c1 <- cbk
    if c1 == c2
    b2 <- cbs
    (bak, bas) <- th(f4t, f3h)
    b1 <- bak
    if b1 == b2
    a2 <- bas
    if a1 == a2
  } yield (a1, b1, c1) // List((2556,1225,5612))

  /*
    1)
    ct == ah
    at == bh
    bt == ch

    2)
    bt == ah
    at == ch
    ct == bh
  */

  for {
    (a, b, c) <- Seq((f3, f5, f4), (f3, f4, f5))
    ah = new Formulæ(a).groupByHead
    at = new Formulæ(a).groupByTail
    bh = new Formulæ(b).groupByHead
    bt = new Formulæ(b).groupByTail
    ch = new Formulæ(c).groupByHead
    ct = new Formulæ(c).groupByTail

    (ack, acs) <- th(at, ch)
    a1 <- ack
    c2 <- acs
    (cbk, cbs) <- th(ct, bh)
    c1 <- cbk
    if c1 == c2
    b2 <- cbs
    (bak, bas) <- th(bt, ah)
    b1 <- bak
    if b1 == b2
    a2 <- bas
    if a1 == a2
  } yield ((a1, b1, c1), a1 + b1 + c1)


  val f: Vector[Int => Int] = Vector(f3, f4, f5, f6, f7, f8)

  for {
    (a, b, c) <- Seq(
      (f(0), f(1), f(2)),
      (f(0), f(2), f(1)))
    ah = new Formulæ(a).groupByHead
    at = new Formulæ(a).groupByTail
    bh = new Formulæ(b).groupByHead
    bt = new Formulæ(b).groupByTail
    ch = new Formulæ(c).groupByHead
    ct = new Formulæ(c).groupByTail

    (ack, acs) <- th(at, ch)
    a1 <- ack
    c2 <- acs
    (cbk, cbs) <- th(ct, bh)
    c1 <- cbk
    if c1 == c2
    b2 <- cbs
    (bak, bas) <- th(bt, ah)
    b1 <- bak
    if b1 == b2
    a2 <- bas
    if a1 == a2
  } yield (a1, b1, c1) // List((2556,1225,5612), (8515,2185,1521))

  /*

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
