#import "BowlingGameTests.h"
#import "BowlingGame.h"

@implementation BowlingGameTests {
    BowlingGame *game;
}

- (void)setUp
{
    [super setUp];

    game = [[BowlingGame alloc] init];
}

- (void)testGutter {
    [self rollMany:0 times:20];
    STAssertEquals(0, [game score], nil);
}

- (void)testTwentyWhenOne {
    [self rollMany:1 times:20];
    STAssertEquals(20, [game score], nil);
}

- (void)testScoreSpare {
    [self rollSpare];
    [game roll:3];
    STAssertEquals(16, [game score], nil);
}

- (void)testScoreStrike {
    [self rollStrike];
    [game roll:3];
    [game roll:4];
    STAssertEquals(24, [game score], nil);
}

- (void)testPerfectGame {
    [self rollMany:10 times:12];
    STAssertEquals(300, [game score], nil);
}

- (void)rollStrike {
    [game roll:10];
}

- (void)rollSpare {
    [game roll:5];
    [game roll:5];
}

- (void)rollMany:(int)pins times:(int)n {
    for (int i = 0; i < n; i++) {
        [game roll:pins];
    }
}

@end
