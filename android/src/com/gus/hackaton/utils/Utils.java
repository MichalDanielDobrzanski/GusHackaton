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
            new FridgeItem("apple", FridgeType.Badge, R.drawable.sticky_note_small),
            new FridgeItem("banana",FridgeType.Badge, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.Badge, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.Badge, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.Badge, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.Badge, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.Badge, R.drawable.sticky_note_small));

    public static List<FridgeItem> DUMMY_QUEST_LIST = Arrays.asList(
            new FridgeItem("find_beer",FridgeType.Quest, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.Quest, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.Quest, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.Quest, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.Quest, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.Quest, R.drawable.sticky_note_small));


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
