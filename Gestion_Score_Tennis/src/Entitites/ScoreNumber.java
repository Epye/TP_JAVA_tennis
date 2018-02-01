public class ScoreNumber implements Score {
    private String score;

    public ScoreNumber(String score) {
        this.score = score;
    }

    @Override
    public String getScore() {
        return score;
    }

    @Override
    public void winPoint() {
        int tmp = Integer.parseInt(score);
        tmp++;
        score = Integer.toString(tmp);
    }

    @Override
    public void loosePoint() {

    }

    @Override
    public void setWin() {
        this.score = "WIN";
    }

}
