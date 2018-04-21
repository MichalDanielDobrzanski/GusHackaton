package com.gus.hackaton.db;

import com.gus.hackaton.R;
import com.gus.hackaton.db.entity.Product;
import com.gus.hackaton.db.entity.Question;
import com.gus.hackaton.db.entity.Ranking;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator
{
    static String [] NAMES = new String[] {"Konrad", "Dariusz", "Michał", "Radek"};
    static int [] POINTS = new int[] {200, 10, 150, 100};
    static String [] QUESTIONS = {"Ile?", "Jak?", "Skąd?"};
    static String [][] ANSWERS = {
            {"a", "b", "c", "d"},
            {"e", "f", "g", "h"},
            {"i", "j", "k", "l"}
    };
    static int [] CORRECT_INDEX = {0, 1, 2, 3};

    static String [] PRODUCT_IDS = new String[] {"CP01147A", "CP01113A"};
    static String [] PRODUCT_NAMES = new String[] {"Eggs", "Bread"};
    static int [] PRODUCT_HEALTH_INDICATORS = new int[] {1, 2};
    static int [] PRODUCT_POINTS = new int[] {20, 10};
    static double [] PRODUCT_CALORIES = new double[] {120, 120};
    static double [] PRODUCT_FATS = new double[] {20, 20};
    static double [] PRODUCT_CARBOHDRATES = new double[] {28, 28};
    static double [] PRODUCT_SUGARS = new double[] {53, 23};
    static double [] PRODUCT_PROTEINS = new double[] {20, 21};
    static int [] DRAWABLE_IDS = new int[] {R.drawable.eggs, R.drawable.bread};


    public static List<Ranking> generateRanking() {
        List<Ranking> rankingList = new ArrayList<>();
        for(int i = 0; i < NAMES.length; ++ i) {
            Ranking r = new Ranking(i, NAMES[i], POINTS[i]);
            rankingList.add(r);
        }

        return rankingList;
    }

    public static List<Question> generateQuestions() {
        List<Question> questions = new ArrayList<>();
        for(int i = 0; i < QUESTIONS.length; ++i) {
            Question q = new Question();
            q.setId(i);
            q.setQuestion(QUESTIONS[i]);
            q.setAnswers(ANSWERS[i]);
            q.setCorrectAnswer(CORRECT_INDEX[i]);
            questions.add(q);
        }

        return questions;
    }

    public static List<Product> generateProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < PRODUCT_IDS.length; i++)
        {
            Product p = new Product();
            p.setId(PRODUCT_IDS[i]);
            p.setName(PRODUCT_NAMES[i]);
            p.setHealth_indicator(PRODUCT_HEALTH_INDICATORS[i]);
            p.setPoints(PRODUCT_POINTS[i]);
            p.setScanned(false);
            p.setCalories(PRODUCT_CALORIES[i]);
            p.setFat(PRODUCT_FATS[i]);
            p.setCarbohydrate(PRODUCT_CARBOHDRATES[i]);
            p.setSugar(PRODUCT_SUGARS[i]);
            p.setProtein(PRODUCT_PROTEINS[i]);
            p.setDrawableId(DRAWABLE_IDS[i]);
            products.add(p);
        }

        return products;
    }
}