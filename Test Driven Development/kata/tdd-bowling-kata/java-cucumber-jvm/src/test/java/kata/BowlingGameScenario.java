package kata;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;

import static org.junit.Assert.assertEquals;

public class BowlingGameScenario {

    BowlingGame game;

    @Given("^a new bowling game$")
    public void createGame() {
        game = new BowlingGame();
    }

    @When("^all of my balls are landing in the gutter$")
    public void allOfMyBallsAreLandingInTheGutter() {
        for (int i = 0; i < 20; i++)
            game.roll(0);
    }

    @When("^all of my rolls are strikes$")
    public void allOfMyRollsAreStrikes() {
        for (int i = 0; i < 12; i++)
            game.roll(10);
    }

    @When("^I roll a single (\\d+)$")
    public void oneRoll(int val) {
        game.roll(val);
    }

    @When("^I roll (\\d+) times (\\d+) and (\\d+)$")
    public void twoRollsWithTimes(int times, int val1, int val2) {
        for (int i = 0; i < times; i++) {
            game.roll(val1);
            game.roll(val2);
        }
    }

    @When("^I roll (\\d+) and (\\d+)$")
    public void twoRolls(int val1, int val2) {
        game.roll(val1);
        game.roll(val2);
    }

    @When("^I roll the following series: (.+)$")
    public void oneRoll(String series) {
        for (String roll : series.split(","))
            game.roll(Integer.parseInt(roll.trim()));
    }

    @Then("^my total score should be (\\d+)$")
    public void myTotalScoreShouldBe(int score) {
        assertEquals(score, game.score());
    }

}
