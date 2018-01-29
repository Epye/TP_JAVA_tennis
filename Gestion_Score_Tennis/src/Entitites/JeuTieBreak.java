import java.util.HashMap;

public class JeuTieBreak implements Game{
    HashMap<Player, Score> currentScore;

    public JeuTieBreak(Player player1, Player player2) {
        currentScore = new HashMap<>();
        currentScore.put(player1, new ScoreNumber("0"));
        currentScore.put(player2, new ScoreNumber("0"));
    }

    @Override
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
    }
}
