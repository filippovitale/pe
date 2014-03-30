package pep_059

object Wip {

  def solve() = {
    val filename = "src/pep_059/cipher1.txt"
    val ints = io.Source.fromFile(filename).mkString.trim.split(",").map(_.toInt).toStream

  }

}
