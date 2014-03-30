package pep_057

object Solution {

  // numerator
  // http://oeis.org/A078057
  // a(0)=1
  // a(1)=3
  val a078057: Stream[BigInt] = 1 #:: 3 #:: (a078057 zip a078057.tail).map {
    // a(n) = 2*a(n-1) + a(n-2)
    case (a, b) => 2 * b + a
  }

  // denominators Stream
  // http://oeis.org/A215928
  // a(0) = a(1) = 1
  // a(2) = 2
  val a215928: Stream[BigInt] = 1 #:: 2 #:: (a215928 zip a215928.tail).map {
    // a(n) = 2*a(n-1) + a(n-2)
    case (a, b) => 2 * b + a
  }

  def solve(max: Int = 1000): Int = {
    val numeratorLengthStream = a078057.drop(1).map(_.toString().length)
    val denominatorLengthStream = a215928.drop(1).map(_.toString().length)

    numeratorLengthStream zip denominatorLengthStream take max count {
      case (n, d) => n > d
    }
  }
}
