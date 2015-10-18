package pep_099

object Solution {

  val stream = getClass.getResourceAsStream("/pep_099/p099_base_exp.txt")
  val lines = scala.io.Source.fromInputStream(stream).getLines()
  val numbers = for {
    s <- lines
    Array(b, e) = s.split(",")
  } yield e.toDouble * math.log(b.toDouble)

  def solve(): String = (numbers.zipWithIndex.maxBy(_._1)._2 + 1).toString

}
