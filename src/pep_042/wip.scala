package pep_042


object wip {

  val filename = "src/pep_042/words.txt"
  val words = io.Source.fromFile(filename).mkString.trim.split(",").map(_.drop(1).dropRight(1))

  def solve(): Int = {
    -1
  }

}
