package com.project.earthquake.data.network;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EarthQuakeApi {
    @GET("earthquakesJSON")
    Flowable<EarthQuakeRsp> fetchEarthQuakeData(@Query("north") float north,
                                                       @Query("south") float south,
                                                       @Query("east") float east,
                                                       @Query("west") float west,
                                                       @Query("username") String userName);
}
