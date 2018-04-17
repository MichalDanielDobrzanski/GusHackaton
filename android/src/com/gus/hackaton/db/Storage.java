package com.gus.hackaton.db;

import com.gus.hackaton.model.FridgeItem;
import com.gus.hackaton.model.Product;

import java.util.List;

public interface Storage {

    void putQuestList(List<FridgeItem> quests);

    void putBadgeList(List<FridgeItem> quests);

    List<FridgeItem> getQuestList();

    List<FridgeItem> getBadgeList();


    void putProductList(List<Product> initialProductsList);

    List<Product> getProductList();
}
