package pep_069

import common.IntOps

object Solution {

  def solve(max: Int = 1000000) = {
    var mul = 1
    for (p <- (2 to 100) filter IntOps.isPrime; if mul * p < max) mul *= p
    mul
  }

}
