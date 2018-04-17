package com.gus.hackaton.model;


import android.support.annotation.DrawableRes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("health_indicator")
    @Expose
    public int health_indicator;


    @SerializedName("nutritional")
    @Expose
    public NutricalInfo nutricalInfo;

    @SerializedName("points")
    @Expose
    public int points;

    @SerializedName("value")
    @Expose
    public List<EurostatData> eurostatDataList;


    private final String eurostatCode;

    private final int drawable;

    public Product(String id, String name, int points, int health_indicator, String eurostatCode, @DrawableRes int drawable) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.health_indicator = health_indicator;
        this.eurostatCode = eurostatCode;
        this.drawable = drawable;
    }

    public String getEurostatCode() {
        return eurostatCode;
    }

    public int getDrawable() {
        return drawable;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", health_indicator=" + health_indicator +
                ", nutricalInfo=" + nutricalInfo +
                ", points=" + points +
                ", eurostatDataList=" + eurostatDataList +
                ", eurostatCode='" + eurostatCode + '\'' +
                ", drawable=" + drawable +
                '}';
    }
}




