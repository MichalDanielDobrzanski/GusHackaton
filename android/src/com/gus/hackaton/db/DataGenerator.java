package com.gus.hackaton.db;

import android.content.Context;

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

    static String [] PRODUCT_IDS = new String[] {"CP01147A", "CP01113A", "CP01161A", "CP01161B", "CP01113A", "CP01113A","CP01171A", "CP01171A"};
    static int [] PRODUCT_POINTS = new int[] {20, 10, 20, 30, 10, 20, 40, 10};
    static int [] PRODUCT_HEALTH_INDICATORS = new int[] {2, 1, 3, 3, 1, 1, 3, 1};

    //TODO: In future replace with external api call to obtain this values
    static double [] PRODUCT_CALORIES = new double[] {150, 250, 50, 89, 300, 200, 250, 40, 12};
    static double [] PRODUCT_FATS = new double[] {11, 3, 0.2, 0.3, 5.8, 3, 4, 0.1, 0.2};
    static double [] PRODUCT_CARBOHDRATES = new double[] {0.7, 49, 14, 23, 56, 50, 60, 9,2};
    static double [] PRODUCT_SUGARS = new double[] {0.7, 5, 10, 12, 3.6, 6, 8, 4, 1};
    static double [] PRODUCT_PROTEINS = new double[] {13, 9, 0.3, 1.1, 11, 11, 20, 1, 1};

    static int [] DRAWABLE_IDS = new int[]{R.drawable.eggs, R.drawable.bread, R.drawable.apple, R.drawable.banana, R.drawable.loaf, R.drawable.mini_bread, R.drawable.onion, R.drawable.pickled_cucumber};


    public static List<Ranking> generateRanking() {
        List<Ranking> rankingList = new ArrayList<>();
        for(int i = 0; i < NAMES.length; ++ i) {
            Ranking r = new Ranking(i, NAMES[i], POINTS[i]);
            rankingList.add(r);
        }

        return rankingList;
    }

    public static List<Question> generateQuestions(Context context) {
        String [] QUESTIONS = {context.getString(R.string.questionHowMany), context.getString(R.string.questionHow), context.getString(R.string.questionFromWhere)};
        String [][] ANSWERS = {
                {"a", "b", "c", "d"},
                {"e", "f", "g", "h"},
                {"i", "j", "k", "l"}
        };
        int [] CORRECT_INDEX = {0, 1, 2, 3};
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

    public static List<Product> generateProducts(Context context) {
        String [] PRODUCT_NAMES = new String[] {
                context.getString(R.string.itemEggs), context.getString(R.string.itemBread), context.getString(R.string.itemApple),
                context.getString(R.string.itemBanana), context.getString(R.string.itemGrahamRoll), context.getString(R.string.itemRoll),
                context.getString(R.string.itemOnion), context.getString(R.string.itemPickledCucumber)
        };
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < PRODUCT_IDS.length; i++)
        {
            Product p = new Product();
            p.setId(i);
            p.setEurostat_id(PRODUCT_IDS[i]);
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