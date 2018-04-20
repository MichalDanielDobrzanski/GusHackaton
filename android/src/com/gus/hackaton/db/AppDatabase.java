package com.gus.hackaton.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.gus.hackaton.db.dao.RankingDao;
import com.gus.hackaton.db.entity.Ranking;

import java.util.List;

@Database(entities = {Ranking.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase sInstance;

    public static final String DATABASE_NAME = "helth-pet-database";

    public abstract RankingDao rankingDao();

    public static AppDatabase getsInstance(final Context context){
        if (sInstance == null) {
            sInstance = buildDatabase(context);
        }

        return sInstance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).addCallback(new Callback()
        {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db)
            {
                super.onCreate(db);
                List<Ranking> ranking = DataGenerator.generateRanking();
                for (Ranking r : ranking){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("uid", r.getUid());
                    contentValues.put("user_name", r.getUserName());
                    contentValues.put("points", r.getPoints());
                    db.insert("ranking", SQLiteDatabase.CONFLICT_IGNORE, contentValues);
                }
            }
        }).build();
    }
}
