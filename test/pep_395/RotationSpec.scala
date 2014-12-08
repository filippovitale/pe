package pep_395

import org.specs2.Specification
import org.specs2.matcher.DataTables

class RotationSpec extends Specification with DataTables {
  def is =
    "adding integers should just work in scala" ! e1

  def e1 =
    "a" | "b" | "c" |
      2 ! 2 ! 4 |
      1 ! 1 ! 2 |> {
      (a, b, c) => a + b === c
    }
}
