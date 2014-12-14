package pep_395

import org.specs2.Specification
import org.specs2.matcher.DataTables
import pep_395.PE.XY

class RotationSpec extends Specification with DataTables {
  def is = "adding integers should just work in scala" ! e1

  def e1 =
    "center" | "point" | "θ" | "result" |
      XY(0, 0) ! XY(0, 5) ! (-1.0 / 4.0) * math.Pi ! XY(5, 5) |
      XY(0, 0) ! XY(0, 5) ! (-3.0 / 4.0) * math.Pi ! XY(5, -5) |
      XY(0, 0) ! XY(0, 5) ! (3.0 / 4.0) * math.Pi ! XY(-5, -5) |
      XY(0, 0) ! XY(0, 5) ! (1.0 / 4.0) * math.Pi ! XY(-5, 5) |> {
      (center, point, θ, result) =>
        val calc = PE.rotate(center, point, θ, math.sqrt(2))
        calc.x must be_==~(result.x)
        calc.y must be_==~(result.y)
    }
}
