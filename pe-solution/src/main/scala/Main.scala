object Main extends App {
  val begin = System.nanoTime()
  val solution = pep_162.Solution.solve()
  val solution = pep_095.Wip.Attempt1.solve()
  val ms = (System.nanoTime() - begin) / 1000 / 1000
  println(s"$solution – ${ms}ms")
}
