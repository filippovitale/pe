package pep_052

object Solution {

  val numbers = for {
    a <- 1 to 1
    b <- 1 to 6
    p = Vector(a, b).mkString
    n <- 0 to 10000
  } yield Vector(p, n).mkString

  def solve() = numbers.map({
    n => (n.toInt, n.sorted)
  }).filter({
    case (n, s) => (2 to 6).forall(i => s == (n * i).toString.sorted)
  }).map({
    case (n, _) => n.toString
  }).head

}
