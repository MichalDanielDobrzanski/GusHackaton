package com.gus.hackaton.model;


import android.support.annotation.DrawableRes;

import com.gus.hackaton.fridge.FridgeType;

import java.util.List;

public final class FridgeItem {

    private String description;
    private FridgeType fridgeType;

    private final int drawableRes;

    public List<EurostatData> eurostatData;

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

    public FridgeItem copy(FridgeType fridgeType) {
        return new FridgeItem(this.description, fridgeType, this.drawableRes);
    }
}
