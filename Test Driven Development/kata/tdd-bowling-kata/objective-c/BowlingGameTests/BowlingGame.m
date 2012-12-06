#import "BowlingGame.h"

@implementation BowlingGame

- (int)score {
    int score = 0;
    int ballIndex = 0;
    for (int frame = 0; frame < 10; frame++) {
        if ([self isStrike:ballIndex]) {
            score += 10 + [self strikeBonus:ballIndex];
            ballIndex++;
        }
        else if ([self isSpare:ballIndex]) {
            score += 10 + [self spareBonus:ballIndex];
            ballIndex += 2;
        }
        else {
            score += [self sumOfBalls:ballIndex];
            ballIndex += 2;
        }
    }
    return score;
}

- (int)sumOfBalls:(int)ballIndex {
    return rolls[ballIndex] + rolls[ballIndex + 1];
}

- (int)spareBonus:(int)ballIndex {
    return rolls[ballIndex + 2];
}

- (int)strikeBonus:(int)ballIndex {
    return rolls[ballIndex + 1] + rolls[ballIndex + 2];
}

- (BOOL)isStrike:(int)ballIndex {
    return rolls[ballIndex] == 10;
}

- (BOOL)isSpare:(int)currentFrame {
    return rolls[currentFrame] + rolls[currentFrame + 1] == 10;
}

- (void)roll:(int)pins {
    rolls[currentRoll++] = pins;
}

@end