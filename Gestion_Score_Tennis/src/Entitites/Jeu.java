import java.util.HashMap;

public class Jeu implements Game {
    HashMap<Player, Score> currentScore;

    public Jeu(Player player1, Player player2) {
        currentScore = new HashMap<>();
        currentScore.put(player1, new ScoreJeu("0"));
        currentScore.put(player2, new ScoreJeu("0"));
    }

    public String getCurrentScorePlayer(Player player) {
        return currentScore.get(player).getScore();
    }

    /***
     * Met à jour les points d'un joueur
     * @param player
     */

    @Override
    public void updateWithPointWonBy(Player player) {
        currentScore.get(player).winPoint();
        for (HashMap.Entry<Player, Score> entry : currentScore.entrySet()) {
            Player key = entry.getKey();
            currentScore.get(key).loosePoint();

        }
    }
}
