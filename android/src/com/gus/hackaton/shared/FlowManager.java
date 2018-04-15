package com.gus.hackaton.shared;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.gus.hackaton.R;
import com.gus.hackaton.db.Storage;
import com.gus.hackaton.db.StorageImpl;
import com.gus.hackaton.fridge.FridgeItem;
import com.gus.hackaton.fridge.FridgeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FlowManager {

    private static final String TAG = FlowManager.class.getSimpleName();

    public static List<FridgeItem> QUESTS_LIST = Arrays.asList(
            new FridgeItem("Chleb",FridgeType.Quest, R.drawable.bread),
            new FridgeItem("Jajko",FridgeType.Quest, R.drawable.egg),
            new FridgeItem("Platki sniadaniowe",FridgeType.Quest, R.drawable.cornflakes),
            new FridgeItem("Losos",FridgeType.Quest, R.drawable.fish),
            new FridgeItem("Ryz",FridgeType.Quest, R.drawable.rice),
            new FridgeItem("Oliwki",FridgeType.Quest, R.drawable.olive),
            new FridgeItem("Ser",FridgeType.Quest, R.drawable.cheese)
    );


    private static FlowManager instance = new FlowManager();

    public static FlowManager getInstance() {
        return instance;
    }


    public void markScanned(Context context, String description) {

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

            List<FridgeItem> badgesList = storage.getBadgeList();
            if (badgesList != null) {
                badgesList.add(badgeItem);
                storage.putBadgeList(badgesList);

            } else
                storage.putBadgeList(Collections.singletonList(badgeItem));
        }

    }

}
