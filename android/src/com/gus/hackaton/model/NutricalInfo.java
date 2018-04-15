package com.gus.hackaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutricalInfo
{
    @SerializedName("calories")
    @Expose
    public double calories;

    @SerializedName("fat")
    @Expose
    public double fat;

    @SerializedName("carbohydrate")
    @Expose
    public double carbohydrate;

    @SerializedName("sugar")
    @Expose
    public double sugar;

    @SerializedName("protein")
    @Expose
    public double protein;

    @Override
    public String toString() {
        return "NutricalInfo{" +
                "calories=" + calories +
                ", fat=" + fat +
                ", carbohydrate=" + carbohydrate +
                ", sugar=" + sugar +
                ", protein=" + protein +
                '}';
    }
}