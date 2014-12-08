package pep_062

object Solution {

  val solve = (BigInt(1) to BigInt(9000)).map(_.pow(3)).map(_.toString()).map(s => (s, s.sorted)).groupBy(_._2).filter(_._2.size > 4).map(_._2).head.map(_._1).head

}
