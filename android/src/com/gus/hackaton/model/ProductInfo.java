package com.gus.hackaton.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductInfo {

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

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", health_indicator=" + health_indicator +
                ", nutricalInfo=" + nutricalInfo +
                ", points=" + points +
                ", value=" + eurostatDataList +
                '}';
    }
}




