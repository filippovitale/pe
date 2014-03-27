package pep_054


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
  //  Full House: Three of a kind and a pair.
  //  Four of a Kind: Four cards of the same value.
  //  Straight Flush: All cards are consecutive values of same suit.
  def rankCards(cards: Seq[Card]): Int = {
    val valuesSet = cards.map(_.value).to[Set]
    val suitesSet = cards.map(_.suit).to[Set]

    val isSameSuit = suitesSet.size == 1

    //  Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
    val isRoyalFlush = valuesSet.forall(Set(10, 11, 12, 13, 14).contains) && isSameSuit

    -1 // TODO
  }

  def solve() = {
    val firstGame = games.head
    -1 // TODO
  }
}
