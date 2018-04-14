package com.gus.hackaton.fridge;

import com.gus.hackaton.R;

import java.util.Arrays;
import java.util.List;

public class FridgeUtils {

    public static final int COLUMNS_COUNT = 6;

    public static List<FridgeItem> DUMMY_LIST = Arrays.asList(
            new FridgeItem("apple",FridgeType.ACHIEVEMENT, R.drawable.sticky_note_small),
            new FridgeItem("banana",FridgeType.ACHIEVEMENT, R.drawable.sticky_note_small),
            new FridgeItem("vodka",FridgeType.ACHIEVEMENT, R.drawable.sticky_note_small),
            new FridgeItem("find pear",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small),
            new FridgeItem("find_beer",FridgeType.QUEST, R.drawable.sticky_note_small)

    );
}
