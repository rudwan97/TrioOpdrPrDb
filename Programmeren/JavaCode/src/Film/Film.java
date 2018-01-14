package Film;

import java.sql.Time;

public class Film {

    //Film gegevens
    private String ID;
    private String title;
    private String recommendedAge;
    private String lanugage;
    private Time duration;
    private String filmType;

    public Film(String ID, String title, String recommendedAge, String lanugage, Time duration, String filmType) {
        this.ID = ID;
        this.title = title;
        this.recommendedAge = recommendedAge;
        this.lanugage = lanugage;
        this.duration = duration;
        this.filmType = filmType;
    }

    //Standaard getters en setters van film
    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    //toString
    @Override
    public String toString() {

        return  "Titel : " +  this.title + ", Tijdsduur : " + duration + ", Genre : " + filmType + "\n";

    }
}
