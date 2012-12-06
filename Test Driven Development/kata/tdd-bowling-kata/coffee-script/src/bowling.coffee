class Bowling

    constructor: ->
        @rolls = []

    roll: (pins) ->
        @rolls.push pins

    score: ->
        score = 0
        current_frame = 0
        for frame in [0..9]
            if @isStrike(current_frame)
                score += @strikeBonus(current_frame)
                current_frame += 1

            else if @isSpare(current_frame)
                score += @spareBonus(current_frame)
                current_frame += 2

            else
                score += @regularScore(current_frame)
                current_frame += 2

        score

    isStrike: (current_frame) ->
        @rolls[current_frame] is 10

    isSpare: (current_frame) ->
        @rolls[current_frame] + @rolls[current_frame + 1] is 10

    strikeBonus: (current_frame) ->
        10 + @rolls[current_frame + 1] + @rolls[current_frame + 2]

    spareBonus: (current_frame) ->
        10 + @rolls[current_frame + 2]

    regularScore: (current_frame) ->
        @rolls[current_frame] + @rolls[current_frame + 1]

exports.Bowling = -> new Bowling()