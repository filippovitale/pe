package common

import scala.collection.mutable

// Implemented for pep_050
case class Memo[A, B](f: A => B) extends (A => B) {
  private val cache = mutable.Map.empty[A, B]

  def apply(x: A) = cache getOrElseUpdate(x, f(x))
}
