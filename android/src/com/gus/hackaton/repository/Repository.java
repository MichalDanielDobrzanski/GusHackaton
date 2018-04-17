package com.gus.hackaton.repository;

import com.annimon.stream.Stream;
import com.gus.hackaton.db.Storage;
import com.gus.hackaton.fridge.FridgeType;
import com.gus.hackaton.model.EurostatData;
import com.gus.hackaton.model.FridgeItem;
import com.gus.hackaton.model.Product;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Repository {

    private final Storage storage;

    public Repository(Storage storage) {
        this.storage = storage;
    }

    public void markScanned(String description, List<EurostatData> eurostatDataList) {

        FridgeItem questItem = null;

        // https://stackoverflow.com/questions/2965747/why-do-i-get-an-unsupportedoperationexception-when-trying-to-remove-an-element-f
        List<FridgeItem> list = new LinkedList<>(storage.getQuestList());
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getDescription(), description)) {
                questItem = list.remove(i);
                break;
            }
        }
        storage.putQuestList(list);

        // badges:
        if (questItem != null) { // null if already scanned
            FridgeItem badgeItem = questItem.copy(FridgeType.Badge);
            badgeItem.eurostatData = eurostatDataList;

            List<FridgeItem> badgesList = storage.getBadgeList();
            if (badgesList != null) {
                badgesList.add(badgeItem);
                storage.putBadgeList(badgesList);

            } else
                storage.putBadgeList(Collections.singletonList(badgeItem));
        }

    }

    public void putProductList(List<Product> initialProductsList) {
        storage.putProductList(initialProductsList);
    }

    public void putQuestList(List<FridgeItem> fridgeItems) {
        storage.putQuestList(fridgeItems);
    }

    public void putBadgeList(List<FridgeItem> fridgeItems) {
        storage.putBadgeList(fridgeItems);
    }


    public List<FridgeItem> getQuestList() {
        return storage.getQuestList();
    }

    public List<FridgeItem> getBadgeList() {
        return storage.getBadgeList();
    }

    public List<Product> getCurrentProductList() {
        return storage.getProductList();
    }

    public void saveProduct(Product product) {
        List<Product> list = storage.getProductList();

        Iterator<Product> iter = list.iterator();
        while (iter.hasNext()) {
            if (Objects.equals(iter.next().name, product.name)) {
                iter.remove();
            }
        }

        list.add(product);
        storage.putProductList(list);
    }
}
