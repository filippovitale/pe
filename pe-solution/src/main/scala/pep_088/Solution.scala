package pep_088

object Solution {
  var MAX = 12000
  val numFactors = (math.log10(2 * MAX) / math.log10(2)).toInt
  val factors = Array.fill(numFactors)(Int.MaxValue)
  val k = (0 to MAX).map(_ * 2).toArray

  def solve() = {
    factors(0) = 1
    factors(1) = 0
    k(1) = 0
    var maxFactor = 1
    var i = 0

    while (i > 0 || maxFactor < numFactors) {
      if (i == 0) {
        if (factors(i) < factors(i + 1)) {
          factors(i) += 1
        } else {
          factors(0) = 2
          maxFactor += 1
        }
        factors(1) = factors(0) - 1
        i += 1
      } else {
        if (i == maxFactor - 1) {
          factors(i) += 1
          val prod = factors.take(maxFactor).product
          val sum = factors.take(maxFactor).sum

          if (prod <= 2 * MAX) {
            val j = prod - sum + maxFactor
            if (j <= MAX && prod < k(j)) k(j) = prod
          } else {
            i -= 1
          }
        } else {
          if (factors(i) < factors(i + 1)) {
            val actual = factors(i)
            factors(i) = actual + 1
            factors(i + 1) = actual
            i += 1
          } else {
            i -= 1
          }
        }
      }
    }
    k.distinct.sum.toString
  }

}
