package kata;

public class BowlingGame {

    private int score;
    private int rolls[] = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        score = 0;
        for (int i = 0; i < 10; i++) {
            score = rolls[i] + rolls[i+1];
            i += 2;
        }
        return score;
    }
}
