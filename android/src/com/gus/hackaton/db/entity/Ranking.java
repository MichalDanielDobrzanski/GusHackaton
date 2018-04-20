package com.gus.hackaton.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ranking")
public class Ranking
{
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "user_name")
    private String userName;

    @ColumnInfo(name = "points")
    private int points;

    public Ranking(int uid, String userName, int points){}

    public Ranking(String name, int points){
        this.userName = name;
        this.points = points;
    }

    public int getUid()
    {
        return uid;
    }

    public void setUid(int uid)
    {
        this.uid = uid;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }
}