package york.test;

import org.junit.Assert;
import org.junit.Test;
import york.test.Player;

public class PlayerTest {

    @Test
    public void playerDicesBeforeRoll() {
        Player player = new Player("p1");

        int[] dices = player.getDices();

        Assert.assertEquals(4, dices.length);

        for (int point : dices) {
            Assert.assertEquals(0, point);
        }

    }

    @Test
    public void playerDicesAfterRoll() {

        Player player = new Player("p1");
        player.roll();

        int[] dices = player.getDices();

        for (int point : dices) {
            Assert.assertTrue(point > 0);
            Assert.assertTrue(point < 7);
        }
    }
}
