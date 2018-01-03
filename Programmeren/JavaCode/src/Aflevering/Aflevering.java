package Aflevering;

public class Aflevering {

    private String id;
    private String serie;
    private String season;
    private String title;
    private String duration;

    public Aflevering(String id, String serie, String season, String title, String duration) {
        this.id = id;
        this.serie = serie;
        this.season = season;
        this.title = title;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
