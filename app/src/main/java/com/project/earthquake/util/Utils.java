package com.project.earthquake.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static List<Address> getAddress(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            if(!Geocoder.isPresent()) {
                Log.e("Utils", "Geocoder is not present!");
                return null;
            }

            addresses = geocoder.getFromLocation(lat, lng, 1);
            if(addresses.size() == 0) return null;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addresses;
    }
}
