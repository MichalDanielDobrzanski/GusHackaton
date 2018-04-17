package com.gus.hackaton.shared;

import android.content.Context;

import com.gus.hackaton.R;
import com.gus.hackaton.db.Storage;
import com.gus.hackaton.db.StorageImpl;
import com.gus.hackaton.fridge.FridgeItem;
import com.gus.hackaton.fridge.FridgeType;
import com.gus.hackaton.model.EurostatData;
import com.gus.hackaton.model.Product;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FlowManager {

    private static FlowManager instance = new FlowManager();

    public static FlowManager getInstance() {
        return instance;
    }

    public List<Product> currentProductList;

    public void markScanned(Context context, String description, List<EurostatData> eurostatDataList) {

        Storage storage = new StorageImpl(context);

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

}
