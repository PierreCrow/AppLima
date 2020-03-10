package com.avances.lima.presentation.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.avances.lima.R;
import com.avances.lima.domain.model.Place;
import com.avances.lima.presentation.ui.adapters.EventsVerticalListDataAdapter;
import com.avances.lima.presentation.ui.dialogfragment.PickCameraGalleryDialog;
import com.avances.lima.presentation.ui.fragments.AccountFragment;
import com.avances.lima.presentation.ui.fragments.EventsFragment;
import com.avances.lima.presentation.ui.fragments.HomeLoggedFragment;
import com.avances.lima.presentation.ui.fragments.SearchFragment;
import com.avances.lima.presentation.ui.fragments.FavoritesFragment;
import com.avances.lima.presentation.ui.fragments.HomeFragment;
import com.avances.lima.presentation.ui.fragments.PlacesFragment;
import com.avances.lima.presentation.ui.fragments.RoutesFragment;
import com.avances.lima.presentation.ui.fragments.SecondsToOfferFragment;
import com.avances.lima.presentation.ui.fragments.TabHome;
import com.avances.lima.presentation.ui.fragments.TabHomePlacesFragment;
import com.avances.lima.presentation.ui.fragments.TabHomeRoutesFragment;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
import com.avances.lima.presentation.utils.UserLocation;
import com.google.rpc.Help;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class MainActivity extends BaseActivity implements
        SecondsToOfferFragment.CierraDialogSeconds,
        HomeFragment.GoToImperdiblesFragment,
        HomeFragment.GoToBuscador,
        HomeFragment.GoToRutasTematicasFragment,
        HomeLoggedFragment.GoToImperdiblesLoggedFragment,
        HomeLoggedFragment.GoToBuscadorLogged,
        HomeLoggedFragment.GoToRutasTematicasLoggedFragment,
        PlacesFragment.GoToTabHomeFragment,
        SearchFragment.GoHome,
        RoutesFragment.GoToTabHomeFragmentFromRutasTematicas,
        UserLocation.CierraLocation,
        PlaceDetailActivity.LikeAPlace,

AccountFragment.fragmentVisibleAccount,
        EventsFragment.fragmentVisibleEvent {


    FrameLayout containerView;
    LocationEngine locationEngine;
    private long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    boolean tagForSearch;

    TransparentProgressDialog loading;
    public static int FRAGMENT_VIEWING=0;
    public static boolean CARGO_TODO=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasLocationPermission()) {
            UserLocation userLocation = new UserLocation(getApplication(), this);
            userLocation.getLocation();

            initUI();
            loadTabHomeFragment();
            timerSecondsToOffer(10);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constants.REQUEST_CODES.REQUEST_CODE_LOCATION);
        }
    }

    private void initUI() {

        containerView = (FrameLayout) findViewById(R.id.containerView);
        tagForSearch = false;
        loading = new TransparentProgressDialog(getApplicationContext());
        CARGO_TODO=false;
        loadTabHomeFragment();
    }

    public boolean hasLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
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
        Fragment ahhh = new FavoritesFragment();
        if (ahhh instanceof LikeAPlaceAddFavorite) {
            ((LikeAPlaceAddFavorite) ahhh).onLikeAPlaceAddFavorite(place);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //refreshListHome
     //   loadTabHomeFragment();
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

   /*     loading = new TransparentProgressDialog(MainActivity.this);
        if (!loading.isShowing()) {
            loading.show();
        }
*/
        Bitmap photo = null;

        if (requestCode == 66502 || requestCode == Constants.REQUEST_CODES.REQUEST_CODE_CAMERA) {
            photo = (Bitmap) data.getExtras().get("data");
        } else {
            if (requestCode == 65660 || requestCode == Constants.REQUEST_CODES.REQUEST_CODE_STORAGE) {
                final Uri imageUri = data.getData();
                InputStream imageStream = null;
                try {
                    imageStream = getContentResolver().openInputStream(imageUri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                photo = BitmapFactory.decodeStream(imageStream);
            }
        }

        String encodedImage = encodeImage(photo);
    /*    if (loading.isShowing()) {
            loading.dismiss();
        }*/
        AccountFragment.goPicture(encodedImage);

    }

    private String encodeImage(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encImage;
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
                    initUI();
                    loadTabHomeFragment();
                    timerSecondsToOffer(4);
                } else {
                    initUI();
                    loadTabHomeFragment();
                    timerSecondsToOffer(4);
                }
                return;
            }
            case Constants.REQUEST_CODES.REQUEST_CODE_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (PickCameraGalleryDialog.importTypee == 0) {
                    } else {
                        displayCameraOrGallery(Constants.TYPE_PHOTO_IMPORT.GALLERY);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Debe aceptar para subir su foto", Toast.LENGTH_LONG).show();
                }
                return;
            }
            case Constants.REQUEST_CODES.REQUEST_CODE_CALENDAR: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String dtStart = EventsVerticalListDataAdapter.eventClicked.getStartDate();
                    String dtFinal = EventsVerticalListDataAdapter.eventClicked.getFinalDate();
                    String tittle = EventsVerticalListDataAdapter.eventClicked.getTittle();
                    String desxcription = EventsVerticalListDataAdapter.eventClicked.getDescription();
                    Helper.addCalendarEvent(getApplicationContext(), tittle, desxcription, dtStart, dtFinal);
                    Toast.makeText(getApplicationContext(), "Agregado a Calendario", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No se puede añadir evento por denegar permiso a calendario", Toast.LENGTH_LONG).show();
                }
                return;
            }

            case Constants.REQUEST_CODES.REQUEST_CODE_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    displayCameraOrGallery(Constants.TYPE_PHOTO_IMPORT.CAMERA);
                } else {
                    Toast.makeText(getApplicationContext(), "Debe aceptar para subir su foto", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    private void displayCameraOrGallery(int i) {
        if (i == Constants.TYPE_PHOTO_IMPORT.CAMERA) {
            openCamera();
        } else {
            openGallery();
        }

    }

    private void openCamera() {
        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, Constants.REQUEST_CODES.REQUEST_CODE_CAMERA);
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, Constants.REQUEST_CODES.REQUEST_CODE_STORAGE);
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

        if (tagForSearch) {
            next(MainActivity.class, null);
        } else {

            Bundle bundle= new Bundle();
            bundle.putInt("FRAGMENT_VIEWING",FRAGMENT_VIEWING);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
            TabHome homeFragment = new TabHome();
               homeFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.containerView, homeFragment);
            fragmentTransaction.commit();
        }
    }


    void loadImperdiblesFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
        TabHomePlacesFragment homeFragment = new TabHomePlacesFragment();
        fragmentTransaction.replace(R.id.containerView, homeFragment);
        fragmentTransaction.commit();
    }


    void loadRutasTematicasFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
        TabHomeRoutesFragment homeFragment = new TabHomeRoutesFragment();
        fragmentTransaction.replace(R.id.containerView, homeFragment);
        fragmentTransaction.commit();
    }


    void loadBuscadorFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.slide_up_down, R.anim.slide_bottom);
        SearchFragment homeFragment = new SearchFragment();
        fragmentTransaction.replace(R.id.containerView, homeFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onClose_Seconds(Boolean close) {

    }


    @Override
    public void goToImperdibles() {
        loadImperdiblesFragment();
    }

    @Override
    public void goToRutasTematicas() {
        loadRutasTematicasFragment();
    }

    @Override
    public void goToBuscador() {

        loadBuscadorFragment();
    }


    @Override
    public void goToImperdiblesLogged() {
        loadImperdiblesFragment();
    }

    @Override
    public void goToRutasTematicasLogged() {
        loadRutasTematicasFragment();
    }

    @Override
    public void goToBuscadorLogged() {
        loadBuscadorFragment();
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
    public void goToHomeFromRutasTematicas() {
        loadTabHomeFragment();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.containerView);
        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            super.onBackPressed();
        }

    }



    public interface IOnBackPressed {
        /**
         * If you return true the back press will not be taken into account, otherwise the activity will act naturally
         *
         * @return true if your processing has priority if not false
         */
        boolean onBackPressed();
    }


    @Override
    public void onPause() {
        super.onPause();



    }



    @Override
    public void onFragmentVisibleAccount(int fragmentViewing) {

   //     FRAGMENT_VIEWING=fragmentViewing;

    }

    @Override
    public void onFragmentVisibleEvent(int fragmentViewing) {
     //   FRAGMENT_VIEWING=fragmentViewing;
    }



}
