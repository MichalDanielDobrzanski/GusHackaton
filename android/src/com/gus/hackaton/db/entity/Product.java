package com.gus.hackaton.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.gus.hackaton.db.DataConverter;
import com.gus.hackaton.db.DataGenerator;
import com.gus.hackaton.model.EurostatData;

import java.util.List;


@Entity(tableName = "product")
public class Product
{
    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "health_indicator")
    private int health_indicator;

    @ColumnInfo(name = "points")
    private int points;

    @ColumnInfo(name = "scanned")
    private boolean scanned;

    @ColumnInfo(name = "calories")
    private double calories;

    @ColumnInfo(name = "fat")
    private double fat;

    @ColumnInfo(name = "carbohydrate")
    private double carbohydrate;

    @ColumnInfo(name = "sugar")
    private double sugar;

    @ColumnInfo(name = "protein")
    private double protein;

    @ColumnInfo(name = "drawable_id")
    private int drawableId;

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "eurostat_data")
    private List<Float> eurostatData;


    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getHealth_indicator()
    {
        return health_indicator;
    }

    public void setHealth_indicator(int health_indicator)
    {
        this.health_indicator = health_indicator;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public boolean isScanned()
    {
        return scanned;
    }

    public void setScanned(boolean scanned)
    {
        this.scanned = scanned;
    }

    public double getCalories()
    {
        return calories;
    }

    public void setCalories(double calories)
    {
        this.calories = calories;
    }

    public double getFat()
    {
        return fat;
    }

    public void setFat(double fat)
    {
        this.fat = fat;
    }

    public double getCarbohydrate()
    {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate)
    {
        this.carbohydrate = carbohydrate;
    }

    public double getSugar()
    {
        return sugar;
    }

    public void setSugar(double sugar)
    {
        this.sugar = sugar;
    }

    public double getProtein()
    {
        return protein;
    }

    public void setProtein(double protein)
    {
        this.protein = protein;
    }

    public List<Float> getEurostatData()
    {
        return eurostatData;
    }

    public void setEurostatData(List<Float> eurostatData)
    {
        this.eurostatData = eurostatData;
    }

    public int getDrawableId()
    {
        return drawableId;
    }

    public void setDrawableId(int drawableId)
    {
        this.drawableId = drawableId;
    }
}