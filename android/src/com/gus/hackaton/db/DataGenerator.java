package com.gus.hackaton.db;

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
}