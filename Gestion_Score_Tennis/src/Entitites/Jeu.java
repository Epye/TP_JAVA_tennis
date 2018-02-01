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

    @Override
    public void updateWithPointWonBy(Player player) {
        Player otherPlayer = new Player("");
        for (HashMap.Entry<Player, Score> entry : currentScore.entrySet()) {
            Player key = entry.getKey();
            if(!key.getName().equals(player.getName())){
                otherPlayer = key;
            }
        }
        if(currentScore.get(otherPlayer).getScore().equals("A")){
            currentScore.get(otherPlayer).loosePoint();
        }else{
            currentScore.get(player).winPoint();
        }
        if(currentScore.get(player).getScore().equals("A") && !currentScore.get(otherPlayer).getScore().equals("40")){
            currentScore.get(player).winPoint();
        }
    }
}
