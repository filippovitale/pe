package pep_069

import pep_050.PrimesSeq

object Solution {

  def solve(max: Int = 1000000) = {
    var mul = 1
    for (p <- PrimesSeq(100); if mul * p < max) mul *= p
    mul
  }

}
