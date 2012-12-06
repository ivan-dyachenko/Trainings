function Bowling() {
    this.pinsDown = [];
    this.throwNumber = 0;
}

Bowling.prototype.score = function(){
    var score = 0;
    var rollIndex = 0;
    for (var frame = 0; frame < 10; frame++) {

        if (this.isStrike(rollIndex)){
            score += 10 + this.strikeBonus(rollIndex);
            rollIndex += 1;
        }
        else if (this.isSpare(rollIndex)){
            score += 10 + this.spareBonus(rollIndex);
            rollIndex += 2;
        }
        else {
            score += this.sumOfTwoBallsInFrame(rollIndex);
            rollIndex += 2;
        }
    }
    return score;
};

Bowling.prototype.roll = function(pinsDown){
    this.pinsDown[this.throwNumber++] = pinsDown;
};

Bowling.prototype.sumOfTwoBallsInFrame = function(rollIndex) {
    return this.pinsDown[rollIndex] + this.pinsDown[rollIndex + 1];
};

Bowling.prototype.spareBonus = function (rollIndex) {
    return this.pinsDown[rollIndex + 2];
};

Bowling.prototype.strikeBonus = function (rollIndex) {
    return this.pinsDown[rollIndex + 1] + this.pinsDown[rollIndex + 2];
};

Bowling.prototype.isStrike = function (roll) {
    return this.pinsDown[roll] == 10;
};

Bowling.prototype.isSpare = function (roll) {
    return this.pinsDown[roll] + this.pinsDown[roll + 1] == 10;
};