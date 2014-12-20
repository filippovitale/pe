package pep_395

import org.scalacheck._
import org.specs2._
import pep_395.PE.XY

class RotationCheck extends Specification with ScalaCheck {

  val ε = 0.0000000001

  def anyXY = for {
    y <- Gen.chooseNum(-10000.0, 10000.0) //Arbitrary.arbitrary[Double]
    x <- Gen.chooseNum(-10000.0, 10000.0) //Arbitrary.arbitrary[Double]
  } yield XY(x, y)

  def is =
    "distance between point always >= 0" !
      Prop.forAll(anyXY, anyXY) { (a: XY, b: XY) => a.distance(b) must be_>=(0.0)} ^
      "360° rotation, comes back to the same spot" !
        Prop.forAll(anyXY) { (p: XY) => p must_== p.r(2 * math.Pi)} ^
      "360° rotation with any center, comes back to the same spot" !
        Prop.forAll(anyXY, anyXY) { (p: XY, c: XY) => p must_== p.scaleAndRotate(c, 2 * math.Pi, 1)} ^
      end
}
