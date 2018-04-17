package com.gus.hackaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EurostatData {

    public enum Country {
        ES,
        HR,
        IT,
        PL,
        FIN
    }

    @SerializedName("key")
    @Expose
    public int countryKey;


    @SerializedName("value")
    @Expose
    public double price;

    public EurostatData(int countryKey, double price) {
        this.countryKey = countryKey;
        this.price = price;
    }

    public Country getCountryKey() {
        return Country.values()[countryKey];
    }
}
