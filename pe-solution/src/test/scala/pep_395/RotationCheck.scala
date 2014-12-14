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
    "distance between point always >= 0" ! ex1 ^
      "360° rotation, comes back to the same spot" ! ex2 ^
      "360° rotation with any center, comes back to the same spot" ! ex3 ^
      end


  def ex1 = Prop.forAll(anyXY, anyXY) { (a: XY, b: XY) => a.distance(b) must be_>=(0.0)}

  def ex2 = Prop.forAll(anyXY) { (p: XY) =>
    val pr = p.r(2 * math.Pi)
    math.abs(p.x - pr.x) must be_<(ε)
    math.abs(p.y - pr.y) must be_<(ε)
  }

  def ex3 = Prop.forAll(anyXY, anyXY) { (p: XY, c: XY) =>
    val pr = PE.scaleAndRotate(c, p, 2 * math.Pi, 1)
    math.abs(p.x - pr.x) must be_<(ε)
    math.abs(p.y - pr.y) must be_<(ε)
  }
}

// prop { (a: String, b: String) => (a + b).startsWith(a)} ^
// check { (a: String, b: String) => (a + b).startsWith(a)} ^
