package york.test;

import org.junit.Assert;
import org.junit.Test;
import york.test.Dice;

public class DiceTest {

    @Test
    public void legalRoll() {
        int roll1 = Dice.roll();
        int roll2 = Dice.roll();
        int roll3 = Dice.roll();
        int roll4 = Dice.roll();

        Assert.assertTrue(roll1 > 0);
        Assert.assertTrue(roll1 < 7);
        Assert.assertTrue(roll2 > 0);
        Assert.assertTrue(roll2 < 7);
        Assert.assertTrue(roll3 > 0);
        Assert.assertTrue(roll3 < 7);
        Assert.assertTrue(roll4 > 0);
        Assert.assertTrue(roll4 < 7);

    }
}
