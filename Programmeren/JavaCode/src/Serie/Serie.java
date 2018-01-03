package Serie;

public class Serie {

    private String serieName;
    private String season;
    private String recommendedAge;
    private String language;
    private String serieType;
    private String looksLike;

    public Serie(String serieName, String season, String recommendedAge, String language, String serieType, String looksLike) {
        this.serieName = serieName;
        this.season = season;
        this.recommendedAge = recommendedAge;
        this.language = language;
        this.serieType = serieType;
        this.looksLike = looksLike;
    }

    public String getSerieName() {
        return serieName;
    }

    public void setSerieName(String serieName) {
        this.serieName = serieName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(String recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSerieType() {
        return serieType;
    }

    public void setSerieType(String serieType) {
        this.serieType = serieType;
    }

    public String getLooksLike() {
        return looksLike;
    }

    public void setLooksLike(String looksLike) {
        this.looksLike = looksLike;
    }


}
