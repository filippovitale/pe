package pep_048

import scala.math.BigInt.int2bigInt

object solution {

  def solve() = (1 until 1000)
    .map(n => n.modPow(n, 10.pow(10)))
    .sum
    .mod(10.pow(10))

}
