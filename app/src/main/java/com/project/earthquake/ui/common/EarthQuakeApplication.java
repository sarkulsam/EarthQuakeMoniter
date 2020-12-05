package com.project.earthquake.ui.common;

import android.app.Application;

import com.project.earthquake.di.DaggerEarthQuakeAppComponent;
import com.project.earthquake.di.EarthQuakeAppComponent;
import com.project.earthquake.di.EarthQuakeNetworkModule;

public class EarthQuakeApplication extends Application {
    private EarthQuakeAppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
         appComponent =  DaggerEarthQuakeAppComponent.builder()
                .earthQuakeNetworkModule(new EarthQuakeNetworkModule())
                .build();

    }

    public EarthQuakeAppComponent getAppComponent() {
        return appComponent;
    }
}
