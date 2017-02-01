package flatmap

object TestCode extends App {

  val cardRank = List("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
  val cardDeck = List("1", "2", "3", "4", "5", "6", "7", "8")
  val cardSuit = List("♠", "♥", "♣", "♦")

  val allCardsWithForYield = for {
    suit <- cardSuit
    deck <- cardDeck
    rank <- cardRank
  } yield {
    rank + suit + deck
  }

  val allCardsWithFlatMap = cardSuit flatMap { suit =>
    cardDeck flatMap {deck =>
      cardRank map { rank => rank + suit + deck }
    }
  }

  println(allCardsWithForYield)
  println(allCardsWithFlatMap)

}
