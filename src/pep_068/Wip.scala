package pep_068

object Wip {

  val i2c = (i: Int) => ('0'.toInt + i).toChar

  def describeSet(combinations: Seq[Seq[Char]]): String = combinations.map(_.mkString("")).mkString("")

  def solve(n: Int = 3) = {
    def combinations(ext: Vector[Char], int: Vector[Char]): Seq[Seq[Char]] = for {
      i <- 0 until n
      j = (i + 1) % n
    } yield Seq(ext(i), int(i), int(j))

    val extInt = for {
      e0 <- 1 to n * 2
      e1 <- 1 to n * 2
      if e0 < e1
      e2 <- 1 to n * 2
      if e0 < e2 && e1 != e2

      i0 <- 1 to n * 2
      if i0 != e0 && i0 != e1 && i0 != e2
      i1 <- 1 to n * 2
      if i1 != e0 && i1 != e1 && i1 != e2 && i1 != i0
      i2 = ((1 to 6).toSet -- Set(e0, e1, e2, i0, i1)).head
    } yield (Vector(e0, e1, e2) map i2c, Vector(i0, i1, i2) map i2c)

    (extInt map {
      case (e, i) => combinations(e, i)
    } filter {
      _.map(_.sum).distinct.length == 1
    } map {
      describeSet
    } filter {
      _.length == 9
    } sorted).last
    //    }).filter(_.length == 16).sorted.head // TODO can we filter the ones != 16 earlier?
  }
}
