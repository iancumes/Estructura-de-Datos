class Association {
    private String english;
    private String spanish;
    private String french;

    public Association(String english, String spanish, String french) {
        this.english = english;
        this.spanish = spanish;
        this.french = french;
    }

    public String getEnglish() {
        return english;
    }

    public String getSpanish() {
        return spanish;
    }

    public String getFrench() {
        return french;
    }
}