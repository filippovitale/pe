package pep_082

import scala.collection.mutable

object solution {

  val filename = "src/pep_082/matrix.txt"
  val input = io.Source.fromFile(filename).mkString.trim.split("\n").map(_.split(",") map (_.toInt))
  val lastRow = input.size - 1
  val lastCol = input.head.size - 1

  case class Position(row: Int, col: Int) {
    def isFirstRow: Boolean = row == 0

    def isFirstCol: Boolean = col == 0

    def isLastRow: Boolean = row == lastRow

    def isLastCol: Boolean = col == lastCol


    def isStartPosition: Boolean = isFirstCol

    def isEndPosition: Boolean = isLastCol


    def moveUp: Position = Position(row - 1, col)

    def moveRight: Position = Position(row, col + 1)

    def moveDown: Position = Position(row + 1, col)

    def nextCells: Seq[Position] = Position(row, col) match {
      case cell if cell.isEndPosition => Nil
      case cell if cell.isFirstRow => Seq(cell.moveDown, cell.moveRight)
      case cell if cell.isLastRow => Seq(cell.moveUp, cell.moveRight)
      case cell => Seq(cell.moveUp, cell.moveDown, cell.moveRight)
    }
  }

  val positions = for {
    r <- 0 to lastRow
    c <- 0 to lastCol
  } yield Position(r, c)

  val (startPositions, otherPositions) = positions.partition(_.isStartPosition)
  val values = mutable.Map[Position, Int]() ++ positions.map(p => p -> input(p.row)(p.col))
  val distance = mutable.Map[Position, Int]() ++ startPositions.map(p => p -> values(p))

  case class PositionsToExplore(priority: Int, position: Position) extends Ordered[PositionsToExplore] {
    def compare(that: PositionsToExplore) = that.priority compare this.priority
  }

  var positionsToExplore = mutable.PriorityQueue[PositionsToExplore]() ++
    startPositions.map(PositionsToExplore(0, _)) ++
    otherPositions.map(PositionsToExplore(Int.MaxValue, _))

  def solve(): Int = {
    var result: Int = Int.MaxValue

    while (positionsToExplore.nonEmpty) {
      val position = positionsToExplore.dequeue().position
      val neighbors = position.nextCells
      neighbors.foreach {
        case neighbor =>
          // val pathValue = distance.getOrElseUpdate(position, Int.MaxValue) + input(neighbor.row)(neighbor.col)
          val pathValue = if (distance.contains(position)) {
            distance.get(position).get + values(neighbor)
          } else {
            Int.MaxValue
          }

          // refactor tryout
          // val a: Option[Int] = distance.get(position)
          // val pv: Int = distance.get(position).getOrElse(Int.MaxValue)
          // val pvv: Int = if (pv == Int.MaxValue) pv else pv + values(neighbor)

          if (neighbor.isEndPosition && pathValue < result) {
            result = pathValue
          }

          if (pathValue < distance.getOrElse(neighbor, Int.MaxValue)) {
            distance.update(neighbor, pathValue)
            // TODO PriorityQueue has O(n) for random access :-/
            positionsToExplore = positionsToExplore.filterNot(_.position == neighbor)
            positionsToExplore += PositionsToExplore(pathValue, neighbor)
          }
      }
    }
    result
  }
}
