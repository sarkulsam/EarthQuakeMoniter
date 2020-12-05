package com.project.earthquake.data.network;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class EarthQuakeWebService implements IEarthQuakeWebService {
    protected EarthQuakeApi earthQuakeApi;

    @Inject
    public EarthQuakeWebService(EarthQuakeApi api) {
        earthQuakeApi = api;
    }

    public Flowable<EarthQuakeRsp> fetchEarthQuakesData(float north,
                                                               float south,
                                                               float east,
                                                               float west,
                                                               String userName) {
        return earthQuakeApi.fetchEarthQuakeData(north, south, east, west, userName)
                .subscribeOn(Schedulers.io());
    }

}
