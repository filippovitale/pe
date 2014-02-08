package pep_081

object wip {

  val filename = "src/pep_081/matrix.txt"
  val input = io.Source.fromFile(filename)
    .mkString.trim.split("\n")
    .map(_.split(",") map (_.toInt))
  // TODO -1 ?
  val lastRow = input.size
  val lastCol = input.head.size

  case class Cell(row: Int, col: Int)

  def nextCells(actualCell: Cell): Seq[Cell] = {
    Nil
  }

  def solve(): Int = {
    -1
  }

  def main(args: Array[String] = Array()) {
    println(solve())
  }
}
