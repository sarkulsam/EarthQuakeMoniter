package com.project.earthquake.di;

import com.project.earthquake.repository.IEarthQuakeRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {EarthQuakeDependenciesModule.class,
                        EarthQuakeNetworkModule.class})
public interface EarthQuakeAppComponent {
    IEarthQuakeRepository earthQuakeRepository();
}
