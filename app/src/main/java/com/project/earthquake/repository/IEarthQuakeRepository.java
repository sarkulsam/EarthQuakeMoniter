package com.project.earthquake.repository;

import com.project.earthquake.data.network.EarthQuakeItem;
import com.project.earthquake.data.network.EarthQuakeRsp;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface IEarthQuakeRepository {
    Flowable<EarthQuakeRsp> getEarthQuakesData(float north,
                                               float south,
                                               float east,
                                               float west,
                                               String userName);
}
