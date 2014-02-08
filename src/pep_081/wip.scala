package pep_081

object wip {

  val filename = "src/pep_081/matrix.txt"
  val input = io.Source.fromFile(filename)
    .mkString.trim.split("\n")
    .map(_.split(",") map (_.toInt))

  case class Cell(row: Int, col: Int) {
    val lastRow = input.size - 1
    val lastCol = input.head.size - 1

    def isLastRow: Boolean = row == lastRow

    def isLastCol: Boolean = col == lastCol
  }

  def nextCells(actualCell: Cell): Seq[Cell] = actualCell match {
    case cell if cell.isLastRow && cell.isLastCol => Nil
    case cell if cell.isLastCol => Seq(Cell(cell.row + 1, cell.col))
    case cell if cell.isLastRow => Seq(Cell(cell.row, cell.col + 1))
    case cell => Seq(Cell(cell.row + 1, cell.col), Cell(cell.row, cell.col + 1))
  }

  def solve(): Int = {
    -1
  }

  def main(args: Array[String] = Array()) {
    println(solve())
  }
}
