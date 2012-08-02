package conveyor;

import java.util.Random;

public class Dice {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 6;

    private final Random random = new Random();

    public int roll() {
        return MIN_VALUE + random.nextInt(MAX_VALUE - MIN_VALUE + 1);
    }

}
