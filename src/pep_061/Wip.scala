package pep_061

import scala.math._

object Wip {

  val p3 = Stream.from(1).map(n => (n * (n + 1)) / 2)
  val p4 = Stream.from(1).map(n => n * n)
  val p5 = Stream.from(1).map(n => (n * (3 * n + 1)) / 2)
  val p6 = Stream.from(1).map(n => n * (2 * n + 1))
  val p7 = Stream.from(1).map(n => (n * (5 * n - 3)) / 2)
  val p8 = Stream.from(1).map(n => n * (3 * n - 2))

  val digit = 4
  val (min, max) = (pow(10, digit - 1).toInt, pow(10, digit).toInt)

  def solve() = {
    val solutions = for {
      a <- p3.dropWhile(_ < min).takeWhile(_ < max)
      b <- p4.dropWhile(_ < min).takeWhile(_ < max)
      if a.toString.drop(digit / 2) == b.toString.take(digit / 2)
      c <- p5.dropWhile(_ < min).takeWhile(_ < max)
      if b.toString.drop(digit / 2) == c.toString.take(digit / 2)
      if c.toString.drop(digit / 2) == a.toString.take(digit / 2)
    } yield (a, b, c)

  }
}
