package pep_054

import org.specs2.mutable._

class HelloWorldSpec extends Specification {

  "Example games" should {
    "be won by player 1" in {
      pep_054.Game("5D 8C 9S JS AC 2C 5C 7D 8S QH").hasPlayer1Won mustEqual true
      pep_054.Game("4D 6S 9H QH QC 3D 6D 7H QD QS").hasPlayer1Won mustEqual true
      pep_054.Game("2H 2D 4C 4D 4S 3C 3D 3S 9S 9D").hasPlayer1Won mustEqual true
    }
    "be won by player 2" in {
      pep_054.Game("5H 5C 6S 7S KD 2C 3S 8S 8D TD").hasPlayer1Won mustEqual false
      pep_054.Game("2D 9C AS AH AC 3D 6D 7D TD QD").hasPlayer1Won mustEqual false
    }
  }
}
