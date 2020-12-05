package com.project.earthquake.data.network;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EarthQuakeItem implements Parcelable {
    @Expose
    @SerializedName("eqid")
    private String id;
    @Expose
    @SerializedName("magnitude")
    private float magnitude;
    @Expose
    @SerializedName("datetime")
    private String timestamp;
    @Expose
    @SerializedName("src")
    private String src;
    @Expose
    @SerializedName("depth")
    private float depth;
    @Expose
    @SerializedName("lat")
    private double lat;
    @Expose
    @SerializedName("lng")
    private double lng;

    private long timeInMillis;
    //private String title;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(float magnitude) {
        this.magnitude = magnitude;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    protected EarthQuakeItem(Parcel in) {
        id = in.readString();
        magnitude = in.readFloat();
        timestamp = in.readString();
        src = in.readString();
        depth = in.readFloat();
        lat = in.readDouble();
        lng = in.readDouble();
        timeInMillis = in.readLong();
    }

    public static final Creator<EarthQuakeItem> CREATOR = new Creator<EarthQuakeItem>() {
        @Override
        public EarthQuakeItem createFromParcel(Parcel in) {
            return new EarthQuakeItem(in);
        }

        @Override
        public EarthQuakeItem[] newArray(int size) {
            return new EarthQuakeItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeFloat(magnitude);
        dest.writeString(timestamp);
        dest.writeString(src);
        dest.writeFloat(depth);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeLong(timeInMillis);
    }
}
