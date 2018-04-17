package com.gus.hackaton.di;

import com.gus.hackaton.MainActivity;
import com.gus.hackaton.ScanActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(ScanActivity scanActivity);
}
