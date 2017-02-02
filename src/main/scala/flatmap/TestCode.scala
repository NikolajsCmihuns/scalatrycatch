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

  case class MobileProduct(name: String)

  val userId = Some("id")
  val password = Some("password")
  val email = Some("email@asd.com")

  def getAccountIdByUserId(userId: String): Option[String] = Some("123456")

  def getProductsByAccountId(accountId: String): Option[List[MobileProduct]] = {
    Some(List(MobileProduct("free calls"), MobileProduct("free 10GB")))
  }

  val productNames = for {
    userId <- userId
    accountId <- getAccountIdByUserId(userId)
    product <- getProductsByAccountId(accountId)
  } yield {
    product.map(_.name)
  }

  val productNamesFlatMap = userId flatMap { userId =>
    getAccountIdByUserId(userId) flatMap { accountId =>
      getProductsByAccountId(accountId) map { product =>
        product map { p => p.name} }}}

  println(productNames)
  println(productNamesFlatMap)



  println(List(1, 2, 3) map { x => List(x + 1) })
  println((List(1, 2, 3) map {x =>List(x + 1)}).flatten)
  println(List(1, 2, 3) flatMap { x => List(x + 1) })
  // println(List(1, 2, 3) flatMap { x =>  x + 2 }) won't compile

  def function1(s: String) = Some(s + "_func1")
  def function2(s: String) = Some(s + "_func2")
  def function3(s: String) = Some(s + "_func3")
  def function4(s: String) = Some(s + "_func4_end")

  (function1("value1") flatMap { value1 =>
    function2(value1) flatMap {resultFromFunc2 =>
      function3(resultFromFunc2) flatMap { resultFromFunc3 =>
        function4(resultFromFunc3) }}} ).foreach(println)

  (function1("value1") flatMap { value1 =>
    function2(value1) flatMap {resultFromFunc2 =>
      function3(resultFromFunc2) map { resultFromFunc3 =>
        function4(resultFromFunc3) }}} ).foreach(println)


  (function1("value1") map { value1 =>
    function2(value1) map {resultFromFunc2 =>
      function3(resultFromFunc2) map { resultFromFunc3 =>
        function4(resultFromFunc3) }}} ).foreach(println)


}
