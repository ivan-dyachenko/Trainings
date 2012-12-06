class BowlingGame {

  var rolls: List[Int] = List()

  def roll(pins: Int) { rolls = rolls :+ pins }

  def score: Int = (convert(rolls) take 20).sum

  def convert(list: List[Int]): List[Int] = {
    list match {
      case 10 :: x :: y :: tail => 10 :: (x + y) :: convert(x :: y :: tail)
      case x :: y :: z :: tail if (x + y == 10) => 10 :: z :: convert(z :: tail)
      case x :: tail => x :: convert(tail)
      case nil => Nil
    }
  }
}
