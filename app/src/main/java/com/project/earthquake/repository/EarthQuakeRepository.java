package com.project.earthquake.repository;

import com.project.earthquake.data.network.EarthQuakeItem;
import com.project.earthquake.data.network.EarthQuakeRsp;
import com.project.earthquake.data.network.IEarthQuakeWebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class EarthQuakeRepository implements IEarthQuakeRepository {
    protected IEarthQuakeWebService webService;

    @Inject
    public EarthQuakeRepository(IEarthQuakeWebService webService) {
        this.webService = webService;
    }

    @Override
    public Flowable<EarthQuakeRsp> getEarthQuakesData(float north,
                                                      float south,
                                                      float east,
                                                      float west,
                                                      String userName) {
        return webService.fetchEarthQuakesData(north, south, east, west, userName);
    }
}
