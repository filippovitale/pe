package pep_092

import scalaz.Memo

object Solution {

  val arriveAt89: Int => Boolean = Memo.mutableHashMapMemo {
    case 89 => true
    case 1  => false
    case n  => arriveAt89(n.toString.toList.map(_ - '0').map(x => x * x).sum)
  }

  def solve(): String = (1 to 10000000).count(arriveAt89).toString

}
