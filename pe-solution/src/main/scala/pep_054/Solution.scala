package pep_054

case class Game(line: String) {

  val (player1Cards, player2Cards) = line.split(" ").map(Card).splitAt(5) match {
    case (p1, p2) => (Cards(p1), Cards(p2))
  }

  def hasPlayer1Won: Boolean = player1Cards.rank > player2Cards.rank

}

case class Card(s: String) {
  val value: Int = "  23456789TJQKA".indexOf(s.head)
  val suit: Char = s.last
}

case class Cards(cards: Array[Card]) {

  val values = cards.map(_.value)
  val valuesSet = values.to[Set]
  val valuesSorted = values.sorted.toSeq
  val valuesGrouped = values.groupBy(_.toInt).mapValues(_.size)
  val valueLowestCard = valuesSorted.head
  val valueHighestCard = valuesSorted.last
  val valuesAreConsecutives = valuesSorted.map(_ - valueLowestCard) == Seq(0, 1, 2, 3, 4)

  val suitesSet = cards.map(_.suit).to[Set]
  val allCardsOfTheSameSuit = suitesSet.size == 1

  //  Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
  val isRoyalFlush =  valuesAreConsecutives && valueHighestCard == 14 && allCardsOfTheSameSuit

  //  Straight Flush: All cards are consecutive values of same suit.
  val isStraightFlush = valuesAreConsecutives && allCardsOfTheSameSuit

  //  Four of a Kind: Four cards of the same value.
  val (isFourOfAKind, fourOfAKindValue) = valuesGrouped.maxBy(_._2) match {
    case (v, n) if n == 4 => (true, v)
    case _ => (false, 0)
  }

  //  Full House: Three of a kind and a pair.
  val fullHouseSeq = Seq(3, 2) flatMap valuesGrouped.map(_.swap).get
  val (isFullHouse, fullHouseThreeValue, fullHousePairValue) = if (fullHouseSeq.size == 2) {
    (true, fullHouseSeq.head, fullHouseSeq.last)
  } else (false, 0, 0)

  //  Flush: All cards of the same suit.
  val isFlush = allCardsOfTheSameSuit
  val flushHighestCard = valueHighestCard

  //  Straight: All cards are consecutive values.
  val isStraight = valuesAreConsecutives
  val straightHighestCard = valueHighestCard

  //  Three of a Kind: Three cards of the same value.
  val threeOfAKindOption = (Seq(3) flatMap valuesGrouped.map(_.swap).get).headOption
  val (isThreeOfAKind, threeOfAKindValue, threeOfAKindHighests) = threeOfAKindOption match {
    case Some(t) => (true, t, (valuesSet - t).max)
    case None => (false, 0, 0)
  }

  //  Two Pairs: Two different pairs.
  val isTwoPair = valuesGrouped.count {
    case (_, n) => n == 2
  } == 2
  val twoPairValues = valuesGrouped.filter({
    case (_, n) => n == 2
  }).keys.toSeq.sorted.reverse

  //  One Pair: Two cards of the same value.
  val isOnePair = valuesGrouped.count {
    case (_, n) => n == 2
  } == 1
  val onePairValue = valuesGrouped.filter({
    case (_, n) => n == 2
  }).keys.toSeq.sorted.reverse

  //  High Card: Highest value card.
  // done!

  def rank: Long = {

    var rank: Long = if (isRoyalFlush) 1 else 0

    rank <<= 4
    if (isStraightFlush) rank += valueHighestCard

    rank <<= 4
    if (isFourOfAKind) rank += fourOfAKindValue

    rank <<= 8
    if (isFullHouse) rank += (fullHouseThreeValue << 4) + fullHousePairValue

    rank <<= 4
    if (isFlush) rank += flushHighestCard

    rank <<= 4
    if (isStraight) rank += straightHighestCard

    rank <<= 8
    if (isThreeOfAKind) rank += (threeOfAKindValue << 4) + threeOfAKindHighests

    rank <<= 12
    if (isTwoPair) rank += (twoPairValues.head << 8) + (twoPairValues.last << 4) + (valuesSet -- twoPairValues.to[Set]).max

    rank <<= 4
    if (isOnePair) rank += onePairValue.head

    rank <<= 4
    rank += valuesSorted(4)

    rank <<= 4
    rank += valuesSorted(3)

    rank <<= 4
    rank += valuesSorted(2)

    rank <<= 4
    rank += valuesSorted(1)

    rank <<= 4
    rank += valuesSorted(0)

    rank
  }

}

object Solution {

  def solve() = {
    val filename = "src/pep_054/poker.txt"
    val games = io.Source.fromFile(filename).getLines().toStream.map(Game)

    games.count(_.hasPlayer1Won)
  }
}

