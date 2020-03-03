package com.avances.lima.presentation.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;

import androidx.annotation.NonNull;

import com.avances.lima.domain.model.UserPreference;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;

import static android.os.Looper.getMainLooper;


public class UserLocation implements LocationEngineCallback<LocationEngineResult> {

    Context context;
    LocationEngine locationEngine;
    private long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    Activity activity;


    public UserLocation(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }


    public interface CierraLocation {
        public void onCloseLocation(Boolean hasLocation, Location location);
    }

    void sendCallback(Boolean hasLocation, Location location) {
        Activity ahhh = activity;
        if (ahhh instanceof CierraLocation) {
            ((CierraLocation) ahhh).onCloseLocation(hasLocation, location);
        }

    }


    public void getLocation() {
        initLocationEngine();
    }

    public void stopGettingLocation() {
        locationEngine.removeLocationUpdates(this);
    }


    @Override
    public void onSuccess(LocationEngineResult result) {


        UserPreference userPreference = Helper.getUserAppPreference(context);

        Location location = result.getLastLocation();
        if (location != null) {
            Double lat = location.getLatitude();
            Double lng = location.getLongitude();
            userPreference.setLat(lat.toString());
            userPreference.setLng(lng.toString());
            userPreference.setHasLocation(true);
            Helper.saveUserAppPreference(context, userPreference);
               // sendCallback(true, location);
        } else {
            userPreference.setLat("0.0");
            userPreference.setLng("0.0");
            userPreference.setHasLocation(false);
            Helper.saveUserAppPreference(context, userPreference);
             //  sendCallback(false, location);
        }
    }

    @Override
    public void onFailure(@NonNull Exception exception) {
        String message = exception.getMessage();
    }


    @SuppressLint("MissingPermission")
    private void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(context);

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();

        locationEngine.requestLocationUpdates(request, this, getMainLooper());
        locationEngine.getLastLocation(this);
    }
}
