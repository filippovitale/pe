package pep_072

import pep_070.EulerPhi.φ

object Solution {
  def solve(max: Int = 1000 * 1000) = (2 to max).par.map(n => BigInt(φ(n))).sum
}
