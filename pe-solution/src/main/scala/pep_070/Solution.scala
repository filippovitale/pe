package pep_070

import common.EulerPhi.φ

object Solution {

  (6666666 to 10000000).par.map(n => (n, φ(n))).filter{
    case (n, t) => n.toString.sorted == t.toString.sorted
  }.seq.sortBy{
    case (n, t) => n.toDouble / t
  }.head._1

}
