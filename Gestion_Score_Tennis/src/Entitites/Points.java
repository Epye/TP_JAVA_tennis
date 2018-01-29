/*public enum Points {
    ZERO("0"), QUINZE("15"), TRENTE("30"), QUARANTE("40"), AVANTAGE("A");
    private String score;

    public String getScore() {
        return score;
    }

    Points(String score) {
        this.score = score;
    }

    public Points winPoint(){
        switch(this){
            case ZERO: return Points.QUINZE; break;
            case QUINZE: return Points.TRENTE; break;
            case TRENTE: return Points.QUARANTE; break;
            case QUARANTE: return Points.AVANTAGE; break;
        }
        return null;
    }

    public Points loosePoint(){
        switch(this){
            case AVANTAGE: return Points.QUARANTE; break;
        }
    }

    public Points winPointTieBreak(){
        int tmp = Integer.parseInt(this.score);
        tmp++;
    }

}*/
