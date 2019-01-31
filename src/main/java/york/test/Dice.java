package york.test;

import java.util.Random;

public class Dice {

    private static Random random = new Random();
    private static final int NUMBER_OF_DICE_PHASE = 6;
    private static final int MINIMUM_NUMBER = 1;

    public static int roll() {
        return random.nextInt(NUMBER_OF_DICE_PHASE) + MINIMUM_NUMBER;
    }
}
