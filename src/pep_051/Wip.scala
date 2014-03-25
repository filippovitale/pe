package pep_051

object Wip {

  val primes2digit = PrimesSeq(2).map(_.toString).to[Set]
  val primes5digit = PrimesSeq(5).map(_.toString).to[Set]

  //  val maps = for {
  //    i <- 0 to 1
  //  //  } yield primes2digit.toSeq.sorted.groupBy(_(i)) //.map({case (c, s) => (c, s.length)}).filter({case (_, l) => l > 3})
  //  } yield primes2digit.groupBy(_(i)) //.map({case (c, s) => (c, s.length)}).filter({case (_, l) => l > 3})

  //  maps.flatten.map({
  //    case (c, s) => s
  //  }).sortBy(_.length).last.head

  for {m <- for {i <- 0 to 1} yield pep_051.PrimesSeq(2).map(_.toString).groupBy(_(i))} yield m

  //  def gb(s: String): String = {
  //    s(0) :: s(1) :: s(2) :: s(3) :: s(4)
  //  }
  //
  //  for {m <- primes5digit.groupBy(gb)} yield m

  val R = """1(\d)1""".r
  "1111" match {
    case R(a) => true
    case _ => false
  }

  (for {
    a <- 0 to 4
    b <- 0 to 4
    if b > a
    c <- 0 to 4
    if c > b
  } yield (a, b, c)).foreach(println)
  //  (0,1,2)
  //  (0,1,3)
  //  (0,1,4)
  //  (0,2,3)
  //  (0,2,4)
  //  (0,3,4)
  //  (1,2,3)
  //  (1,2,4)
  //  (1,3,4)
  //  (2,3,4)

  //  val xs = Seq(56003, 56113, 56333, 56443, 56663, 56773, 56993).map(_.toString).to[Set]
  val xs = Seq(56003, 56013, 56113, 56333, 56443, 56663, 56773, 56993).map(_.toString)

  (for {
    a <- 0 to 4
    b <- 0 to 4
    if b > a
    c <- 0 to 4
    if c > b
  } yield xs.groupBy(n => (n(a) + n(b) + n(c)))).head

  for {
    a <- 0 to 4
    b <- 0 to 4
    if b > a
    c <- 0 to 4
    if c > b
  } yield xs.groupBy(n => (n(a) + n(b) + n(c))).filter({
    case (_, s) => s.size > 1
  }).map({
    case (_, s) => s.head
  })

  val patterns = for {
    a <- 0 to 4
    b <- 0 to 4
    if b > a
    c <- 0 to 4
    if c > b
  } yield (n: String) => n(a) + n(b) + n(c)

  for {
    p <- patterns
  } yield xs.groupBy({
    p
  }).filter({
    case (_, s) => s.size > 1
  }).map({
    case (_, s) => s.head
  })

  val maps = for {
    p <- patterns
  } yield xs.groupBy(p)

  maps.map({
    m => m.values.map(l => (l.head, l.size))
  }).flatten.filter({
    case (_, s) => s > 1
  })
  //  }).head._1

//  def countPrimes(p: String = "56**3", primes: Set[String] = xs): Int = {
//    1
//  }

  val patterns2 = for {
    a <- 0 to 4
    b <- 0 to 4
    if b > a
    c <- 0 to 4
    if c > b
    s = (0 to 4).to[Set] - a - b - c
    x = s.head
    y = (s - x).head
  } yield ((n: String) => (n(a) + n(b) + n(c)), (n: String) => n(x) + n(y))

val maps2 = for {
    p <- patterns2
  } yield xs.groupBy(p._1).map({
      case (_, m) => m // TODO filter using p._2 !!!!! same digit!!!! Set(xxxx).size == 1
    })
  maps2.flatten

  maps2.map({
    m => m.values.map(l => (l.head, l.size))
  }).flatten.filter({
    case (_, s) => s > 3
  })

}
