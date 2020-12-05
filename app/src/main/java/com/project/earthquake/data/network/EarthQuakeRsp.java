package com.project.earthquake.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EarthQuakeRsp {
    @Expose
    @SerializedName("earthquakes")
    List<EarthQuakeItem> items;

    public List<EarthQuakeItem> getItems() {
        return items;
    }
}
