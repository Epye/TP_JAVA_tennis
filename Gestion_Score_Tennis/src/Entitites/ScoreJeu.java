public class ScoreJeu implements Score{
    private String score;

    public ScoreJeu(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    /***
     * Met à jour les points d'un joueur
     */

    public void winPoint(){
        switch (score){
            case "0": this.score = "15"; break;
            case "15": this.score = "30"; break;
            case "30": this.score = "40"; break;
            case "40": this.score = "A"; break;
            case "A": this.score = "WIN"; break;
        }
    }

    public void loosePoint(){
        switch (score){
            case "A": this.score = "40"; break;
        }
    }
}
