package com.gus.hackaton;

import android.app.Application;

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
