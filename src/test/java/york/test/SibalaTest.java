package york.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import york.test.Player;
import york.test.Sibala;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class SibalaTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void sibalaNoPlayersPlay() {
        Sibala sibala = new Sibala();
        sibala.play();
        Assert.assertEquals("There are no more than one players\n", outContent.toString());
    }

    @Test
    public void sibalaTwoPlayers() {
        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Sibala sibala = new Sibala(p1, p2);
        sibala.play();

        List<Player> playerList = sibala.getPlayers();

        Assert.assertEquals(2, playerList.size());

        playerList.forEach(player -> {

            int[] dices = player.getDices();
            for (int i = 0; i < dices.length; i++) {
                Assert.assertTrue(dices[i] > 0);
                Assert.assertTrue(dices[i] < 7);
            }
        });
    }

    @Test
    public void testResultCommentAndPointsCorrectly() {

        Player p1 = new Player("p1");
        p1.setDices(new int[]{1,1,1,1});

        Player p2 = new Player("p2");
        p2.setDices(new int[]{1,2,3,1});

        Sibala sibala = new Sibala(p1, p2);
        sibala.showResult();

        List<Player> players = sibala.getPlayers();

        Assert.assertEquals(ResultComment.SAME_COLOR, players.get(0).getDiceResult());
        Assert.assertEquals(ResultComment.N_POINTS, players.get(1).getDiceResult());

    }

    @Test
    public void testResultPointsCorrectly() {

        Player p1 = new Player("p1");
        p1.setDices(new int[]{1,5,1,2});

        Player p2 = new Player("p2");
        p2.setDices(new int[]{1,2,3,1});

        Sibala sibala = new Sibala(p1, p2);
        sibala.showResult();

        List<Player> players = sibala.getPlayers();

        Assert.assertEquals(ResultComment.N_POINTS, players.get(0).getDiceResult());
        Assert.assertEquals(7, players.get(0).getPoints());
        Assert.assertEquals(ResultComment.N_POINTS, players.get(1).getDiceResult());
        Assert.assertEquals(5, players.get(1).getPoints());

    }

    @Test
    public void testResultTwoPairPointsCorrectly() {

        Player p1 = new Player("p1");
        p1.setDices(new int[]{1,5,1,5});

        Player p2 = new Player("p2");
        p2.setDices(new int[]{1,2,2,1});

        Sibala sibala = new Sibala(p1, p2);
        sibala.showResult();

        List<Player> players = sibala.getPlayers();

        Assert.assertEquals(ResultComment.N_POINTS, players.get(0).getDiceResult());
        Assert.assertEquals(10, players.get(0).getPoints());
        Assert.assertEquals(ResultComment.N_POINTS, players.get(1).getDiceResult());
        Assert.assertEquals(4, players.get(1).getPoints());

    }

    @Test
    public void winBySamePoints() {

        Player p1 = new Player("p1");
        p1.setDices(new int[]{1,1,1,1});

        Player p2 = new Player("p2");
        p2.setDices(new int[]{1,2,3,1});

        Sibala sibala = new Sibala(p1, p2);

        sibala.showResult();
        sibala.findWinner();

        Assert.assertEquals("p1 win, because of [1, 1, 1, 1]\n", outContent.toString());

    }

    @Test
    public void drawByBothSamePoints() {

        Player p1 = new Player("p1");
        p1.setDices(new int[]{1,1,1,1});

        Player p2 = new Player("p2");
        p2.setDices(new int[]{2,2,2,2});

        Sibala sibala = new Sibala(p1, p2);

        sibala.showResult();
        sibala.findWinner();

        Assert.assertEquals("Draw\n", outContent.toString());

    }

    @Test
    public void drawBySamePoints() {

        Player p1 = new Player("p1");
        p1.setDices(new int[]{3,1,1,3});

        Player p2 = new Player("p2");
        p2.setDices(new int[]{2,1,2,5});

        Sibala sibala = new Sibala(p1, p2);

        sibala.showResult();
        sibala.findWinner();

        Assert.assertEquals("Draw\n", outContent.toString());

    }

    @Test
    public void drawBySamePointsII() {

        Player p1 = new Player("p1");
        p1.setDices(new int[]{1,2,3,4});

        Player p2 = new Player("p2");
        p2.setDices(new int[]{2,3,4,5});

        Sibala sibala = new Sibala(p1, p2);

        sibala.showResult();
        sibala.findWinner();

        Assert.assertEquals("Draw\n", outContent.toString());

    }
}
