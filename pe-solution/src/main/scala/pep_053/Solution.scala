package pep_053

object Factorial {
  def apply(n: Int): BigInt = calculateByForLoop(n)

  def calculateByForLoop(n: Int): BigInt = {
    require(n > 0, "n must be positive")

    var accumulator: BigInt = 1
    for (i <- 1 to n)
      accumulator = i * accumulator
    accumulator
  }
}

object Solution {

  def ncr(n: Int, r: Int): BigInt = Factorial(n) / (Factorial(r) * Factorial(n - r))

  val combinatoricSelection = for {
    n <- 1 to 100
    r <- 1 until n
  } yield ncr(n, r)

  def solve() = combinatoricSelection.count(_ > 1000000)

}
