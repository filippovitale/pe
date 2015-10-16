package pep_089

object Solution {

  val stream = getClass.getResourceAsStream("/pep_089/p089_roman.txt")
  val lines = scala.io.Source.fromInputStream(stream).getLines()

  def solve(): String = lines
    .map(s => s.length - s.replaceAll("DCCCC|LXXXX|VIIII|CCCC|XXXX|IIII", "  ").length)
    .sum.toString

}
