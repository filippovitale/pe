package pep_052

object Wip {

  val prefix = for {
    a <- 1 to 1
    b <- 1 to 6
  } yield Vector(a, b).mkString

  // TODO merge 2 fors

  val numbers = for {
    p <- prefix
    n <- 0 to 10000
  } yield Vector(p, n).mkString

  def solve(): String = {
    numbers.map(n => (n.toInt, n.sorted)).map({
      case (i, s1) => (i, s1, (i * 2).toString)
    }).filter({
      case (i, s1, n2) => s1 == n2.sorted
    }).map({
      case (i, n1, n2) => i.toString
    }).head
  }

}
