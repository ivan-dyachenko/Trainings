should  = require "should"
game = require "../src/bowling"

describe "Bowling", ->

    bowling = null

    rollMany = (num, pins) ->
        bowling.roll(pins) for x in [0..num-1]

    beforeEach ->
        bowling = game.Bowling()

    it "should score zero on gutter game", ->
        rollMany 20, 0
        bowling.score().should.equal 0

    it "should score twenty when all one", ->
        rollMany 20, 1
        bowling.score().should.equal 20

    it "should calculate correctly with one spare", ->
        bowling.roll 5
        bowling.roll 5
        bowling.roll 3
        rollMany 17, 0
        bowling.score().should.equal 16

    it "should calculate correctly with one strike", ->
        bowling.roll 10
        bowling.roll 3
        bowling.roll 4
        rollMany 17, 0
        bowling.score().should.equal 24

    it "should calculate a perfect game", ->
        rollMany 12, 10
        bowling.score().should.equal 300