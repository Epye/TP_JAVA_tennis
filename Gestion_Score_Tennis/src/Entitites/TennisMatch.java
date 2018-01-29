import java.util.HashMap;

public class TennisMatch {
    private Player player1;
    private Player player2;
    private MatchType matchType;
    private boolean tieBreakInLastSet;
    private HashMap<Integer, Score> gameScorePlayer1;
    private HashMap<Integer, Score> gameScorePlayer2;
    private Game game;

    public TennisMatch(Player player1, Player player2, MatchType matchType, boolean tieBreakInLastSet) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchType = matchType;
        this.tieBreakInLastSet = tieBreakInLastSet;
        gameScorePlayer1 = new HashMap<>();
        gameScorePlayer2 = new HashMap<>();
        gameScorePlayer1.put(1, new ScoreNumber("0"));
        gameScorePlayer2.put(1, new ScoreNumber("0"));
        game = new Jeu(player1, player2);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public Game getGame() {
        return game;
    }

    /***
     * Met à jour les points d'un joueur
     * @param player
     */
    public void updateWithPointWonBy(Player player) {
        game.updateWithPointWonBy(player);
        isFinished();
    }

    /***
     * Retourne le score du joueur
     * @param player
     */
    public String pointsForPlayer(Player player) {
        return game.getCurrentScorePlayer(player);
    }

    public int currentSetNumber() {
        return 1;
    }

    public int gamesInCurrentSetForPlayer(Player player) {
        return 1;
    }

    public int gamesInSetForPlayer(int setNumber, Player player) {
        return 1;
    }

    public boolean isFinished() {
        if(pointsForPlayer(player1) == "WIN"){
            gameScorePlayer1.get(gameScorePlayer1.size()).winPoint();
            System.out.println(player1.getName() + " gagne le jeu");
            resetGame();
        }
        return false;
    }

    private void resetGame(){
        if(ballDeMatch()){
            System.out.println("BAAAAAAAALLLLLLE DE MAAAAAAATCH");
            game = new JeuTieBreak(player1, player2);
        }else{
            System.out.println("Jeu Décisif");
            game = new Jeu(player1, player2);
        }
    }

    private boolean ballDeMatch(){
        if(tieBreakInLastSet){
            if(gameScorePlayer1.size() == matchType.numberOfSetsToWin()-1 && gameScorePlayer2.size() == matchType.numberOfSetsToWin()-1){
                if(gameScorePlayer1.get(gameScorePlayer1.size()).getScore() == "6" && gameScorePlayer2.get(gameScorePlayer2.size()).getScore() == "6"){
                    return true;
                }
            }
        }
        return false;
    }

}

