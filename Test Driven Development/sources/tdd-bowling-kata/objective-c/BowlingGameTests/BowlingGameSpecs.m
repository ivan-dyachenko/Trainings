#import "Kiwi.h"
#import "BowlingGame.h"

SPEC_BEGIN(BowlingGameSpecs)

describe(@"BowlingGame", ^{

    __block BowlingGame *game;

    __block void(^rollMany)(int, int);

    beforeEach(^{
        game = [[BowlingGame alloc] init];

        rollMany = ^(int times, int pins) {
            for (int i = 0; i < times; i++) {
                [game roll:pins];
            }
        };
    });

    it(@"should score zero on gutter game", ^{
        rollMany(20, 0);
        [[theValue([game score]) should] equal:theValue(0)];
    });

    it(@"should score twenty when all one", ^{
        rollMany(20, 1);
        [[theValue([game score]) should] equal:theValue(20)];
    });

    it(@"should score spare correctly", ^{
        [game roll:5];
        [game roll:5];
        [game roll:3];
        rollMany(17, 0);
        [[theValue([game score]) should] equal:theValue(16)];
    });

    it(@"should score strike correctly", ^{
        [game roll:10];
        [game roll:3];
        [game roll:4];
        rollMany(16, 0);
        [[theValue([game score]) should] equal:theValue(24)];
    });

    it(@"should score perfect game", ^{
        rollMany(12, 10);
        [[theValue([game score]) should] equal:theValue(300)];
    });
});

SPEC_END