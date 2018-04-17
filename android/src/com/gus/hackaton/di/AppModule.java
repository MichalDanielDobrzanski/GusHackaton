package com.gus.hackaton.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.gus.hackaton.repository.db.Storage;
import com.gus.hackaton.repository.db.StorageImpl;
import com.gus.hackaton.repository.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {


    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    public Storage providesStorage(Context context) {
        return new StorageImpl(context);
    }

    @Provides
    @Singleton
    public Repository providesRepository(Storage storage) {
        return new Repository(storage);
    }

    @Provides
    @Singleton
    public SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
