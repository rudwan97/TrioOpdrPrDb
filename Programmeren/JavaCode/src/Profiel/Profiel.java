package Profiel;

import Account.Account;

public class Profiel{

    private String subscriptionNumber;
    private String profileName;
    private String birthDate;

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

    public Profiel(String subscriptionNumber, String profileName, String birthDate) {


        this.subscriptionNumber = subscriptionNumber;
        this.profileName = profileName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Profielnaam : " + this.profileName + ", Abbonneenummer : " + this.subscriptionNumber + ", Geboortedatum : " + this.birthDate;
    }
}
