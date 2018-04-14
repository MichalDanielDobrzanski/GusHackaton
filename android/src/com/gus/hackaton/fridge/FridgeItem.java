package com.gus.hackaton.fridge;


public final class FridgeItem {

    private String description;
    private FridgeType fridgeType;

    public FridgeItem(String description, FridgeType fridgeType) {
        this.description = description;
        this.fridgeType = fridgeType;
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
