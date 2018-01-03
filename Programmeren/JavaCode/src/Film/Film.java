package Film;

public class Film {

    private String id;
    private String title;
    private String recommendedAge;
    private String lanugage;
    private String duration;
    private String filmType;

    public Film(String id, String title, String recommendedAge, String lanugage, String duration, String filmType) {
        this.id = id;
        this.title = title;
        this.recommendedAge = recommendedAge;
        this.lanugage = lanugage;
        this.duration = duration;
        this.filmType = filmType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecommendedAge() {
        return recommendedAge;
    }

    public void setRecommendedAge(String recommendedAge) {
        this.recommendedAge = recommendedAge;
    }

    public String getLanugage() {
        return lanugage;
    }

    public void setLanugage(String lanugage) {
        this.lanugage = lanugage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id +
                ", title='" + title +
                ", recommendedAge='" + recommendedAge +
                ", lanugage='" + lanugage +
                ", duration='" + duration +
                ", filmType='" + filmType + "\n";
    }
}
