package com.gus.hackaton.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.gus.hackaton.db.dao.RankingDao;
import com.gus.hackaton.db.entity.Ranking;

@Database(entities = {Ranking.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase sInstance;

    public static final String DATABASE_NAME = "helth-pet-database";

    public abstract RankingDao rankingDao();

    public static AppDatabase getsInstance(final Context context){
        if (sInstance == null) {
            sInstance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
            sInstance.rankingDao().insertAll(DataGenerator.generateRanking());
        }

        return sInstance;
    }

}
