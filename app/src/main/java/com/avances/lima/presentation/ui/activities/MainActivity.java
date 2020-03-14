package com.avances.lima.presentation.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.avances.lima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.lima.data.datasource.db.AppLimaDb;
import com.avances.lima.data.datasource.db.model.DbCountry;
import com.avances.lima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.lima.data.datasource.db.model.DbEvent;
import com.avances.lima.data.datasource.db.model.DbGender;
import com.avances.lima.data.datasource.db.model.DbInterest;
import com.avances.lima.data.datasource.db.model.DbPermanencyDay;
import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.data.datasource.db.model.DbRoute;
import com.avances.lima.data.datasource.db.model.DbSuggestedTag;
import com.avances.lima.data.mapper.CountryDataMapper;
import com.avances.lima.data.mapper.DistritNeighborhoodDataMapper;
import com.avances.lima.data.mapper.EventDataMapper;
import com.avances.lima.data.mapper.GenderDataMapper;
import com.avances.lima.data.mapper.InterestDataMapper;
import com.avances.lima.data.mapper.PermanencyDayDataMapper;
import com.avances.lima.data.mapper.PlaceDataMapper;
import com.avances.lima.data.mapper.RouteDataMapper;
import com.avances.lima.data.mapper.SuggestedTagDataMapper;
import com.avances.lima.domain.model.Country;
import com.avances.lima.domain.model.DistritNeighborhood;
import com.avances.lima.domain.model.Event;
import com.avances.lima.domain.model.Gender;
import com.avances.lima.domain.model.Interest;
import com.avances.lima.domain.model.PermanencyDay;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.model.Route;
import com.avances.lima.domain.model.SuggestedTag;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.interactor.synchronization.SynchronizationCallback;
import com.avances.lima.interactor.synchronization.VerifySyncCallback;
import com.avances.lima.presentation.presenter.CountryPresenter;
import com.avances.lima.presentation.presenter.DistritNeighborhoodPresenter;
import com.avances.lima.presentation.presenter.EventPresenter;
import com.avances.lima.presentation.presenter.GenderPresenter;
import com.avances.lima.presentation.presenter.InterestPresenter;
import com.avances.lima.presentation.presenter.PermanencyDayPresenter;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.presenter.RoutePresenter;
import com.avances.lima.presentation.presenter.SuggestedTagPresenter;
import com.avances.lima.presentation.presenter.SynchronizationPresenter;
import com.avances.lima.presentation.presenter.UsuarioPresenter;
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
import com.avances.lima.presentation.view.CountryView;
import com.avances.lima.presentation.view.DistritNeighborhoodView;
import com.avances.lima.presentation.view.EventView;
import com.avances.lima.presentation.view.GenderView;
import com.avances.lima.presentation.view.InterestView;
import com.avances.lima.presentation.view.PermanencyDayView;
import com.avances.lima.presentation.view.PlaceView;
import com.avances.lima.presentation.view.RouteView;
import com.avances.lima.presentation.view.SuggestedTagView;
import com.avances.lima.presentation.view.SynchronizationView;
import com.avances.lima.presentation.view.UsuarioView;
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
import java.util.ArrayList;
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
        PlaceDetailActivity.LikeAPlace, SynchronizationView,
        AccountFragment.fragmentVisibleAccount,
        EventsFragment.fragmentVisibleEvent, PlaceView, DistritNeighborhoodView, InterestView,
        RouteView, EventView, UsuarioView, CountryView, GenderView, SuggestedTagView, PermanencyDayView {


    FrameLayout containerView;
    LocationEngine locationEngine;
    private long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    boolean tagForSearch;

    TransparentProgressDialog loading;
    public static int FRAGMENT_VIEWING = 0;
    public static boolean CARGO_TODO = false;

    SynchronizationPresenter synchronizationPresenter;


    PlacePresenter placePresenter;
    DistritNeighborhoodPresenter distritNeighborhoodPresenter;
    InterestPresenter interestPresenter;
    RoutePresenter routePresenter;
    EventPresenter eventPresenter;
    UsuarioPresenter usuarioPresenter;
    CountryPresenter countryPresenter;
    GenderPresenter genderPresenter;
    PermanencyDayPresenter permanencyDayPresenter;
    SuggestedTagPresenter suggestedTagPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (hasLocationPermission()) {
            UserLocation userLocation = new UserLocation(getApplication(), this);
            userLocation.getLocation();
            loadPresenter();
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

    private void loadPresenter() {
        synchronizationPresenter = new SynchronizationPresenter();
        synchronizationPresenter.addView(this);
    }

    private void initUI() {
        loading = new TransparentProgressDialog(this);
        containerView = (FrameLayout) findViewById(R.id.containerView);
        tagForSearch = false;
        // loading = new TransparentProgressDialog(getApplicationContext());
        CARGO_TODO = false;
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


    void syncall(WsSynchronization wsSynchronization) {

        UserPreference userPreference;


        synchronizationPresenter = new SynchronizationPresenter();
        synchronizationPresenter.addView(this);

        placePresenter = new PlacePresenter();
        placePresenter.addView(this);

        distritNeighborhoodPresenter = new DistritNeighborhoodPresenter();
        distritNeighborhoodPresenter.addView(this);

        interestPresenter = new InterestPresenter();
        interestPresenter.addView(this);

        routePresenter = new RoutePresenter();
        routePresenter.addView(this);

        eventPresenter = new EventPresenter();
        eventPresenter.addView(this);

        usuarioPresenter = new UsuarioPresenter();
        usuarioPresenter.addView(this);

        countryPresenter = new CountryPresenter();
        countryPresenter.addView(this);

        genderPresenter = new GenderPresenter();
        genderPresenter.addView(this);

        suggestedTagPresenter = new SuggestedTagPresenter();
        suggestedTagPresenter.addView(this);

        permanencyDayPresenter = new PermanencyDayPresenter();
        permanencyDayPresenter.addView(this);

        WsData wsData = wsSynchronization.getWsData();

        PlaceDataMapper placeDataMapper = new PlaceDataMapper();
        ArrayList<DbPlace> dbPlaces = placeDataMapper.transformWsToDb(wsData);
        placePresenter.createPlaces(dbPlaces, Constants.STORE.DB);

        DistritNeighborhoodDataMapper distritNeighborhoodDataMapper = new DistritNeighborhoodDataMapper();
        ArrayList<DbDistritNeighborhood> dbDistritNeighborhoods = distritNeighborhoodDataMapper.transformWsToDb(wsData);
        distritNeighborhoodPresenter.createDistritNeighborhoods(dbDistritNeighborhoods, Constants.STORE.DB);

        InterestDataMapper interestDataMapper = new InterestDataMapper();
        ArrayList<DbInterest> dbInterests = interestDataMapper.transformWsToDb(wsData);
        interestPresenter.createInterests(dbInterests, Constants.STORE.DB);

        RouteDataMapper routeDataMapper = new RouteDataMapper();
        ArrayList<DbRoute> dbRoutes = routeDataMapper.transformWsToDb(wsData);
        routePresenter.createRoutes(dbRoutes, Constants.STORE.DB);

        EventDataMapper eventDataMapper = new EventDataMapper();
        ArrayList<DbEvent> dbEvents = eventDataMapper.transformWsToDb(wsData);
        eventPresenter.createEvents(dbEvents, Constants.STORE.DB);

        CountryDataMapper countryDataMapper = new CountryDataMapper();
        ArrayList<DbCountry> dbCountries = countryDataMapper.transformWsToDb(wsData);
        countryPresenter.createCountry(dbCountries, Constants.STORE.DB);

        GenderDataMapper genderDataMapper = new GenderDataMapper();
        ArrayList<DbGender> dbGenders = genderDataMapper.transformWsToDb(wsData);
        genderPresenter.createGender(dbGenders, Constants.STORE.DB);

        PermanencyDayDataMapper permanencyDayDataMapper = new PermanencyDayDataMapper();
        ArrayList<DbPermanencyDay> dbPermanencyDays = permanencyDayDataMapper.transformWsToDb(wsData);
        permanencyDayPresenter.createPermanencyDay(dbPermanencyDays, Constants.STORE.DB);

        SuggestedTagDataMapper suggestedTagDataMapper = new SuggestedTagDataMapper();
        ArrayList<DbSuggestedTag> dbSuggestedTags = suggestedTagDataMapper.transformWsToDb(wsData);
        suggestedTagPresenter.createSuggestedTag(dbSuggestedTags, Constants.STORE.DB);

        String lastDateSync = wsData.getWsDataVerifySynchronization().getDateLastSynchronization();

        userPreference = Helper.getUserAppPreference(getContext());
        userPreference.setFirstSyncSuccess(true);
        userPreference.setLastDateSynchronization(lastDateSync);
        Helper.saveUserAppPreference(getContext(), userPreference);
    }

    @Override
    public void syncSuccesfull(WsSynchronization wsData) {

        deleteSQLITE();

        timerSync(20, wsData);

        next(MainActivity.class, null);

    }

    @Override
    public void verifiedSync(boolean sync) {
        if (sync) {
            UserPreference userPreference = Helper.getUserAppPreference(getContext());
            String token = userPreference.getToken();
            if (!loading.isShowing()) {
                loading.show();
            }
            synchronizationPresenter.syncAll(token, Constants.STORE.CLOUD);

            Toast.makeText(this, "Sincronización en curso", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void countryListLoaded(List<Country> countries) {

    }

    @Override
    public void countryCreated(String message) {

    }

    @Override
    public void distritNeighborhoodListLoaded(List<DistritNeighborhood> distritNeighborhoods) {

    }

    @Override
    public void distritCreated(String message) {

    }

    @Override
    public void distritUpdated(String message) {

    }

    @Override
    public void eventListLoaded(List<Event> events) {

    }

    @Override
    public void eventCreated(String message) {

    }

    @Override
    public void eventUpdated(String message) {

    }

    @Override
    public void genderListLoaded(List<Gender> genders) {

    }

    @Override
    public void genderCreated(String message) {

    }

    @Override
    public void interestListLoaded(List<Interest> dbInterests) {

    }

    @Override
    public void interestCreated(String message) {

    }

    @Override
    public void interestUpdated(String message) {

    }

    @Override
    public void permanencyDayListLoaded(List<PermanencyDay> permanencyDays) {

    }

    @Override
    public void permanencyDayCreated(String message) {

    }

    @Override
    public void placeListLoaded(List<Place> places) {

    }

    @Override
    public void placeCreated(String message) {

    }

    @Override
    public void placeUpdated(String message) {

    }

    @Override
    public void routeListLoaded(List<Route> routes) {

    }

    @Override
    public void routeCreated(String message) {

    }

    @Override
    public void routeUpdated(String message) {

    }

    @Override
    public void suggestedTagListLoaded(List<SuggestedTag> suggestedTags) {

    }

    @Override
    public void suggestedTagCreated(String message) {

    }

    @Override
    public void temporalUserRegistered(String idTempUser) {

    }

    @Override
    public void tokenGenerated(String token) {

    }

    @Override
    public void userRegistered(Usuario usuario) {

    }

    @Override
    public void loginSuccess(Usuario usuario) {

    }

    @Override
    public void loginSocialMediaSuccess(Usuario usuario) {

    }

    @Override
    public void forgotPasswordSuccess(String message) {

    }

    @Override
    public void reSendCodeSuccess(String message) {

    }

    @Override
    public void userGot(Usuario usuario) {

    }

    @Override
    public void validateCodeSuccess(Usuario usuario) {

    }

    @Override
    public void routesByInterestSuccess(List<String> idRoutes) {

    }

    @Override
    public void userUpdated(Usuario usuario) {

    }

    @Override
    public void versionApp(String version) {

    }

    @Override
    public void imageUploaded(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public Context getContext() {
        return this;
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

        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        String lastDateSync = userPreference.getLastDateSynchronization();

        if (synchronizationPresenter != null) {
            synchronizationPresenter.verifySync(Helper.getUserAppPreference(getApplicationContext()).getToken(), lastDateSync);
        } else {
            loadPresenter();
            {
                synchronizationPresenter.verifySync(Helper.getUserAppPreference(getApplicationContext()).getToken(), lastDateSync);
            }
        }
    }

    private void deleteSQLITE() {
        try {
            getContext().deleteDatabase(AppLimaDb.DB_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    timerSecondsToOffer(10);
                } else {
                    initUI();
                    loadTabHomeFragment();
                    timerSecondsToOffer(10);
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

        long secondsMill = seconds * 10;

        new CountDownTimer(10000, secondsMill) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                loadSecondsToOfferFragment();
            }

        }.start();

    }


    void timerSync(Integer seconds, WsSynchronization wsSynchronization) {


        Integer total = seconds * 1000;

        if (!Helper.getUserAppPreference(getApplicationContext()).isSecondsToOfferViewed()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    syncall(wsSynchronization);

                    if (loading.isShowing()) {
                        loading.dismiss();
                    }
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

            Bundle bundle = new Bundle();
            bundle.putInt("FRAGMENT_VIEWING", FRAGMENT_VIEWING);

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

        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);

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
