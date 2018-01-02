package com.company;

public class Account {

    private String subscriptionNumber;
    private String name;
    private String street;
    private String zipcode;
    private String houseNumber;
    private String city;


    public Account(String subscriptionNumber, String name, String street, String zipcode, String houseNumber, String city) {
        this.subscriptionNumber = subscriptionNumber;
        this.name = name;
        this.street = street;
        this.zipcode = zipcode;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public String getSubscriptionNumber() {
        return subscriptionNumber;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setSubscriptionNumber(String subscriptionNumber) {
        this.subscriptionNumber = subscriptionNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return this.name + " " + this.subscriptionNumber;
    }
}
