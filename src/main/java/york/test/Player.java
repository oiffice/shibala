package york.test;

import lombok.Data;

@Data
public class Player {

    private String name;
    private int[] dices = new int[4];
    private ResultComment diceResult;
    private int points;

    public Player(String name) {
        this.name = name;
    }

    public void roll() {

        for (int i = 0; i < dices.length; i++) {
            dices[i] = Dice.roll();
        }
    }
}
