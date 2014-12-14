package pep_395

import org.specs2._
import org.scalacheck._
import pep_395.PE.XY

class RotationCheck extends Specification with ScalaCheck {

  def anyXY = for {
    y <- Arbitrary.arbitrary[Double]
    x <- Arbitrary.arbitrary[Double]
  } yield XY(x, y)

  def is =
    "distance between point always >= 0" ! ex1 ^
      end


  def ex1 = Prop.forAll(anyXY, anyXY) { (a: XY, b: XY) => a.distance(b) must be_>=(0.0)}
  //    "startsWith (prop)" ! prop { (a: String, b: String) => (a + b).startsWith(a)} ^
  //      "startsWith" ! check { (a: String, b: String) => (a + b).startsWith(a)} ^

}
