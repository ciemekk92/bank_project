package com.company.banking.util;

public class Address {
    private String country, city, postcode, street;

    public Address(String country, String city, String postcode, String street) {
        this.country = country;
        this.city = city;
        this.postcode = postcode;
        this.street = street;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return country + ", " + postcode + " " + city + ", " + street;
    }
}
