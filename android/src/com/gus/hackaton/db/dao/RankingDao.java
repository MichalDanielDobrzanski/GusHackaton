package com.gus.hackaton.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.gus.hackaton.db.entity.Ranking;

import java.util.List;

@Dao
public interface RankingDao
{
    @Query("SELECT * FROM ranking")
    List<Ranking> getAll();

    @Insert
    void insertAll(List<Ranking> rankings);

    @Delete
    void delete(Ranking ranking);
}