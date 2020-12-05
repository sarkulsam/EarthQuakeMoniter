package com.project.earthquake.data.network;

import java.util.List;

import io.reactivex.Flowable;

public interface IEarthQuakeWebService {
    Flowable<EarthQuakeRsp> fetchEarthQuakesData(float north,
                                                        float south,
                                                        float east,
                                                        float west,
                                                        String userName);
}
