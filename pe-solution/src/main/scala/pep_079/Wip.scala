package pep_079

object Wip {

  object Attempt1 {

    def printStats(l: List[List[Char]]): Unit = {
      println(s"----- total number of lines: ${l.size} ------------------------------")
      l.flatten.groupBy(identity).map { case (a, b) => a -> b.size }.toList.sorted foreach println
      println("--------------------------------------------------------------")
      println("First digit  – 7, 3, 6, 1, 2, 8 – (not 0,4,5,9)")
      l.map(_.head).groupBy(identity).map { case (a, b) => a -> b.size }.toList.sorted foreach println
      println("--------------------------------------------------------------")
      println("Second digit – 1, 2, 6, 8, 9, 3 – (leftmost should be centermost)")
      l.map(_(1)).groupBy(identity).map { case (a, b) => a -> b.size }.toList.sorted foreach println
      println("--------------------------------------------------------------")
      println("Third digit  – 1, 6, 2, 8, 0, 9 – (not 3,4,5,7)")
      l.map(_.last).groupBy(identity).map { case (a, b) => a -> b.size }.toList.sorted foreach println
      println("--------------------------------------------------------------")
    }

    def verifySolution(a: Char, b: Char, c: Char, l: List[Char]): Int =
      if (l.dropWhile(_ != a).drop(1).dropWhile(_ != b).drop(1).dropWhile(_ != c).nonEmpty)
        0
      else
        1

    def verifySolutions(llc: List[List[Char]], l: List[Char]): Int =
      llc.foldLeft(0) { case (err, List(a, b, c)) => err + verifySolution(a, b, c, l) }

    def solveS4(): Unit = {
      val s = "1234".toList

      def r(cs: List[Char]): List[Char] = {
        val r1 = 1 + scala.util.Random.nextInt(cs.size - 1)
        val (a, b) = cs.splitAt(r1)
        val r2 = scala.util.Random.nextInt(b.size)
        List(a.last, b(r2))
      }

      val llc = List.fill(100)(r(s))
      printStats(llc)
    }

    // 7, 3, 6, 1, 2, 8
    //    3, 9, 8, 6, 2, 1
    //    1, 2, 6, 8, 9, 3
    //       1, 6, 2, 8, 0, 9
    // 7, 3, 1, 6, 2, 8, 9, 0
  }

}
