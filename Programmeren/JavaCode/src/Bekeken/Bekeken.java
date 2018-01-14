package Bekeken;

public class Bekeken {

    //Gegevens van een bekeken kolom
    private String subscriptionNumber;
    private String profileName;
    private String watched;
    private String percentageSeen;

    public Bekeken(String subscriptionNumber, String profileName, String watched, String percentageSeen) {
        this.subscriptionNumber = subscriptionNumber;
        this.profileName = profileName;
        this.watched = watched;
        this.percentageSeen = percentageSeen;
    }

    //Standaard getters en setters
    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getWatched() {
        return watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }

    public String getPercentageSeen() {
        return percentageSeen;
    }

    public void setPercentageSeen(String percentageSeen) {
        this.percentageSeen = percentageSeen;
    }

    //toString
    @Override
    public String toString() {
        return "Bekeken " +
                "subscriptionNumber=" + subscriptionNumber +
                " profileName=" + profileName +
                " watched=" + watched +
                " percentageSeen=" + percentageSeen + "\n";
    }
}
