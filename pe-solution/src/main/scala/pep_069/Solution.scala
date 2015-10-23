package pep_069

import common.Prime.isPrime

object Solution {

  def solve(max: Int = 1000000) = {
    var mul = 1
    for (p <- (2 to 100) filter (isPrime(_)); if mul * p < max) mul *= p
    mul
  }

}
