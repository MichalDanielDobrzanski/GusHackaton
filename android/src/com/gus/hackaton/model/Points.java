package com.gus.hackaton.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Points
{
    @SerializedName("points")
    @Expose
    public int points;

    public Points(int p){
        points = p;
    }

    @Override
    public String toString() {
        return "Points{" +
                "points=" + points +
                '}';
    }
}
