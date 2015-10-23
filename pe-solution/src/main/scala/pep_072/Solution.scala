package pep_072

import common.EulerPhi.φ

object Solution {
  def solve(max: Int = 1000 * 1000) = (2 to max).par.map(n => BigInt(φ(n))).sum
}
