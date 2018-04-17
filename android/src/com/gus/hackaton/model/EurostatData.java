package com.gus.hackaton.model;

public final class EurostatData {

    private String country;

    public double price;

    public EurostatData(String country, double price) {
        this.country = country;
        this.price = price;
    }


    public String getCountry() {
        return country;
    }

    public double getPrice() {
        return price;
    }
}
