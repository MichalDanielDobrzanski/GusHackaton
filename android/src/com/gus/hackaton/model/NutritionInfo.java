package com.gus.hackaton.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NutritionInfo
{
    private final String id;

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

    public NutritionInfo(String id, double calories, double fat, double carbohydrate, double sugar, double protein) {
        this.id = id;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.sugar = sugar;
        this.protein = protein;
    }

    public String getId() {
        return id;
    }

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