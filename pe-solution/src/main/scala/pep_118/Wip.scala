package pep_118

import common.IntOps._

object Wip {

  object Attempt1 {

    val digits = (1 to 9).toSet

    // Set is Monadic but not a Monad :-(

    val p1: Set[Int] = for {
      n <- digits
      if isPrime(n)
    } yield n

    val p2: Set[Int] = for {
      a <- digits diff Set(2, 4, 5, 6, 8)
      b <- digits
      n = a + b * 10
      if isPrime(n)
    } yield n

    val p3: Set[Int] = for {
      a <- digits diff Set(2, 4, 5, 6, 8)
      b <- digits
      c <- digits
      n = a + b * 10 + c * 100
      if isPrime(n)
    } yield n

    val p4: Set[Int] = for {
      a <- digits diff Set(2, 4, 5, 6, 8)
      b <- digits
      c <- digits
      d <- digits
      n = a + b * 10 + c * 100 + d * 1000
      if isPrime(n)
    } yield n

    @deprecated("this is slower than isPrime!", "1.0")
    def divisibleBy3(d: Int*): Boolean = {
      var r = d.sum
      while (r > 10) {
        val t = r.toString.toList.map(_ - '0').sum
        r = t
      }
      Set(3, 6, 9).contains(r)
    }

    def solve() =
      s"p1=${p1.size}\n" +
        s"p2=${p2.size}\n" +
        s"p3=${p3.size}\n" +
        s"p4=${p4.size}\n" +
        s"$p2"

    //        s"p5=${p5.size}\n" +
    //        s"p6=${p6.size}\n" +
    //        s"p7=${p7.size}\n" +
    //        s"p8=${p8.size}\n"

    //.toList.sorted
    //s"p7=${p7.size}\n"
    //s"p8=${p8.size}\n"

    /*
WRONG

p1=4       p1=4        p1=4
p2=21      p2=21       p2=21
p3=128     p3=128      p3=128
p4=857     p4=857      p4=857
p5=6112    p5=6112     p5=6112
p6=45191   p6=45191    p6=45191
                       p7=345553
 – 2040ms   – 1873ms    – 10091ms
 */

  }

  object Attempt2 {
    // Set is Monadic but not a Monad :-(
    val digits = (1 to 9).toSet

    val p: Int => Set[Int] = scalaz.Memo.mutableHashMapMemo { i =>
      val ns = for {
        s <- digits.subsets(i)
        p <- s.toList.permutations
        n = (0 /: p)(_ * 10 + _)
        if isPrime(n)
      } yield n
      ns.toSet
    }

    def stats() =
      s"p1=${p(1).size}  \n" + // p1=4
        s"p2=${p(2).size}\n" + // p2=20
        s"p3=${p(3).size}\n" + // p3=83
        s"p4=${p(4).size}\n" + // p4=395
        s"p5=${p(5).size}\n" + // p5=1610
        s"p6=${p(6).size}\n" + // p6=5045
        s"p7=${p(7).size}\n" + // p7=12850
        s"p8=${p(8).size}\n" + // p8=23082
        s"p9=${p(9).size}\n" + // p9=0
        "                    " //  – 2810ms

    val partitions = Iterator(
      List(1, 1, 1, 1, 2, 3),
      List(1, 1, 1, 1, 5),
      List(1, 1, 1, 2, 2, 2),
      List(1, 1, 1, 2, 4),
      List(1, 1, 1, 3, 3),
      List(1, 1, 2, 2, 3),
      List(1, 1, 2, 5),
      List(1, 1, 3, 4),
      List(1, 2, 2, 2, 2),
      List(1, 2, 2, 4),
      List(1, 2, 3, 3),
      List(1, 3, 5),
      List(1, 4, 4)
    )

    def split(n: Int): List[Int] = Iterator.iterate(n)(_ / 10).takeWhile(_ != 0).map(_ % 10).toList

    def areDisjoint(ns: Int*): Boolean = {
      val ls = ns.flatMap(split)
      ls.size == ls.toSet.size
    }

    def solve() = {

      val aaa111123 = for {
        is <- partitions.take(1) // TODO .take(1) List(1, 1, 1, 1, 2, 3)
        a <- p(1)
        b <- p(1)
        if areDisjoint(a, b)
        if a < b
        c <- p(1)
        if areDisjoint(a, b, c)
        if b < c
        d <- p(1)
        if areDisjoint(a, b, c, d)
        if c < d
        e <- p(2)
        if d < e
        if areDisjoint(a, b, c, d, e)
        f <- p(3)
        if e < f
        if areDisjoint(a, b, c, d, e, f)
      } yield (a, b, c, d, e, f)

      aaa111123 foreach println
      "TODO"
      //      (5,2,7,3,89,461)
      //      (5,2,7,3,89,641)
      //      TODO – 298ms
    }
  }

  object Attempt3 {
    // Set is Monadic but not a Monad :-(
    val digits = (1 to 9).toSet

    val p: Int => Set[Int] = scalaz.Memo.mutableHashMapMemo { i =>
      val ns = for {
        s <- digits.subsets(i)
        p <- s.toList.permutations
        n = (0 /: p)(_ * 10 + _)
        if isPrime(n)
      } yield n
      ns.toSet
    }

    val partitions = List(
      List(4, 5),
      List(3, 3, 3),
      List(2, 3, 4),
      List(2, 2, 5),
      List(2, 2, 2, 3),
      List(1, 4, 4),
      List(1, 3, 5),
      List(1, 2, 3, 3),
      List(1, 2, 2, 4),
      List(1, 2, 2, 2, 2),
      List(1, 1, 3, 4),
      List(1, 1, 2, 5),
      List(1, 1, 2, 2, 3),
      List(1, 1, 1, 3, 3),
      List(1, 1, 1, 2, 4),
      List(1, 1, 1, 2, 2, 2),
      List(1, 1, 1, 1, 5),
      List(1, 1, 1, 1, 2, 3)
    )

    def split(n: Int): List[Int] = Iterator.iterate(n)(_ / 10).takeWhile(_ != 0).map(_ % 10).toList

    def areDisjoint(ns: Int*): Boolean = {
      val ls = ns.flatMap(split)
      ls.size == ls.toSet.size
    }

    def solve() = {

      val solutions6 = for {
        is <- partitions.filter(_.size == 6)
        a <- p(is(0))
        b <- p(is(1)) if a < b && areDisjoint(a, b)
        c <- p(is(2)) if b < c && areDisjoint(a, b, c)
        d <- p(is(3)) if c < d && areDisjoint(a, b, c, d)
        e <- p(is(4)) if d < e && areDisjoint(a, b, c, d, e)
        f <- p(is(5)) if e < f && areDisjoint(a, b, c, d, e, f)
      } yield (a, b, c, d, e, f)

      val solutions5 = for {
        is <- partitions.filter(_.size == 5)
        a <- p(is(0))
        b <- p(is(1)) if a < b && areDisjoint(a, b)
        c <- p(is(2)) if b < c && areDisjoint(a, b, c)
        d <- p(is(3)) if c < d && areDisjoint(a, b, c, d)
        e <- p(is(4)) if d < e && areDisjoint(a, b, c, d, e)
      } yield (a, b, c, d, e)

      val solutions4 = for {
        is <- partitions.filter(_.size == 4)
        a <- p(is(0))
        b <- p(is(1)) if a < b && areDisjoint(a, b)
        c <- p(is(2)) if b < c && areDisjoint(a, b, c)
        d <- p(is(3)) if c < d && areDisjoint(a, b, c, d)
      } yield (a, b, c, d)

      val solutions3 = for {
        is <- partitions.filter(_.size == 3)
        a <- p(is(0))
        b <- p(is(1)) if a < b && areDisjoint(a, b)
        c <- p(is(2)) if b < c && areDisjoint(a, b, c)
      } yield (a, b, c)

      val solutions2 = for {
        is <- partitions.filter(_.size == 2)
        a <- p(is(0))
        b <- p(is(1)) if a < b && areDisjoint(a, b)
      } yield (a, b)

      (solutions2 ++ solutions3 ++ solutions4 ++ solutions5 ++ solutions6).size
    }

    // 13042 – 2601ms

  }

  object Attempt4 {
    val digits = (1 to 9).permutations

    val is = for {
      a <- 0 to 4
      b <- 0 to(9, 2) if a + b < 10
      c <- 0 to(9, 3) if a + b + c < 10
      d <- 0 to(9, 4) if a + b + c + d < 10
      e <- 0 to(9, 5) if a + b + c + d + e == 9
    } yield (a, b, c, d, e)

    val p = for {
      v <- digits
      (a, b, c, d, e) <- is.toIterator
    } yield ()

    def solve() = p take 10 foreach println
  }
}
