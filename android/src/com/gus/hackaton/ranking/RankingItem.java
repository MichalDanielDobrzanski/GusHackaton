package com.gus.hackaton.ranking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankingItem {

    @SerializedName("username")
    @Expose
    private String userName;

    @SerializedName("points")
    @Expose
    private int points;


    public RankingItem(String userName, int points) {
        this.userName = userName;
        this.points = points;
    }

    String getUserName() {
        return userName;
    }

    public int getPoints() {
        return points;
    }
}
