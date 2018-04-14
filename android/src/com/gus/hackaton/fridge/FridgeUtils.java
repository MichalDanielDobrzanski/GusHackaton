package com.gus.hackaton.fridge;

import com.gus.hackaton.R;

import java.util.Arrays;
import java.util.List;

public class FridgeUtils {

    public static final int COLUMNS_COUNT = 2;

    public static List<FridgeItem> DUMMY_BADGE_LIST = Arrays.asList(
            new FridgeItem("apple",FridgeType.Badge, R.drawable.sticky_note_small),
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


}
