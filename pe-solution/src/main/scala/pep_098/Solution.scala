package pep_098

object Solution {
  val stream = getClass.getResourceAsStream("/pep_098/p098_words.txt")
  val string = scala.io.Source.fromInputStream(stream).mkString

  def isSquare(n: Int): Boolean = {
    val s = math.sqrt(n).toInt
    s * s == n
  }

  def squareAnagrams(a: String, b: String): Iterator[Int] = {
    assume(a.length == b.length)

    val cs = a.toSet
    assume(cs == b.toSet)

    for {
      subSet <- (0 to 9).toSet.subsets(a.length)
      permutation <- subSet.toList.permutations
      map = (cs zip permutation).toMap
      if map(a.head) != 0
      if map(b.head) != 0
      ca = (0 /: a.map(map)) { case (n, i) => n * 10 + i }
      if isSquare(ca)
      cb = (0 /: b.map(map)) { case (n, i) => n * 10 + i }
      if isSquare(cb)
    } yield math.max(ca, cb)
  }

  def solve() = {
    val words = string.split(',').map(_.filter(_ != '"')).filter(_.length > 1).toList
    val anagramWords = words.map(w => w.sorted -> w).groupBy(_._1).mapValues(_.map(_._2)).values.filter(_.length > 1)

    val candidates = for {
      ls <- anagramWords.toIterator
      ss <- ls.toSet.subsets(2)
      a = ss.head
      b = ss.last
      sa <- squareAnagrams(a, b)
    } yield sa

    candidates.max
  }
}
