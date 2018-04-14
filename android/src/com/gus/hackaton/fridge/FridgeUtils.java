package com.gus.hackaton.fridge;

import java.util.Arrays;
import java.util.List;

public class FridgeUtils {

    public static final int COLUMNS_COUNT = 6;

    public static List<FridgeItem> DUMMY_LIST = Arrays.asList(
            new FridgeItem("apple",FridgeType.ACHIEVEMENT),
            new FridgeItem("banana",FridgeType.ACHIEVEMENT),
            new FridgeItem("vodka",FridgeType.ACHIEVEMENT),
            new FridgeItem("find pear",FridgeType.QUEST),
            new FridgeItem("find_beer",FridgeType.QUEST)
    );
}
