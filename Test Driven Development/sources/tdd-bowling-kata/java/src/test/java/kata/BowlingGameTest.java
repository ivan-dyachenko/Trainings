package kata;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

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
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assertEquals(0, game.score());
    }

    @Test
    public void shouldScoreTwentyWhenAllOne() {
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score());
    }
}
