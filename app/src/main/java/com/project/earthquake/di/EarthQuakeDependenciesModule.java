package com.project.earthquake.di;

import com.project.earthquake.data.network.EarthQuakeWebService;
import com.project.earthquake.data.network.IEarthQuakeWebService;
import com.project.earthquake.repository.EarthQuakeRepository;
import com.project.earthquake.repository.IEarthQuakeRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class EarthQuakeDependenciesModule {
    @Binds
    @Singleton
    abstract IEarthQuakeRepository provideEarthQuakeRepository(EarthQuakeRepository repository);

    @Binds
    @Singleton
    abstract IEarthQuakeWebService provideEarthQuakeWebService(EarthQuakeWebService service);
}
