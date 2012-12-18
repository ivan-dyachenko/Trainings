package kata

import spock.lang.Specification

class BowlingSpecification extends Specification {

    BowlingGame game

    def setup() {
        game = new BowlingGame()
    }

    def "should score zero on gutter game"() {
        when: rollMany 20, 0
        then: game.score() == 0
    }

    def "should score twenty when all one"() {
        when: rollMany 20, 1
        then: game.score() == 20
    }

    def "should score spare correctly"() {
        when:
        rollSpare()
        game.roll 3
        rollMany 17, 0

        then:
        game.score() == 16
    }

    def "should score strike correctly"() {
        when:
        rollStrike()
        game.roll 4
        game.roll 3
        rollMany 16, 0

        then:
        game.score() == 24
    }

    def "should score perfect game"() {
        when:
        rollMany 12, 10

        then:
        game.score() == 300
    }

    void rollMany(int n, int pins) {
        (1..n).each { game.roll pins }
    }

    void rollSpare() {
        game.roll 5
        game.roll 5
    }

    void rollStrike() {
        game.roll 10
    }

}