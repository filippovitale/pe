package pep_068

object Wip {

  def describeSet(combinations: Seq[Seq[Int]]): String = combinations.map(_.mkString("")).mkString("")

  def solve(n: Int = 5) = {
    def combinations(ext: Vector[Int], int: Vector[Int]): Seq[Seq[Int]] = for {
      i <- 0 until n
      j = (i + 1) % n
    } yield Seq(ext(i), int(i), int(j))

    val extInt = for {
      e0 <- 1 to 6
      e1 <- 1 to 10
      if e0 < e1
      e2 <- 1 to 10
      if e0 < e2 && e2 != e1
      e3 <- 1 to 10
      if e0 < e3 && e3 != e1 && e3 != e2
      e4 <- 1 to 10
      if e0 < e4 && e4 != e1 && e4 != e2 && e4 != e3
      if Set(e0, e1, e2, e3, e4).contains(10)

      i0 <- 1 to 9
      if i0 != e0 && i0 != e1 && i0 != e2 && i0 != e3 && i0 != e4
      i1 <- 1 to 9
      if i1 != e0 && i1 != e1 && i1 != e2 && i1 != e3 && i1 != e4
      if i1 != i0
      i2 <- 1 to 9
      if i2 != e0 && i2 != e1 && i2 != e2 && i2 != e3 && i2 != e4
      if i2 != i0 && i2 != i1
      i3 <- 1 to 9
      if i3 != e0 && i3 != e1 && i3 != e2 && i3 != e3 && i3 != e4
      if i3 != i0 && i3 != i1 && i3 != i2
      i4 = ((1 to 9).toSet -- Set(e0, e1, e2, i0, i1, i2, i3)).head
    } yield (Vector(e0, e1, e2, e3, e4), Vector(i0, i1, i2, i3, i4))

    (extInt map {
      case (e, i) => combinations(e, i)
    } filter {
      _.map(_.sum).distinct.length == 1
    } map {
      describeSet
    } sorted).last
  }
}
