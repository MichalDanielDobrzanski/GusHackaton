package com.gus.hackaton.db;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class DataConverter
{
    @TypeConverter
    public String fromFloatList(List<Float> eurostatData) {
        if (eurostatData == null) {
            return (null);
        }

        JsonObject o = new JsonObject();
        for (int i = 0; i < eurostatData.size(); ++i) {
            o.addProperty(String.valueOf(i), eurostatData.get(i));
        }
        return o.toString();
    }

    @TypeConverter
    public List<Float> toFloatList(String eurostatDataString) {
        if (eurostatDataString == null) {
            return (null);
        }

        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(eurostatDataString).getAsJsonObject();
        List<Float> eurostatData = new ArrayList<>();

        for(int i = 0; i < 5; ++i) {
            JsonPrimitive prim = o.getAsJsonPrimitive(String.valueOf(i));
            if(prim != null)
                eurostatData.add(prim.getAsFloat());
        }

        return eurostatData;
    }
}
