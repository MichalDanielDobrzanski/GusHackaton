package com.gus.hackaton.db;

import com.gus.hackaton.db.entity.Ranking;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator
{
    public static List<Ranking> generateRanking() {
        List<Ranking> rankingList = new ArrayList<>();
        rankingList.add(new Ranking("Konrad", 200));
        rankingList.add(new Ranking("Dariusz", 100));
        rankingList.add(new Ranking("Radek", 150));

        return rankingList;
    }
}
