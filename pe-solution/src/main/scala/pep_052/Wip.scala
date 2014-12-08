package pep_052

object Wip {

  val numbers = for {
    a <- 1 to 1
    b <- 1 to 6
    p = Vector(a, b).mkString
    n <- 0 to 10000
  } yield Vector(p, n).mkString

  def solve(): String = {
    numbers.map({
      n => (n.toInt, n.sorted)
    }).filter({
      case (n, s) => s == (n * 2).toString.sorted
    }).filter({
      case (n, s) => s == (n * 3).toString.sorted
    }).filter({
      case (n, s) => s == (n * 4).toString.sorted
    }).filter({
      case (n, s) => s == (n * 5).toString.sorted
    }).filter({
      case (n, s) => s == (n * 6).toString.sorted
    }).map({
      case (n, _) => n.toString
    }).head
  }

}
