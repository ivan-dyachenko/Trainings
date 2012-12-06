import org.scalatest.junit.JUnitRunner
import org.scalatest._
import org.scalatest.matchers._
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class BowlingGameTest extends FunSuite with ShouldMatchers with BeforeAndAfter {

  var game: BowlingGame = null

  def rollMany(n: Int, pins: Int) { for (i <- 1 to n) game roll (pins) }

  def rollSpare() {
    game roll 5
    game roll 5
  }

  def rollStrike() { game roll 10 }

  before {
    game = new BowlingGame()
  }

  test("should score zero on gutter game") {
    rollMany(20, 0)
    (game score) should be(0)
  }

  test("should score twenty when all one") {
    rollMany(20, 1)
    (game score) should be(20)
  }

  test("should score spare correctly") {
    rollSpare()
    game roll 3
    rollMany(17, 0)
    (game score) should be(16)
  }

  test("should score strike correctly") {
    rollStrike()
    game roll 3
    game roll 4
    rollMany(16, 0)
    (game score) should be(24)
  }

  test("should score perfect game") {
    rollMany(12, 10)
    (game score) should be(300)
  }

}