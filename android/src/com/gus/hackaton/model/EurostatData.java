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
    public int country;


    @SerializedName("value")
    @Expose
    public double price;

    public Country getCountry() {
        return Country.values()[country];
    }
}
