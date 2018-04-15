package com.gus.hackaton.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gus.hackaton.fridge.FridgeItem;

import java.lang.reflect.Type;
import java.util.List;

public class StorageImpl implements Storage {

    private static final String QUESTS_LIST = "QUESTS_LIST";
    private static final String BADGES_LIST = "BADGES_LIST";

    private final SharedPreferences sharedPreferences;

    private final SharedPreferences.Editor editor;

    public StorageImpl(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    @Override
    public void putQuestList(List<FridgeItem> quests) {
        setList(QUESTS_LIST, quests);
    }

    @Override
    public void putBadgeList(List<FridgeItem> badges) {
        setList(BADGES_LIST, badges);
    }

    @Override
    public List<FridgeItem> getQuestList() {
        List<FridgeItem> productFromShared;

        Type type = new TypeToken<List<FridgeItem>>() {}.getType();
        Gson gson = new Gson();
        String jsonPreferences = sharedPreferences.getString(QUESTS_LIST, "");
        productFromShared = gson.fromJson(jsonPreferences, type);

        return productFromShared;
    }

    @Override
    public List<FridgeItem> getBadgeList() {
        List<FridgeItem> productFromShared;

        Type type = new TypeToken<List<FridgeItem>>() {}.getType();
        Gson gson = new Gson();
        String jsonPreferences = sharedPreferences.getString(BADGES_LIST, "");
        productFromShared = gson.fromJson(jsonPreferences, type);

        return productFromShared;
    }

    private <T> void setList(String key, List<T> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        set(key, json);
    }

    private void set(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

}
