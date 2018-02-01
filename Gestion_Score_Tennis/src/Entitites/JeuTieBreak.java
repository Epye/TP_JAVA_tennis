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

    @Override
    public void updateWithPointWonBy(Player player) {
        Player otherPlayer = new Player("");
        currentScore.get(player).winPoint();
        for (HashMap.Entry<Player, Score> entry : currentScore.entrySet()) {
            Player key = entry.getKey();
            if(!key.getName().equals(player.getName())){
                otherPlayer = key;
            }
        }
        if(Integer.parseInt(currentScore.get(player).getScore()) >= 6 && Integer.parseInt(currentScore.get(player).getScore()) > Integer.parseInt(currentScore.get(otherPlayer).getScore()) -2){
            currentScore.get(player).setWin();
        }
    }
}
