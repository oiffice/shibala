import york.test.Player;
import york.test.Sibala;

public class ShibalaApplication {

    public static void main(String[] args) {

        Sibala sibala = new Sibala(new Player("P1"), new Player("P2"));
        sibala.letsPlay();
    }
}
