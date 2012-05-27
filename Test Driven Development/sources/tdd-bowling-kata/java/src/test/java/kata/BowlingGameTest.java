package kata;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BowlingGameTest {

    /**
     * should score zero on gutter game
     * should score twenty when all one
     * should score spare correctly
     * should score all spares correctly
     * should score strike correctly
     * should score super game
     */

    private BowlingGame game;

    @Before
    public void setUp() throws Exception {
        game = new BowlingGame();
    }

    @Test
    public void shouldScoreZeroOnGutterGame() {
        rollMany(20, 0);
        assertEquals(0, game.score());
    }

    @Test
    public void shouldScoreTwentyWhenAllOne() {
        rollMany(20, 1);
        assertEquals(20, game.score());
    }

    private void rollMany(int count, int pins) {
        for (int i = 0; i < count; i++) {
            game.roll(pins);
        }
    }

    @Test
    public void shouldScoreSpareCorrectly() {
        game.roll(6);
        game.roll(4);
        game.roll(3);
        rollMany(17, 0);
        assertEquals(16, game.score());
    }
}
