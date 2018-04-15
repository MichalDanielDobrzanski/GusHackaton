package com.gus.hackaton.utils;

import com.gus.hackaton.ranking.RankingItem;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static final int COLUMNS_COUNT = 2;

    public static List<RankingItem> DUMMY_RANKING_LIST = Arrays.asList(
            new RankingItem("Ryan gossling", 1000),
            new RankingItem("Konrad rap G", 900),
            new RankingItem("Darek The Dude", 760),
            new RankingItem("Roots reggae Radek", 160),
            new RankingItem("Michal D", 160),
            new RankingItem("Michal DD", 150),
            new RankingItem("Michal DDD", 140),
            new RankingItem("Michal DDDD", 120)
    );

}
