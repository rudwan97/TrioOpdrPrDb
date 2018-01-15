package Profiel;

public class Profiel{

    //Gegevens van een profiel
    private String subscriptionNumber;
    private String profileName;
    private String birthDate;

    public Profiel(String subscriptionNumber, String profileName, String birthDate) {
        this.subscriptionNumber = subscriptionNumber;
        this.profileName = profileName;
        this.birthDate = birthDate;
    }

    //Standaard getters en setters
    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }

    //toString
    @Override
    public String toString() {
        return "Profielnaam : " + this.profileName + ", Abbonneenummer : " + this.subscriptionNumber + ", Geboortedatum : " + this.birthDate;
    }
}
