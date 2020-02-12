package com.avances.applima.presentation.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.applima.R;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.presentation.ui.adapters.EventosVerticalListDataAdapter;
import com.avances.applima.presentation.ui.dialogfragment.EventDetail;
import com.avances.applima.presentation.ui.dialogfragment.FilterDialog;
import com.avances.applima.presentation.ui.fragments.BuscadorFragment;
import com.avances.applima.presentation.ui.fragments.FavoritosFragment;
import com.avances.applima.presentation.ui.fragments.HomeFragment;
import com.avances.applima.presentation.ui.fragments.ImperdiblesFragment;
import com.avances.applima.presentation.ui.fragments.RutasTematicasFragment;
import com.avances.applima.presentation.ui.fragments.SecondsToOfferFragment;
import com.avances.applima.presentation.ui.fragments.TabHome;
import com.avances.applima.presentation.ui.fragments.TabHomeImperdibles;
import com.avances.applima.presentation.ui.fragments.TabHomeRutasTematicas;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.UserLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends BaseActivity implements
        SecondsToOfferFragment.CierraDialogSeconds,
        HomeFragment.GoToImperdiblesFragment,
        HomeFragment.GoToBuscador,
        HomeFragment.GoToRutasTematicasFragment,
        ImperdiblesFragment.GoToTabHomeFragment,
        BuscadorFragment.GoHome,
        RutasTematicasFragment.GoToTabHomeFragmentFromRutasTematicas,
        UserLocation.CierraLocation,
        PlaceDetailActivity.LikeAPlace {


    FrameLayout containerView;
    UserPreference userPreference;

    private FusedLocationProviderClient fusedLocationClient;
    LocationEngine locationEngine;
    private long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;

    boolean tagForSearch;


    public boolean hasLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }


    public boolean hasStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            return false;
        } else {
            return true;
        }
    }


    @SuppressLint("MissingPermission")
    private void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this);

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();

        locationEngine.requestLocationUpdates(request, callback, getMainLooper());
        locationEngine.getLastLocation(callback);
    }

    private MainActivityLocationCallback callback = new MainActivityLocationCallback(this);

    @Override
    public void onCloseLocation(Boolean hasLocation, Location location) {

        if (hasLocation) {

        }
    }


    public interface LikeAPlaceAddFavorite {
        public void onLikeAPlaceAddFavorite(Place place);
    }


    public void sendCallback(Place place) {


        Fragment ahhh = new FavoritosFragment();
        if (ahhh instanceof LikeAPlaceAddFavorite) {
            ((LikeAPlaceAddFavorite) ahhh).onLikeAPlaceAddFavorite(place);
        }

   /*     FragmentManager fragmentManager = getSupportFragmentManager();

        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null && fragment.isVisible() && fragment instanceof FavoritosFragment) {
                ((FavoritosFragment) fragment).refreshList(place);
            }
        }
*/

    }

    @Override
    protected void onResume() {
        super.onResume();

        //refreshListHome
        loadTabHomeFragment();
    }

    @Override
    public void onLike(Place place) {

        sendCallback(place);
    }


    private static class MainActivityLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<MainActivity> activityWeakReference;

        MainActivityLocationCallback(MainActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location has changed.
         *
         * @param result the LocationEngineResult object which has the last known location within it.
         */
        @Override
        public void onSuccess(LocationEngineResult result) {
            MainActivity activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();
                String looo = location.toString();


                if (location == null) {
                    return;
                }

            }
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location can not be captured
         *
         * @param exception the exception message
         */
        @Override
        public void onFailure(@NonNull Exception exception) {
            Log.d("LocationChangeActivity", exception.getLocalizedMessage());
            MainActivity activity = activityWeakReference.get();
            if (activity != null) {
                Toast.makeText(activity, exception.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Constants.REQUEST_CODES.REQUEST_CODE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UserLocation userLocation = new UserLocation(getApplication(), this);
                    userLocation.getLocation();

                    if (!hasStoragePermission()) {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE},
                                Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
                    }

                    // permission was granted, yay! Do the
                    initUI();
                    loadTabHomeFragment();
                    timerSecondsToOffer(4);
                } else {
                    // permission denied, boo! Disable the
                    initUI();
                    loadTabHomeFragment();
                    timerSecondsToOffer(4);
                }
                return;
            }

            case Constants.REQUEST_CODES.REQUEST_CODE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the

                }
                return;
            }

            case Constants.REQUEST_CODES.REQUEST_CODE_CALENDAR: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //    String hola= EventosVerticalListDataAdapter.completeDate;
                    String dtStart = EventosVerticalListDataAdapter.eventClicked.getStartDate();
                    String dtFinal = EventosVerticalListDataAdapter.eventClicked.getFinalDate();
                    String tittle = EventosVerticalListDataAdapter.eventClicked.getTittle();
                    String desxcription = EventosVerticalListDataAdapter.eventClicked.getDescription();
                    Helper.addCalendarEvent(getApplicationContext(), tittle, desxcription, dtStart, dtFinal);
                    Toast.makeText(getApplicationContext(), "Agregado a Calendario", Toast.LENGTH_SHORT).show();

                } else {
                    // permission denied, boo! Disable the
                    Toast.makeText(getApplicationContext(), "No se puede añadir evento por denegar permiso a calendario", Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasLocationPermission()) {
            UserLocation userLocation = new UserLocation(getApplication(), this);
            userLocation.getLocation();

            if (!hasStoragePermission()) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
            }

            initUI();
            loadTabHomeFragment();
            timerSecondsToOffer(4);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constants.REQUEST_CODES.REQUEST_CODE_LOCATION);
        }





   /*     fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            userPreference = Helper.getUserAppPreference(getApplicationContext());
                            userPreference.setLat(String.valueOf(location.getLatitude()));
                            userPreference.setLng(String.valueOf(location.getLongitude()));
                            Helper.saveUserAppPreference(getApplicationContext(), userPreference);
                        }
                    }
                });
*/
      /*  GPSTracker gpsTracker= new GPSTracker(getApplicationContext());
        if(gpsIsEnabled()) {
            userPreference = Helper.getUserAppPreference(getApplicationContext());
            userPreference.setLat(String.valueOf(gpsTracker.getLatitude()));
            userPreference.setLng(String.valueOf(gpsTracker.getLongitude()));
            Helper.saveUserAppPreference(getApplicationContext(), userPreference);
        }
*/


        //   Helper.hideKeyboard(MainActivity.this);

        //   initUI();
        //   loadTabHomeFragment();
        //  timerSecondsToOffer(4);


    }

    private void initUI() {

        containerView = (FrameLayout) findViewById(R.id.containerView);

        tagForSearch = false;

        loadTabHomeFragment();


    }


    void timerSecondsToOffer(Integer seconds) {
        Integer total = seconds * 1000;

        if (!Helper.getUserAppPreference(getApplicationContext()).isSecondsToOfferViewed()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadSecondsToOfferFragment();
                }
            }, 3000);
        }

    }


    void loadSecondsToOfferFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        SecondsToOfferFragment accountFragment = new SecondsToOfferFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }


    void loadTabHomeFragment() {

        // Bundle bundle= new Bundle();
        // bundle.putStringArrayList("tags", (ArrayList<String>) tags);

        if (tagForSearch) {
            next(MainActivity.class, null);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
            TabHome homeFragment = new TabHome();
            //   homeFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.containerView, homeFragment);
            fragmentTransaction.commit();
        }


    }


    void loadImperdiblesFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
        TabHomeImperdibles homeFragment = new TabHomeImperdibles();
        fragmentTransaction.replace(R.id.containerView, homeFragment);
        fragmentTransaction.commit();
    }


    void loadRutasTematicasFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
        TabHomeRutasTematicas homeFragment = new TabHomeRutasTematicas();
        fragmentTransaction.replace(R.id.containerView, homeFragment);
        fragmentTransaction.commit();
    }


    void loadBuscadorFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
        BuscadorFragment homeFragment = new BuscadorFragment();
        fragmentTransaction.replace(R.id.containerView, homeFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClose_Seconds(Boolean close) {

    /*    ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) containerView.getLayoutParams();
        params.height = 0;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        containerView.setLayoutParams(params);
        tabbarMenu.setVisibility(View.VISIBLE);*/

        // loadTabHomeFragment();
    }


    @Override
    public void goToImperdibles() {
        loadImperdiblesFragment();
    }

    @Override
    public void goToHome() {
        loadTabHomeFragment();
    }

    @Override
    public void gotoHome(boolean serach) {
        tagForSearch = serach;
        loadTabHomeFragment();
    }

    @Override
    public void goToBuscador() {

        loadBuscadorFragment();
    }

    @Override
    public void goToHomeFromRutasTematicas() {
        loadTabHomeFragment();
    }

    @Override
    public void goToRutasTematicas() {
        loadRutasTematicasFragment();
    }
}
