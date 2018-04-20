package com.gus.hackaton.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.gus.hackaton.db.dao.ProductDao;
import com.gus.hackaton.db.dao.QuestionDao;
import com.gus.hackaton.db.dao.RankingDao;
import com.gus.hackaton.db.entity.Product;
import com.gus.hackaton.db.entity.Question;
import com.gus.hackaton.db.entity.Ranking;

import java.util.List;

@Database(entities = {Ranking.class, Question.class, Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
    private static AppDatabase sInstance;

    public static final String DATABASE_NAME = "helth-pet-database";

    public abstract RankingDao rankingDao();
    public abstract QuestionDao questionDao();
    public abstract ProductDao productDao();

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

                List<Question> questions = DataGenerator.generateQuestions();
                for (Question q : questions) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("question", q.getQuestion());
                    contentValues.put("answer1", q.getAnswers()[0]);
                    contentValues.put("answer2", q.getAnswers()[1]);
                    contentValues.put("answer3", q.getAnswers()[2]);
                    contentValues.put("answer4", q.getAnswers()[3]);
                    contentValues.put("correct_answer", q.getCorrectAnswer());
                    db.insert("question", SQLiteDatabase.CONFLICT_IGNORE, contentValues);
                }

                List<Product> products = DataGenerator.generateProducts();
                for (Product p : products) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", p.getId());
                    contentValues.put("name", p.getName());
                    contentValues.put("health_indicator", p.getHealth_indicator());
                    contentValues.put("points", p.getPoints());
                    contentValues.put("scanned", p.isScanned());
                    contentValues.put("calories", p.getCalories());
                    contentValues.put("fat", p.getFat());
                    contentValues.put("carbohydrate", p.getCarbohydrate());
                    contentValues.put("sugar", p.getSugar());
                    contentValues.put("protein", p.getProtein());
                    contentValues.put("drawable_id", p.getDrawableId());
                    db.insert("product", SQLiteDatabase.CONFLICT_IGNORE, contentValues);
                }

            }
        }).build();
    }
}
