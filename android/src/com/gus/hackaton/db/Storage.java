package com.gus.hackaton.db;

import com.gus.hackaton.fridge.FridgeItem;

import java.util.List;

public interface Storage {

    void putQuestList(List<FridgeItem> quests);

    void putBadgeList(List<FridgeItem> quests);

    List<FridgeItem> getQuestList();

    List<FridgeItem> getBadgeList();

}
