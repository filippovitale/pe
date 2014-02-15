package pep_082

import scala.collection.mutable

object solution {

  val filename = "src/pep_082/matrix.txt"
  val input = io.Source.fromFile(filename).mkString.trim.split("\n").map(_.split(",") map (_.toInt))
  val lastRow = input.size - 1
  val lastCol = input.head.size - 1

  case class Position(row: Int = 0, col: Int = 0) {
    def isFirstRow: Boolean = row == 0

    def isFirstCol: Boolean = col == 0

    def isLastRow: Boolean = row == lastRow

    def isLastCol: Boolean = col == lastCol


    def isStartPosition: Boolean = isFirstCol

    def isEndPosition: Boolean = isLastCol


    def moveUp: Position = Position(row - 1, col)

    def moveRight: Position = Position(row, col + 1)

    def moveDown: Position = Position(row + 1, col)
  }


  def nextCells(actualCell: Position): Seq[Position] = actualCell match {
    case cell if cell.isEndPosition => Nil
    case cell if cell.isFirstRow => Seq(cell.moveDown, cell.moveRight)
    case cell if cell.isLastRow => Seq(cell.moveUp, cell.moveRight)
    case cell => Seq(cell.moveUp, cell.moveDown, cell.moveRight)
  }

  val distance = mutable.Map[Position, Int]() ++
    (for {r <- 0 to lastRow} yield Position(r, 0) -> input(r)(0))

  val previous = mutable.Map[Position, Position]()

  case class PositionsToExplore(priority: Int, position: Position) extends Ordered[PositionsToExplore] {
    def compare(that: PositionsToExplore) = that.priority compare this.priority
  }

  val firstColumn = for {
    r <- 0 to lastRow
  } yield PositionsToExplore(0, Position(r, 0))

  val rest = for {
    r <- 0 to lastRow
    c <- 1 to lastCol
  } yield PositionsToExplore(Int.MaxValue, Position(r, c))

  var positionsToExplore = mutable.PriorityQueue[PositionsToExplore]() ++ firstColumn ++ rest

  var a = positionsToExplore.length

  def solve(): Int = {
    var result: Int = Int.MaxValue
    while (positionsToExplore.nonEmpty) {
      val position = positionsToExplore.dequeue().position
      val neighbors = nextCells(position)
      neighbors.foreach {
        case neighbor =>
          // val pathValue = distance.getOrElseUpdate(position, Int.MaxValue) + input(neighbor.row)(neighbor.col)
          val pathValue = if (distance.contains(position)) {
            distance.get(position).get + input(neighbor.row)(neighbor.col)
          } else {
            Int.MaxValue
          }

          if (neighbor.isEndPosition && pathValue < result) {
            result = pathValue
          }

          if (pathValue < distance.getOrElse(neighbor, Int.MaxValue)) {
            distance.update(neighbor, pathValue)
            previous.update(neighbor, position)
            // TODO PriorityQueue are O(n) for random access :-/
            positionsToExplore = positionsToExplore.filterNot(_.position == neighbor)
            positionsToExplore.enqueue(PositionsToExplore(pathValue, neighbor))
          }
      }
    }
    result
  }
}
