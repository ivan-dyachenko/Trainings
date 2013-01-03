Feature: Bowling Game score
  As an Gamer
  I want to calculate total score in bowling game
  So that I can know score when game is finished

  Background:
    Given a new bowling game

  Scenario: Gutter Game
    When all of my balls are landing in the gutter
    Then my total score should be 0

  Scenario: Beginners game
    When I roll 2 and 7
    And I roll 3 and 4
    And I roll 8 times 1 and 1
    Then my total score should be 32

  Scenario: All Spares
    When I roll 10 times 1 and 9
    And I roll a single 1
    Then my total score should be 110

  Scenario: All Strikes
    When all of my rolls are strikes
    Then my total score should be 300

  Scenario Outline: Specification by example
    When I roll the following series: <series>
    Then my total score should be <score>

  Examples:
    | series                                  | score |
    | 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 | 0     |
    | 2,7,3,4,1,1,5,1,1,1,1,1,1,1,1,1,1,1,5,1 | 40    |
    | 3,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 | 29    |
    | 5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5 | 145   |
    | 10,10,10,10,10,10,10,10,10,10,10,10     | 300   |


 
