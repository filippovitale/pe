package pep_081

object wip {
  def main(args: Array[String] = Array("src/pep_081/matrix.txt")) {
    val solution = solve(args.head)
    println(solution)
  }

  def solve(filename: String): Int = {
    val matrix = io.Source.fromFile(filename)
      .mkString.trim.split("\n")
      .map(_.split(",") map (_.toInt))


    matrix.last.last // TODO
  }
}
