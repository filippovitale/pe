package pep_097


object Solution {

  def solve(): String =
    (BigInt(2).modPow(7830457, 10000000000L) * 28433 + 1)
    .toString().takeRight(10)

}
