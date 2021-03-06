package pep_081

object solution {

  val filename = "src/pep_081/matrix.txt"
  val input = io.Source.fromFile(filename)
    .mkString.trim.split("\n")
    .map(_.split(",") map (_.toInt))

  case class Cell(row: Int, col: Int) {
    val lastRow = input.size - 1
    val lastCol = input.head.size - 1

    def isLastRow: Boolean = row == lastRow

    def isLastCol: Boolean = col == lastCol

    def moveRight: Cell = Cell(row, col + 1)

    def moveDown: Cell = Cell(row + 1, col)
  }

  def nextCells(actualCell: Cell): Seq[Cell] = actualCell match {
    case cell if cell.isLastRow && cell.isLastCol => Nil
    case cell if cell.isLastCol => Seq(cell.moveDown)
    case cell if cell.isLastRow => Seq(cell.moveRight)
    case cell => Seq(cell.moveDown, cell.moveRight)
  }

  def minPath(f: Cell => Int)(cell: Cell): Int = {
    util.Try(nextCells(cell).map(f).min).toOption.
      getOrElse(0) + input(cell.row)(cell.col)
  }

  def solve(startCell: Cell = Cell(0, 0)): Int = {
    Memoize1.Y(minPath)(startCell)
  }
}
