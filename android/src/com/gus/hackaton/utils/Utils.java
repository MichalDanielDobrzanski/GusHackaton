package com.gus.hackaton.utils;

import com.gus.hackaton.R;
import com.gus.hackaton.fridge.FridgeItem;
import com.gus.hackaton.fridge.FridgeType;
import com.gus.hackaton.ranking.RankingItem;

import java.util.Arrays;
import java.util.List;

public class Utils {

    public static final int COLUMNS_COUNT = 2;

    public static List<FridgeItem> DUMMY_BADGE_LIST = Arrays.asList(
            new FridgeItem("apple",FridgeType.Badge, R.drawable.apple),
            new FridgeItem("banana",FridgeType.Badge, R.drawable.banana),
            new FridgeItem("orange",FridgeType.Badge, R.drawable.orange),
            new FridgeItem("pomato",FridgeType.Badge, R.drawable.pomato),
            new FridgeItem("milk",FridgeType.Badge, R.drawable.milk),
            new FridgeItem("yogurt",FridgeType.Badge, R.drawable.yoghurt)
    );

    public static List<FridgeItem> QUESTS_LIST = Arrays.asList(
            new FridgeItem("Chleb",FridgeType.Quest, R.drawable.bread),
            new FridgeItem("Jajko",FridgeType.Quest, R.drawable.egg),
            new FridgeItem("Platki sniadaniowe",FridgeType.Quest, R.drawable.cornflakes),
            new FridgeItem("Losos",FridgeType.Quest, R.drawable.fish),
            new FridgeItem("Ryz",FridgeType.Quest, R.drawable.rice),
            new FridgeItem("Oliwki",FridgeType.Quest, R.drawable.olive),
            new FridgeItem("Ser",FridgeType.Quest, R.drawable.cheese)
    );


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
