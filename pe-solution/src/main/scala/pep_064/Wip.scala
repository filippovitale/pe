package pep_064

import Stream._

object Wip {

  val max = 13

  val rationalSquares = (1 to max).map(n => n * n).takeWhile(_ <= max).toSet

  val irrationalSquares = (1 to max).filterNot(rationalSquares.contains)

  def continuedFractionPeriodLength(n: Int): Int = {

    // http://oeis.org/A003285
    // a(n)=if(issquare(n), return(0));
    // my(s=sqrt(n), x=s, f=floor(s), P=[0], Q=[1], k);

    var s = math.sqrt(n)
    var x = s
    var f = math.floor(s)
    var P = List(0)
    val Q = List(1)
    //    var k = _

    // while(1, k=#P; P=concat(P, f*Q[k]-P[k]);
    // Q=concat(Q, (n-P[k+1]^2)/Q[k]);
    // k++;
    // for(i=1, k-1, if(P[i]==P[k]&&Q[i]==Q[k], return(k-i)));
    // x=(P[k]+s)/Q[k]; f=floor(x))

    // ...I don't like this implementation
    -1
  }

  def continuedFractions(n: Int): Seq[Int] = {
    // http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
    val m0: Int = 0
    val d0: Int = 1
    val a0: Int = math.floor(math.sqrt(n)).toInt

    val m1: Int = d0 * a0 - m0
    val d1: Int = (n - m1 * m1) / d0
    val a1: Int = math.floor((a0 + m1) / d1).toInt

    Seq() // TODO
  }


  // lazy experiment
  val n: Int = 7
  lazy val cfStream: Stream[(Int, Int, Int)] = (0, 1, n) #:: (cfStream map {
    case (m: Int, d: Int, a: Int) =>
      val fSqrt = math.floor(math.sqrt(n))
      val mn: Int = d * a - m
      val dn: Int = (n - mn * mn) / d
      val an: Int = ((fSqrt + mn) / dn).toInt
      (mn, dn, an)
  })
  cfStream.take(20).toList.map(_._3)

  //  lazy val fibs = {
  //    def f(a: Int, b: Int): Stream[Int] = a #:: f(b, a + b)
  //    f(0, 1)
  //  }

  def continuedFractionPeriod(n: Int): Stream[Int] = {

    val fSqrt = math.floor(math.sqrt(n))
    val lastContinuedFraction = fSqrt * 2

    lazy val cfStream: Stream[(Int, Int, Int)] = (0, 1, n) #:: (cfStream map {
      case (m: Int, d: Int, a: Int) =>
        val mn: Int = d * a - m
        val dn: Int = (n - mn * mn) / d
        val an: Int = ((fSqrt + mn) / dn).toInt
        (mn, dn, an)
    })

    cfStream.drop(3).map(_._3).takeWhile(_ != lastContinuedFraction) // TODO #:: lastContinuedFraction
  }
  continuedFractionPeriod(7).take(20).toList

}

case class CF(n: Int)(m: Int, d: Int, val a: Int) {

  def next(): CF = {
    val m1: Int = d * a - m
    val d1: Int = (n - m1 * m1) / d
    val a1: Int = math.floor((a + m1) / d1).toInt
    CF(n)(m1, d1, a1)
  }

}

case class CFs(m: Int, d: Int, a: Int) {

  def next(): CFs = {
    val m1: Int = d * a - m
    val d1: Int = (7 - m1 * m1) / d
    val a1: Int = ((math.floor(math.sqrt(7)) + m1) / d1).toInt
    CFs(m1, d1, a1)
  }

}
