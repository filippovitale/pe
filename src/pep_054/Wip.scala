package pep_054

import scala.::


case class Game(line: String) {
  val (player1Cards, player2Cards) = line.split(" ").map(Card).splitAt(5)
}

case class Card(s: String) {
  val value: Int = "  23456789TJQKA".indexOf(s.head)
  val suit: Char = s.last
}

object Wip {

  val filename = "src/pep_054/poker.txt"
  val games = io.Source.fromFile(filename).getLines().toStream.map(Game)

  // TODO
  //  High Card: Highest value card.
  //  One Pair: Two cards of the same value.
  //  Two Pairs: Two different pairs.
  //  Three of a Kind: Three cards of the same value.
  //  Straight: All cards are consecutive values.
  //  Flush: All cards of the same suit.
  def rankCards(cards: Seq[Card]) = {
    val values = cards.map(_.value)
    val valuesSet = values.to[Set]
    val valuesSorted = values.sorted
    val suitesSet = cards.map(_.suit).to[Set]

    val isSameSuit = suitesSet.size == 1

    //  Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    val isRoyalFlush = valuesSet == Set(10, 11, 12, 13, 14) && isSameSuit

    //  Straight Flush: All cards are consecutive values of same suit.
    val isStraightFlush = valuesSorted.map(_ - valuesSorted.head) == Set(0, 1, 2, 3, 4) && isSameSuit

    //  Four of a Kind: Four cards of the same value.
    val (isFourOfAKind, fourOfAKindValue) = valuesSorted.groupBy(_.toString).mapValues(_.size).maxBy(_._2) match {
      case (v, n) if n == 4 => (true, v)
      case _ => (false, 0)
    }

    //  Full House: Three of a kind and a pair.
    val fullHouseSeq = Seq(3, 2) flatMap valuesSorted.groupBy(_.toString).mapValues(_.size).map(_.swap).get
    val (isFullHouse, fullHouseThreeValue, fullHousePairValue) = if (fullHouseSeq.size == 2) {
      (true, fullHouseSeq.head, fullHouseSeq.last)
    } else (false, "0", "0")

    (isRoyalFlush, isStraightFlush, isFourOfAKind, fourOfAKindValue, isFullHouse, fullHouseThreeValue, fullHousePairValue)
  }

  def solve() = {
    val firstGame = games.head

    import scala.math.Ordering.Implicits._
    var x = (true, false) <(false, true)

    -1 // TODO
  }
}
