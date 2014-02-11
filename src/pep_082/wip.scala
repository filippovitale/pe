package pep_082

import scala.collection.mutable

object wip {

  val filename = "src/pep_082/matrix.txt"
  //  val input = io.Source.fromFile(filename).mkString.trim.split("\n").map(_.split(",") map (_.toInt))
  val input = """
                |131	673	234	103	18
                |201	96	342	965	150
                |630	803	746	422	111
                |537	699	497	121	956
                |805	732	524	37	331
              """.stripMargin.trim.split("\n").map(_.split("\t") map (_.toInt))
  val lastRow = input.size - 1
  val lastCol = input.head.size - 1

  case class Position(row: Int = 0, col: Int = 0) {
    def Start: Position = Position(0, 0)

    def isEndPosition: Boolean = isLastCol

    def isFirstRow: Boolean = row == 0

    def isLastRow: Boolean = row == lastRow

    def isLastCol: Boolean = col == lastCol

    def moveUp: Position = Position(row - 1, col)

    def moveRight: Position = Position(row, col + 1)

    def moveDown: Position = Position(row + 1, col)
  }

  def nextCells(actualCell: Position): Seq[Position] = actualCell match {
    case cell if cell.isLastCol => Nil
    case cell if cell.isFirstRow => Seq(cell.moveDown, cell.moveRight)
    case cell if cell.isLastRow => Seq(cell.moveUp, cell.moveRight)
    case cell => Seq(cell.moveUp, cell.moveDown, cell.moveRight)
  }

  val distance = mutable.Map[Position, Int](Position().Start -> 0)
  val previous = mutable.Map[Position, Position]()

  //  val a = for {
  //    i <- 0 to 3
  //    j <- 0 to 3
  //  } yield (i, j)

  //  val positions: Position =  for {
  //    row: Int <- 0 to 79
  //    col: Int <- 0 to 79
  //  } yield (Position(row, col), Int.MaxValue)

  // (Position(0,0), 0) ++ o + positions.tail

  case class PositionsToExplore(priority: Int, position: Position) extends Ordered[PositionsToExplore] {
    def compare(that: PositionsToExplore) = that.priority compare this.priority
  }

  val positionsToExplore = mutable.PriorityQueue[PositionsToExplore]() ++
    mutable.Seq(PositionsToExplore(Int.MaxValue, Position(0, 0))) // ++ the rest

  // positionsToExplore.dequeue()

  def minPath(f: Position => Int)(cell: Position): Int = {
    -1
  }

  def solve(startCell: Position = Position(0, 0)): Int = {
    -1 // TODO
  }
}
