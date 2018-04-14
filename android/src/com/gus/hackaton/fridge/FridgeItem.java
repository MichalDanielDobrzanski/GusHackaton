package com.gus.hackaton.fridge;


import android.support.annotation.DrawableRes;

public final class FridgeItem {

    private String description;
    private FridgeType fridgeType;

    private final int drawableRes;

    public FridgeItem(String description, FridgeType fridgeType, @DrawableRes int drawableRes) {
        this.description = description;
        this.fridgeType = fridgeType;
        this.drawableRes = drawableRes;
    }

    public int getDrawableRes() {
        return drawableRes;
    }


    public String getDescription() {
        return description;
    }

    public FridgeType getFridgeType() {
        return fridgeType;
    }

    @Override
    public String toString() {
        return "FridgeItem{" +
                "description='" + description + '\'' +
                ", fridgeType=" + fridgeType +
                '}';
    }
}
