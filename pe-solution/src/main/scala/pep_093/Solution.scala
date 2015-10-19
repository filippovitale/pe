package pep_093

object Solution {

  def permutations(α: Int, β: Int, γ: Int, δ: Int) =
    Array(α, β, γ, δ).permutations.map { case Array(a, b, c, d) => (a, b, c, d) }

  // -----------------------------------------------------------------------------------------------------------------

  import scalaz.syntax.std.boolean._

  type Op = (Int, Int) => Option[Int]

  val plus: Op = {
    case (a, b) => Option(a + b)
  }
  val minus: Op = {
    case (a, b) => (a > b) ? Option(a - b) | None
  }
  val multiply: Op = {
    case (a, b) => Option(a * b)
  }
  val divide: Op = {
    case (a, b) => (a % b == 0) ? Option(a / b) | None
  }

  // -----------------------------------------------------------------------------------------------------------------

  def eval(a: Int, ab: Op, b: Int, bc: Op, c: Int, cd: Op, d: Int): Iterator[Int] = List(
    for {x <- ab(a, b); y <- bc(x, c); z <- cd(y, d)} yield z,
    for {x <- bc(b, c); y <- ab(a, x); z <- cd(y, d)} yield z,
    for {x <- bc(b, c); y <- cd(x, d); z <- ab(a, y)} yield z,
    for {x <- cd(c, d); y <- bc(b, x); z <- ab(a, y)} yield z,
    for {x <- ab(a, b); y <- cd(c, d); z <- bc(x, y)} yield z
  ).flatten.toIterator

  // -----------------------------------------------------------------------------------------------------------------

  def solve() = {
    val MAX = 10
    val candidates = for {
      α <- (1 until MAX).toList
      β <- (α until MAX).toList
      γ <- (β until MAX).toList
      δ <- (γ until MAX).toList
    } yield (α, β, γ, δ)

    def generateResults(α: Int, β: Int, γ: Int, δ: Int) = for {
      (a, b, c, d) <- permutations(α, β, γ, δ)
      ab <- Iterator(plus, minus, multiply, divide)
      bc <- Iterator(plus, minus, multiply, divide)
      cd <- Iterator(plus, minus, multiply, divide)
      result <- eval(a, ab, b, bc, c, cd, d)
    } yield result

    val highests = for {
      (α, β, γ, δ) <- candidates
      results = generateResults(α, β, γ, δ).toSet.toList.sorted
      highest <- results.zipWithIndex dropWhile (x => x._1 - x._2 == 1) map (_._2) take 1
    } yield (List(α, β, γ, δ).mkString, highest)

    highests.sortBy(_._2).reverse.head._1
  }
}
