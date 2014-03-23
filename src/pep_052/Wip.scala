package pep_052

object Wip {

  val prefix = for {
    a <- 1 to 1
    b <- 1 to 6
  } yield Vector(a, b).mkString

  val numbers = for {
    p <- prefix
    n <- 0 to 10000
  } yield Vector(p, n).mkString

  numbers.map(n => (n, n.toInt)).map({
    case (n, i) => (n, (i * 2).toString)
  }).map({
    case (n1, n2) => (n1, n1.sorted, n2.sorted)
  }).filter({
    case (n, n1, n2) => n1.sorted == n2.sorted
  }).map({
    case (n, n1, n2) => n
  }).head

}
