package pep_076

import org.specs2.mutable.Specification

class WipSpec extends Specification {

  val sampleResults =
    """0 0
      |1 0
      |2 1
      |3 2
      |4 4
      |5 6
      |6 10
      |7 14
      |8 21
      |9 29
      |10 41
      |11 55
      |12 76
      |13 100
      |14 134
      |15 175
      |16 230
      |17 296
      |18 384
      |19 489
      |20 626""".stripMargin
//      |21 791
//      |22 1001
//      |23 1254
//      |24 1574
//      |25 1957
//      |26 2435
//      |27 3009
//      |28 3717
//      |29 4564
//      |30 5603""".stripMargin
      .split("\n").map(_.split(" ")(1)).toList.map(_.toInt)

  "solve1" >> {
    sampleResults ==== sampleResults.indices.map(Wip.solve1).map(_.size).toList
  }.pendingUntilFixed

  "solve2" >> {
    sampleResults ==== sampleResults.indices.map(Wip.solve2).map(_.size).toList
  }

  "solve3" >> {
    sampleResults ==== sampleResults.indices.map(Wip.solve3).map(_.size).toList
  }

}
