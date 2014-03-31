package pep_059

object Solution {

  val filename = "src/pep_059/cipher1.txt"
  val encodedStream = io.Source.fromFile(filename).mkString.trim.split(",").map(_.toInt).toStream

  def solve() = {
    val possiblePasswordDigits = encodedStream.groupBy(_.toInt).mapValues(_.length).toList.sortBy {
      case (_, s) => s
    }.reverse.take(10).map {
      case (c, _) => c ^ ' '.toInt
    }.map(_.toChar).filter(('a' to 'z').contains)

    val passwords = for {
      a <- possiblePasswordDigits
      b <- possiblePasswordDigits
      if a != b
      c <- possiblePasswordDigits
      if b != c
    } yield Seq(a, b, c)

    val decodeStreams: Seq[Stream[Int]] = for {
      p <- passwords
    } yield Stream.continually(p.toStream.map(_.toInt)).flatten

    val decodedTries = for {
      decodeStream <- decodeStreams
    } yield (encodedStream zip decodeStream).map {
        case (e, d) => e ^ d
      }

    decodedTries.filter(_.take(100).map(_.toChar).mkString.count(c => ('a' to 'z').contains(c) || ('A' to 'Z').contains(c) || c == ' ') > 90).head.sum
  }

}
