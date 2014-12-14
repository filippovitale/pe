package pep_395

import org.specs2.Specification
import org.specs2.matcher.DataTables
import pep_395.PE.XY

class RotationSpec extends Specification with DataTables {
  def is = "adding integers should just work in scala" ! e1

  def e1 =
    "center" | "point" | "θ" | "result" |
      XY(0, 0) ! XY(0, 5) ! -0.25 * math.Pi ! XY(+5, +5) |
      XY(0, 0) ! XY(0, 5) ! -0.75 * math.Pi ! XY(+5, -5) |
      XY(0, 0) ! XY(0, 5) ! +0.75 * math.Pi ! XY(-5, -5) |
      XY(0, 0) ! XY(0, 5) ! +0.25 * math.Pi ! XY(-5, +5) |> {
      (center, point, θ, result) =>
        val calc = PE.scaleAndRotate(center, point, θ, math.sqrt(2))
        calc.x must beCloseTo(result.x, 5.significantFigures)
        calc.y must beCloseTo(result.y, 5.significantFigures)
    }
}
