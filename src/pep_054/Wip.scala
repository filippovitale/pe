package pep_054

case class Game(line: String) {
  val (player1Cards, player2Cards) = line.split(" ").map(Card).splitAt(5)

  def hasPlayer1Won: Boolean = rankCards(player1Cards) > rankCards(player2Cards)

  def rankCards(cards: Seq[Card]): Long = {
    val values = cards.map(_.value)
    val valuesSet = values.to[Set]
    val valuesSorted = values.sorted
    val suitesSet = cards.map(_.suit).to[Set]
    val valuesGrouped = values.groupBy(_.toInt).mapValues(_.size) // TODO why I need .toInt?!???!

    //  Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    val allCardsOfTheSameSuit = suitesSet.size == 1
    val isRoyalFlush = valuesSet == Set(10, 11, 12, 13, 14) && allCardsOfTheSameSuit

    //  Straight Flush: All cards are consecutive values of same suit.
    val allCardsAreConsecutiveValues = valuesSorted.map(_ - valuesSorted.head) == Set(0, 1, 2, 3, 4)
    val isStraightFlush = allCardsAreConsecutiveValues && allCardsOfTheSameSuit
    val highestCard = valuesSorted.last

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
    val flushHighestCard = highestCard

    //  Straight: All cards are consecutive values.
    val isStraight = allCardsAreConsecutiveValues
    val straightHighestCard = highestCard

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

    var rank: Long = if (isRoyalFlush) 1 else 0

    rank <<= 4
    rank += {
      if (isStraightFlush) highestCard else 0
    }

    rank <<= 4
    rank += {
      if (isFourOfAKind) fourOfAKindValue else 0
    }

    rank <<= 8
    rank += {
      if (isFullHouse) {
        (fullHouseThreeValue << 4) + fullHousePairValue
      } else 0
    }

    rank <<= 4
    rank += {
      if (isFlush) flushHighestCard else 0
    }

    rank <<= 4
    rank += {
      if (isStraight) straightHighestCard else 0
    }

    rank <<= 8
    rank += {
      if (isThreeOfAKind) {
        (threeOfAKindValue << 4) + threeOfAKindHighests
      } else 0
    }

    rank <<= 12
    rank += {
      if (isTwoPair) {
        (twoPairValues.head << 8) + (twoPairValues.last << 4) + (valuesSet -- twoPairValues.to[Set]).max
      } else 0
    }

    rank <<= 4
    rank += {
      if (isOnePair) onePairValue.head else 0
    }

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

case class Card(s: String) {
  val value: Int = "  23456789TJQKA".indexOf(s.head)
  val suit: Char = s.last
}

object Wip {

  def solve() = {
    val filename = "src/pep_054/poker.txt"
    val games = io.Source.fromFile(filename).getLines().toStream.map(Game)

    games.count(_.hasPlayer1Won)

    // 379, 621 is wrong
  }
}

