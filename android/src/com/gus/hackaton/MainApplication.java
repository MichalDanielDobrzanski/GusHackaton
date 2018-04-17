package com.gus.hackaton;

import android.app.Application;

import com.gus.hackaton.di.AppComponent;
import com.gus.hackaton.di.AppModule;
import com.gus.hackaton.di.DaggerAppComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("default.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
