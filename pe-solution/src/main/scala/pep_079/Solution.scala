package pep_079

import scalaz._, Scalaz._

object Solution {

  val filename = "/pep_079/p079_keylog.txt"
  val input = io.Source.fromURL(getClass.getResource(filename))
    .mkString.trim.split("\n").map(_.toList).toList


  def solve(): String = {

    def aaa(i: Int, k: Int) = input.map(_(i)).groupBy(identity).map { case (a, b) => a -> b.size * k }

    val a = aaa(0, -1)
    val b = aaa(1, 0)
    val c = aaa(2, 1)

    val first = (a.keySet -- b.keySet -- c.keySet).toList
    val body = List(a, b, c).suml.toList.sortBy(_._2).map(_._1)
    val last = (c.keySet -- b.keySet -- a.keySet).toList

    val solution = first ::: body.diff(first).diff(last) ::: last
    solution.mkString
  }

}
