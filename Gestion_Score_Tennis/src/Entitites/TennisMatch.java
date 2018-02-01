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

    public void updateWithPointWonBy(Player player) {
        if(!endMatch()) {
            game.updateWithPointWonBy(player);
            isFinished();
        }
    }

    public String pointsForPlayer(Player player) {
        return game.getCurrentScorePlayer(player);
    }

    public int currentSetNumber() {
        return gameScorePlayer1.size();
    }

    public int gamesInCurrentSetForPlayer(Player player) {
        if(player.getName().equals(player1.getName())){
            return Integer.parseInt(gameScorePlayer1.get(gameScorePlayer1.size()).getScore());
        } else {
            return Integer.parseInt(gameScorePlayer2.get(gameScorePlayer2.size()).getScore());
        }
    }

    public int gamesInSetForPlayer(int setNumber, Player player) {
        if(player.getName().equals(player1.getName())){
            return Integer.parseInt(gameScorePlayer1.get(setNumber).getScore());
        } else {
            return Integer.parseInt(gameScorePlayer2.get(setNumber).getScore());
        }
    }

    public boolean isFinished() {
        if(!endMatch()) {
            if (pointsForPlayer(player1) == "WIN") {
                gameScorePlayer1.get(gameScorePlayer1.size()).winPoint();
                System.out.println(player1.getName() + " gagne le jeu");
                afficheScore();
                if (!endMatch()) {
                    resetGame();
                } else {
                    System.out.println("Félicitation !");
                }
            } else if (pointsForPlayer(player2) == "WIN") {
                gameScorePlayer2.get(gameScorePlayer2.size()).winPoint();

                System.out.println(player2.getName() + " gagne le jeu");

                afficheScore();
                if (!endMatch()) {
                    resetGame();
                } else {
                    System.out.println("Félicitation !");
                }
            }
            return false;
        }else{
            return true;
        }
    }

    private void afficheScore(){
        String score = "Score actuel : \n" + player1.getName() + "\n";
        for (HashMap.Entry<Integer, Score> entry : gameScorePlayer1.entrySet()) {
            score += "Set " + entry.getKey() + " - " + entry.getValue().getScore() + "\n";
        }
        score += player2.getName() + "\n";
        for (HashMap.Entry<Integer, Score> entry : gameScorePlayer2.entrySet()) {
            score += "Set " + entry.getKey() + " - " + entry.getValue().getScore() + "\n";
        }
        System.out.println(score);
    }

    private void resetGame(){
        if(balleDeMatch()){
            System.out.println("TIIIIIIIIIIE BREAAAAAAAAK");
            game = new JeuTieBreak(player1, player2);
        }else{
            if(winSetPlayer1()){
                System.out.println(player1.getName() + " gagne le set !");
                newSet();
                afficheScore();
            } else if (winSetPlayer2()){
                System.out.println(player2.getName() + " gagne le set !");
                newSet();
                afficheScore();
            }
            System.out.println("Jeu Suivant");
            game = new Jeu(player1, player2);
        }
    }

    private boolean winSetPlayer1(){
        return gamesInCurrentSetForPlayer(player1) >= 6 && gamesInCurrentSetForPlayer(player1) >= gamesInCurrentSetForPlayer(player2) - 2;
    }

    private boolean winSetPlayer2(){
        return gamesInCurrentSetForPlayer(player2) >= 6 && gamesInCurrentSetForPlayer(player2) >= gamesInCurrentSetForPlayer(player1) - 2;
    }

    private boolean setWinforPlayer1(){
        int nbWin = 0;
        for (HashMap.Entry<Integer, Score> entry : gameScorePlayer1.entrySet()) {
            if(Integer.parseInt(entry.getValue().getScore()) >= Integer.parseInt(gameScorePlayer2.get(entry.getKey()).getScore()) -2 && Integer.parseInt(entry.getValue().getScore()) >= 6){
                nbWin++;
            }
        }
        if(nbWin == matchType.numberOfSetsToWin()){
            return true;
        }else{
            return false;
        }
    }

    private boolean setWinforPlayer2(){
        int nbWin = 0;
        for (HashMap.Entry<Integer, Score> entry : gameScorePlayer2.entrySet()) {
            if(Integer.parseInt(entry.getValue().getScore()) >= Integer.parseInt(gameScorePlayer1.get(entry.getKey()).getScore()) -2 && Integer.parseInt(entry.getValue().getScore()) >= 6){
                nbWin++;
            }
        }
        if(nbWin == matchType.numberOfSetsToWin()){
            return true;
        }else{
            return false;
        }
    }

    private void newSet(){
        gameScorePlayer1.put(gameScorePlayer1.size()+1, new ScoreNumber("0"));
        gameScorePlayer2.put(gameScorePlayer2.size()+1, new ScoreNumber("0"));
    }

    private boolean balleDeMatch(){
        if(tieBreakInLastSet){
            if(gameScorePlayer1.size() == matchType.maxNumberOfSets()){
                if(gamesInCurrentSetForPlayer(player1) == 6 && gamesInCurrentSetForPlayer(player2) == 6){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean endMatch(){
        if(setWinforPlayer1()){
            System.out.println("FINISH ! " + player1.getName() + " gagne le match !");
            return true;
        } else if(setWinforPlayer2()) {
            System.out.println("FINISH ! " + player2.getName() + " gagne le match !");
            return true;
        }
        return false;
    }
}

