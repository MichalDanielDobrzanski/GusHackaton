package com.gus.hackaton.ranking;

public class RankingItem {

    private String userName;
    private int points;


    public RankingItem(String userName, int points) {
        this.userName = userName;
        this.points = points;
    }

    public String getUserName() {
        return userName;
    }

    public int getPoints() {
        return points;
    }
}
