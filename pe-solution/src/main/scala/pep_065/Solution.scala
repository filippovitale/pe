package pep_065

object Solution {

  def a(i: Int) = (i % 3, i / 3) match {
    case (0, r) => 2 * r
    case _ => 1
  }

  def solve(max: Int = 100): Int = {
    var (nk2, nk1, n) = (BigInt(0), BigInt(1), BigInt(2))

    for (i <- 2 to max) {
      nk2 = nk1
      nk1 = n
      n = a(i) * nk1 + nk2
    }
    n.toString().map(_.toInt - '0'.toInt).sum
  }

}

// WIP
//  import Stream._

// Numerator of the Continued Fractions of e
//  lazy val numerators: Stream[Int] = 1 #:: 3 #:: (numerators zip numerators.tail map {
//    case (nk2, nk1) => ak * nk1 + nk2 // nk = ak * nk-1 + nk-2
//  })

//  lazy val numerators: Stream[(Int, Int, Int)] = (0, 0, 0) #:: numerators.scanLeft((0, 0, 1))({
//    case (a, b) => (0, 0, a._3 + b._3)
//  }) //(nk2, nk1, nk0)

//  lazy val numerators: Stream[(Int, Int, Int)] = (0, 0, 0) #:: numerators.scanLeft((0, 0, 1))({
//    case (a, b) => (0, 0, 10 + b._3)
//  }) //(nk2, nk1, nk0)
//  numerators.take(10).toList

//  Numerator of the Continued Fractions of e
//  lazy val numerators: Stream[(Int, Int)] = (0, 3) #::(1, 8) #:: (numerators zip numerators.tail map {
//    case ((k2, nk2), (k1, nk1)) => (k1 + 1, a(k1 + 1) * nk1 + nk2) // nk = ak * nk-1 + nk-2
//  })

//  val fibs: Stream[Int] = 0 #:: fibs.scanLeft(1)(_ + _)
