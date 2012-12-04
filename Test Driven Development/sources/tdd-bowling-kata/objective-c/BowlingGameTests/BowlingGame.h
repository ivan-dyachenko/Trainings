#import <Foundation/Foundation.h>


@interface BowlingGame : NSObject {
    int currentRoll;
    int rolls[21];
}

- (int)score;

- (void)roll:(int)pins;

@end