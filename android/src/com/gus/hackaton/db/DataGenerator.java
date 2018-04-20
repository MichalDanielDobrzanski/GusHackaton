package com.gus.hackaton.db;

import com.gus.hackaton.db.entity.Ranking;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator
{
    static String [] NAMES = new String[] {"Konrad", "Dariusz", "Michał", "Radek"};
    static int [] POINTS = new int[] {200, 10, 150, 100};

    public static List<Ranking> generateRanking() {
        List<Ranking> rankingList = new ArrayList<>();
        for(int i = 0; i < NAMES.length; ++ i) {
            Ranking r = new Ranking(i, NAMES[i], POINTS[i]);
            rankingList.add(r);
        }

        return rankingList;
    }
}
