package pep_083

import scala.collection.mutable

object solution {

  val filename = "src/pep_083/matrix.txt"
  val input = io.Source.fromFile(filename).mkString.trim.split("\n").map(_.split(",") map (_.toInt))
  val lastRow = input.size - 1
  val lastCol = input.head.size - 1

  case class Position(row: Int, col: Int) {
    def isFirstRow: Boolean = row == 0

    def isFirstCol: Boolean = col == 0

    def isLastRow: Boolean = row == lastRow

    def isLastCol: Boolean = col == lastCol


    def isStartPosition: Boolean = isFirstRow && isFirstCol

    def isEndPosition: Boolean = isLastRow && isLastCol


    def moveUp: Position = Position(row - 1, col)

    def moveLeft: Position = Position(row, col - 1)

    def moveRight: Position = Position(row, col + 1)

    def moveDown: Position = Position(row + 1, col)

    def nextPositions: Set[Position] = Position(row, col) match {
      case cell if cell.isEndPosition => Set()
      case cell if cell.isStartPosition => Set(cell.moveDown, cell.moveRight)
      case cell =>
        val nextPositions = mutable.Set(cell.moveUp, cell.moveDown, cell.moveLeft, cell.moveRight)
        if (cell.isFirstRow) nextPositions.remove(cell.moveUp)
        if (cell.isFirstCol) nextPositions.remove(cell.moveLeft)
        if (cell.isLastRow) nextPositions.remove(cell.moveDown)
        if (cell.isLastCol) nextPositions.remove(cell.moveRight)
        nextPositions.toSet
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
    var result = Int.MaxValue

    while (positionsToExplore.nonEmpty) {
      val position = positionsToExplore.dequeue().position
      val neighbors = position.nextPositions
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
