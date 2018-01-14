package Aflevering;

public class Aflevering {

    //Aflevering gegevens
    private String id;
    private String serie;
    private String season;
    private String title;
    private String duration;
    private int avgSeen;

    public Aflevering(String id, String serie, String season, String title, String duration) {
        this.id = id;
        this.serie = serie;
        this.season = season;
        this.title = title;
        this.duration = duration;
    }

    //Speciale constructor om een aflevering te maken met alleen een titel en het gemiddeld bekeken percentage
    public Aflevering(String title, int avgSeen){
        this.title = title;
        this.avgSeen = avgSeen;
    }

    //Standaard getters en setters
    public int getAvgSeen() {
        return avgSeen;
    }

    public void setAvgSeen(int avgSeen) {
        this.avgSeen = avgSeen;
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


    //toString
    @Override
    public String toString() {
        if (this.season != null) {
            return this.title + " " + this.serie + "/n";
        }
        else return this.title + " " + this.avgSeen + "\n";
    }


}
