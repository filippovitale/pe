package pep_090

object Solution {
  val DICE_FACES = 6
  val digits = (0 to 9).toList

  // all of the square numbers below one-hundred
  // 01, 04, 09, 16, 25, 36, 49, 64, 81
  // 01, 04, 06, 16, 25, 36, 46, 64, 81 // 6/9 normalised
  // 01, 04, 06, 16, 25, 36, 46, 81     // no duplicates
  val squares = List((0, 1), (0, 4), (0, 9), (1, 6), (2, 5), (3, 6), (4, 9), (6, 4), (8, 1))

  val includingUpsideDown: Int => List[Int] = {
    case 6 | 9 => List(6, 9)
    case a     => List(a)
  }

  def containsAllSquares(d1: List[Int], d2: List[Int]): Boolean = {
    val rollCombinations = for {
      f1 <- d1 flatMap includingUpsideDown
      f2 <- d2 flatMap includingUpsideDown
    } yield (f1, f2)

    squares.forall { case (a, b) =>
      rollCombinations.contains((a, b)) || rollCombinations.contains((b, a))
    }
  }

  def solve(): String = {

    val arrangements = for {
      (d1, i) <- (digits combinations DICE_FACES).zipWithIndex
      d2 <- digits combinations DICE_FACES take i
    } yield (d1, d2)

    arrangements.count((containsAllSquares _).tupled).toString
  }

}
