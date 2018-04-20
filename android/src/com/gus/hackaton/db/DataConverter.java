package com.gus.hackaton.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gus.hackaton.model.EurostatData;

import java.lang.reflect.Type;
import java.util.List;

public class DataConverter
{
    @TypeConverter
    public String fromEurostatList(List<EurostatData> eurostatData) {
        if (eurostatData == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<EurostatData>>() {
        }.getType();
        String json = gson.toJson(eurostatData, type);
        return json;
    }

    @TypeConverter
    public List<EurostatData> toEurostatDataList(String eurostatDataString) {
        if (eurostatDataString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<EurostatData>>() {
        }.getType();
        List<EurostatData> eurostatData = gson.fromJson(eurostatDataString, type);
        return eurostatData;
    }
}
