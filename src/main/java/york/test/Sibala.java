package york.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Data
public class Sibala {

    private List<Player> players;
    private final int MINIMUM_PLAYERS = 2;
    private static final String NO_PLAYER_ERROR = "There are no more than one players";
    private String WINNER_STRING_FORMAT = "%s win, because of %s";
    private String DICES_NUMBER_OF_PLAYER = "After rolling %s got %s";

    public Sibala() {
        this.players = new ArrayList<>();
    }

    public Sibala(List<Player> players) {
        this.players = players;
    }

    public Sibala(Player... players) {

        this.players = new ArrayList<>();
        this.players.addAll(Arrays.asList(players));

    }

    public void letsPlay() {
        this.play();
        this.showResult();
        this.findWinner();
    }

    public void play() {

        if (Objects.isNull(this.players) || this.players.size() < MINIMUM_PLAYERS) {
            this.showMessage(NO_PLAYER_ERROR);
            return;
        }

        // roll the dices
        players.forEach(Player::roll);

        players.forEach(player -> {
            this.showMessage(String.format(DICES_NUMBER_OF_PLAYER, player.getName(), Arrays.toString(player.getDices())));
        });

    }

    public void showResult() {
        
        players.forEach(player -> {

            int[] dices = player.getDices();
            if (this.isSamePointsResult(dices)) {
                player.setDiceResult(ResultComment.SAME_COLOR);
                player.setPoints(dices[0]);
            } else if (this.isTwelve(dices)) {
                player.setDiceResult(ResultComment.TWELVE);
                player.setPoints(12);
            } else {
                int point = this.getPoint(dices);
                player.setPoints(point);
                if (point == 0) {
                    player.setDiceResult(ResultComment.NO_POINTS);
                } else {
                    player.setDiceResult(ResultComment.N_POINTS);
                }
            }

        });

    }

    public void findWinner() {

        for (int i = 1; i < players.size(); i++) {

            if (players.get(i).getDiceResult().getLevelCode() > players.get(i - 1).getDiceResult().getLevelCode()) {
                players.remove(players.get(i - 1));
            } else if (players.get(i).getDiceResult().getLevelCode() < players.get(i - 1).getDiceResult().getLevelCode()) {
                players.remove(players.get(i));
            } else if (players.get(i).getDiceResult().getLevelCode() < 3) {

                if (players.get(i).getPoints() > players.get(i-1).getPoints()) {
                    players.remove(players.get(i - 1));
                } else if (players.get(i).getPoints() < players.get(i-1).getPoints()) {
                    players.remove(players.get(i));
                }
            }
        }


        String msg;
        if (players.size() < 2) {
            msg = String.format(WINNER_STRING_FORMAT, players.get(0).getName(), Arrays.toString(players.get(0).getDices()));
        } else {
            msg = "Draw";
        }

        this.showMessage(msg);

    }

    private boolean isSamePointsResult(int[] dices) {

        for (int i = 1; i < dices.length; i++) {
            if (dices[i] != dices[i - 1]) {
                return false;
            }
        }

        return true;

    }
    
    private boolean isTwelve(int[] dices) {
        
        int sixCount = 0;
        
        for (int i = 0; i < dices.length; i++) {
            if (dices[i] == 6) {
                sixCount++;
            }
        }
        
        return (sixCount == 4);
    }

    private int getPoint(int[] dices) {
        
        int[] pointCount = new int[6];
        
        for (int point : dices) {
            
            pointCount[point - 1]++;
            
        }
        
        int pairPointSum = 0;
        int regularPointSum = 0;
        int twoPairCount = 0;
        
        for (int pointAsIndex = 0; pointAsIndex < pointCount.length; pointAsIndex++) {
            
            if (pointCount[pointAsIndex] == 2) {
                 pairPointSum = (pointAsIndex + 1) >= pairPointSum ? (pointAsIndex + 1) : pairPointSum;
                 twoPairCount++;
            } else if (pointCount[pointAsIndex] == 1) {
                regularPointSum += (pointAsIndex + 1);
            } else if (pointCount[pointAsIndex] == 0) {
                continue;
            }
        }

        if (twoPairCount == 2) {
            return pairPointSum * 2;
        } else if (twoPairCount == 1) {
            return regularPointSum;
        } else {
            return 0;
        }
        
    }
    
    private void showMessage(String msg) {
        System.out.println(msg);
    }
}
