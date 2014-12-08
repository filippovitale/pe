package pep_081

class Memoize1[-T, +R](f: T => R) extends (T => R) {
  private[this] val memorized = scala.collection.mutable.Map.empty[T, R]

  def apply(x: T): R = {
    memorized.getOrElseUpdate(x, f(x))
  }
}

object Memoize1 {
  def apply[T, R](f: T => R) = new Memoize1(f)

  // Y Combinator Memoize
  def Y[T, R](f: (T => R) => T => R): (T => R) = {
    lazy val yf: T => R = Memoize1(f(yf)(_))
    yf
  }
}
