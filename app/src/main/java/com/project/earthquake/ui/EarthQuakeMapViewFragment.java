package com.project.earthquake.ui;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.earthquake.R;
import com.project.earthquake.data.network.EarthQuakeItem;
import com.project.earthquake.ui.common.EarthQuakeBaseFragment;
import com.project.earthquake.util.Constants;
import com.project.earthquake.util.Utils;

import java.util.List;

/**
 * This class will show selected earthquake position on map view.
 */
public class EarthQuakeMapViewFragment extends EarthQuakeBaseFragment implements OnMapReadyCallback {

    protected MapView mMapView;
    private EarthQuakeItem earthQuakeItem = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_earth_quake_map_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = view.findViewById(R.id.mapView);
        earthQuakeItem = (EarthQuakeItem) getArguments().get(Constants.BUNDLE_KEY_EQ_ITEMS);

        mMapView.onCreate(savedInstanceState);

        mMapView.getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mMapView != null) mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mMapView != null) mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mMapView != null) mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        List<Address> addresses = Utils.getAddress(getContext(),
                earthQuakeItem.getLat(),
                earthQuakeItem.getLng());
        String title = Constants.UNKNOWN;
        if(addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            if(!TextUtils.isEmpty(address.getLocality())) {
                title = address.getLocality();
            }
        }
        LatLng loc = new LatLng(earthQuakeItem.getLat(), earthQuakeItem.getLng());
        map.addMarker(new MarkerOptions()
                .position(loc)
                .title(title));
        map.moveCamera(CameraUpdateFactory.newLatLng(loc));

    }

    @Override
    public void onPause() {
        if(mMapView != null) mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if(mMapView != null) mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(mMapView != null) mMapView.onLowMemory();
    }
}


