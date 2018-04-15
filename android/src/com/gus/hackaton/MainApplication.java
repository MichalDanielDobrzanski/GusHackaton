package com.gus.hackaton;

import android.app.Application;
import android.content.SharedPreferences;

import com.gus.hackaton.db.Storage;
import com.gus.hackaton.db.StorageImpl;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("default.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

}
