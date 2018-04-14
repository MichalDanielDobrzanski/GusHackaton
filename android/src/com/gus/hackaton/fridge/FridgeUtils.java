package com.gus.hackaton.fridge;

import com.gus.hackaton.R;

import java.util.Arrays;
import java.util.List;

public class FridgeUtils {

    public static final int COLUMNS_COUNT = 2;

    public static List<FridgeItem> DUMMY_BADGE_LIST = Arrays.asList(
            new FridgeItem("apple",FridgeType.BADGE, R.drawable.sticky_note_small),
            new FridgeItem("banana",FridgeType.BADGE, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.BADGE, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.BADGE, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.BADGE, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.BADGE, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.BADGE, R.drawable.sticky_note_small));

    public static List<FridgeItem> DUMMY_QUEST_LIST = Arrays.asList(
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small));


}
