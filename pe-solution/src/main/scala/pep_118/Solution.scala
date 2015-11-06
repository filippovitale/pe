package pep_118

import common.IntOps._

object Solution {

  def countSolutions(permutation: IndexedSeq[Int], last: Int): Int = permutation.inits
    .map { current => (permutation.drop(current.size), (0 /: current)(_ * 10 + _)) }
    .filter { case (_, number) => number >= last && isPrimeMemo(number) }
    .map { case (next, number) => if (next.isEmpty) 1 else countSolutions(next, number) }
    .sum

  def solve() = (1 to 9).permutations.map(countSolutions(_, 0)).sum
}
