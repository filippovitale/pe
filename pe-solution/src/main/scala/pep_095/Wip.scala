package pep_095

import common.LongOps

import scala.collection.mutable

object Wip {
  val MAX = 1000000

  object Attempt1 {
    def solve(): String = {
      //      val a: Seq[(ChainLength, Long)] = (1 to MAX).map(a => (aaa(a), a * -1))
      //      val b: (ChainLength, Long) = a.max
      //      val c: Long = b._2 * -1
      //      c.toString
      ???
    }
  }

  object Attempt2 {
    type ChainLength = Int
    val map = mutable.Map.empty[Long, ChainLength]

    def aaa(n: Long): Unit = aaa(List(n))

    def aaa(ls: List[Long]): Unit = {
      //println(s"ls=$ls\tmap=$map")
      ls match {
        case l :: t if l > MAX         => map ++= t.map(_ -> 0).toMap
        case l :: t if map.contains(l) => map ++= t.map(_ -> 0).toMap
        case l :: t if t.contains(l)   =>
          val (chain, nothing) = t.splitAt(t.indexOf(l) + 1)
          println(s"l=$l\tt=$t\tchain=$chain\tnothing=$nothing")
          map ++= chain.map(_ -> chain.length).toMap ++ nothing.map(_ -> 0).toMap
        case l :: t                    => aaa(LongOps.properDivisorsSum(l) :: ls)
        case _                         => ???
      }

    }

    def solve() = {
      val ls = LongOps.streamFrom(1).take(MAX)
      ls foreach aaa
      val (len, n) = map.toIterator.map { case (k, v) => (v, -1 * k) }.max
      println(s"(len, n)=($len, $n)")
      n * -1
    }
  }

  // 15065ms
  object Attempt3 {
    type ChainLength = Int
    val map = mutable.Map.empty[Long, ChainLength]

    def findChain(ls: List[Long]): (List[Long], List[Long]) = ls match {
      case l :: t if l > MAX | map.contains(l) => (List.empty[Long], t)
      case l :: t if t.contains(l)             => t.splitAt(t.indexOf(l) + 1)
      case l :: t                              => findChain(LongOps.properDivisorsSum(l) :: ls)
    }

    def solve() = {
      val ls = LongOps.streamFrom(1).take(MAX / 50)
      ls.foreach { l =>
        val (chain, nothing) = findChain(List(l))
        map ++= chain.map(_ -> chain.length).toMap ++ nothing.map(_ -> 0).toMap
      }

      val (len, n) = map.toIterator.map { case (k, v) => (v, -1 * k) }.max
      println(s"(len, n)=($len, $n)")
      n * -1
    }
  }

}
