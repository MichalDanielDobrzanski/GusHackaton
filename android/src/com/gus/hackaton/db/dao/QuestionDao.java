package com.gus.hackaton.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.gus.hackaton.db.entity.Question;

import java.util.List;

@Dao
public interface QuestionDao
{
    @Query("SELECT * FROM question")
    List<Question> getAll();
}
