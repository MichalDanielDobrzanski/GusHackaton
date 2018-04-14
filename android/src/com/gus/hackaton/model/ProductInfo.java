package com.gus.hackaton.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfo
{
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
}