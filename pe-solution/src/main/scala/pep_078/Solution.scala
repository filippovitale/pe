package pep_078


object Solution {

  val max = 60000

  val penta = Stream.from(1)
    .flatMap { n => List(n, -1 * n) }
    .map(n => (3 * n * n - n) / 2)
    .takeWhile(_ < max).toArray

  val partitions = Array.fill[Int](max)(1)

  def solve(k: Int): Int = {

    for (n <- 1 until max) {
      partitions(n) = penta
        .takeWhile(_ <= n)
        .map(n - _).map(partitions)
        .grouped(2).map(_.sum)
        .grouped(2).map {
        case a :: b :: Nil => a - b
        case a :: Nil      => a
      }.foldLeft(0L) { case (a, b) => (a + b) % k }
        .toInt
    }

    Stream.from(2)
      .map(n => (n, partitions(n)))
      .dropWhile(_._2 != 0)
      .map(_._1)
      .head
  }

  def solve(): String = solve(1000000).toString

}
