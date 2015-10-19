object Main extends App {
  val begin = System.nanoTime()
  val solution = pep_094.Solution.solve()
  val ms = (System.nanoTime() - begin) / 1000 / 1000
  println(s"$solution – ${ms}ms")
}
