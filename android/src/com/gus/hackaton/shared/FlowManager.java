package com.gus.hackaton.shared;

import android.util.Log;
import android.widget.Toast;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.gus.hackaton.R;
import com.gus.hackaton.fridge.FridgeItem;
import com.gus.hackaton.fridge.FridgeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FlowManager {

    private static final String TAG = FlowManager.class.getSimpleName();

    private List<FridgeItem> questsList = new ArrayList<>();

    private List<FridgeItem> badgesList = new ArrayList<>();


    public static List<FridgeItem> QUESTS_LIST = Arrays.asList(
            new FridgeItem("Chleb",FridgeType.Quest, R.drawable.bread),
            new FridgeItem("Jajko",FridgeType.Quest, R.drawable.egg),
            new FridgeItem("Platki sniadaniowe",FridgeType.Quest, R.drawable.cornflakes),
            new FridgeItem("Losos",FridgeType.Quest, R.drawable.fish),
            new FridgeItem("Ryz",FridgeType.Quest, R.drawable.rice),
            new FridgeItem("Oliwki",FridgeType.Quest, R.drawable.olive),
            new FridgeItem("Ser",FridgeType.Quest, R.drawable.cheese)
    );


    private FlowManager() {
        questsList = QUESTS_LIST;

    }

    private static FlowManager instance = new FlowManager();

    public static FlowManager getInstance() {
        return instance;
    }

    public List<FridgeItem> getQuestsList() {
        return questsList;
    }

    public void markScanned(String description) {

        FridgeItem fridgeItem = null;

        // https://stackoverflow.com/questions/2965747/why-do-i-get-an-unsupportedoperationexception-when-trying-to-remove-an-element-f
        List<FridgeItem> list = new LinkedList<>(questsList);
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getDescription(), description)) {
                fridgeItem = list.remove(i);
                break;
            }
        }

        questsList = list;

        if (fridgeItem != null)
            badgesList.add(fridgeItem);
    }

    public List<FridgeItem> getBadgesList() {
        return badgesList;
    }
}
