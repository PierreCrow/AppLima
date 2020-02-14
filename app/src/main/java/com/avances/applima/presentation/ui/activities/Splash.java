package com.avances.applima.presentation.ui.activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.cloud.model.synchronization.WsData;
import com.avances.applima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.applima.data.datasource.db.model.DbCountry;
import com.avances.applima.data.datasource.db.model.DbDistritNeighborhood;
import com.avances.applima.data.datasource.db.model.DbEvent;
import com.avances.applima.data.datasource.db.model.DbGender;
import com.avances.applima.data.datasource.db.model.DbInterest;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.data.datasource.db.model.DbRoute;
import com.avances.applima.data.datasource.db.model.DbSuggestedTag;
import com.avances.applima.data.mapper.CountryDataMapper;
import com.avances.applima.data.mapper.DistritNeighborhoodDataMapper;
import com.avances.applima.data.mapper.EventDataMapper;
import com.avances.applima.data.mapper.GenderDataMapper;
import com.avances.applima.data.mapper.InterestDataMapper;
import com.avances.applima.data.mapper.PlaceDataMapper;
import com.avances.applima.data.mapper.RouteDataMapper;
import com.avances.applima.data.mapper.SuggestedTagDataMapper;
import com.avances.applima.domain.model.Country;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.domain.model.Event;
import com.avances.applima.domain.model.Gender;
import com.avances.applima.domain.model.Interest;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.model.Route;
import com.avances.applima.domain.model.SuggestedTag;
import com.avances.applima.domain.model.UserPreference;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.presentation.presenter.CountryPresenter;
import com.avances.applima.presentation.presenter.DistritNeighborhoodPresenter;
import com.avances.applima.presentation.presenter.EventPresenter;
import com.avances.applima.presentation.presenter.GenderPresenter;
import com.avances.applima.presentation.presenter.InterestPresenter;
import com.avances.applima.presentation.presenter.PlacePresenter;
import com.avances.applima.presentation.presenter.RoutePresenter;
import com.avances.applima.presentation.presenter.SuggestedTagPresenter;
import com.avances.applima.presentation.presenter.SynchronizationPresenter;
import com.avances.applima.presentation.presenter.UsuarioPresenter;
import com.avances.applima.presentation.ui.dialogfragment.NoInternetDialog;
import com.avances.applima.presentation.utils.AppKilledService;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.utils.MyFirebaseMessagingService;
import com.avances.applima.presentation.utils.TextureVideoView;
import com.avances.applima.presentation.view.CountryView;
import com.avances.applima.presentation.view.DistritNeighborhoodView;
import com.avances.applima.presentation.view.EventView;
import com.avances.applima.presentation.view.GenderView;
import com.avances.applima.presentation.view.InterestView;
import com.avances.applima.presentation.view.PlaceView;
import com.avances.applima.presentation.view.RouteView;
import com.avances.applima.presentation.view.SuggestedTagView;
import com.avances.applima.presentation.view.SynchronizationView;
import com.avances.applima.presentation.view.UsuarioView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.rpc.Help;

import java.util.ArrayList;
import java.util.List;

public class Splash extends BaseActivity
        implements SynchronizationView, PlaceView, DistritNeighborhoodView, InterestView,
        RouteView, EventView, UsuarioView, CountryView, GenderView, SuggestedTagView, MyFirebaseMessagingService.GoSplash {

    SynchronizationPresenter synchronizationPresenter;
    PlacePresenter placePresenter;
    DistritNeighborhoodPresenter distritNeighborhoodPresenter;
    InterestPresenter interestPresenter;
    RoutePresenter routePresenter;
    EventPresenter eventPresenter;
    private FirebaseAnalytics mFirebaseAnalytics;
    boolean firstSyncSuccess;
    VideoView videoHolder;
    Button btnEmpezar;
    UserPreference userPreference;
    LinearLayout lltextSplash;
    UsuarioPresenter usuarioPresenter;
    CountryPresenter countryPresenter;
    GenderPresenter genderPresenter;
    TextureVideoView vvVideo;
    SuggestedTagPresenter suggestedTagPresenter;

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(com.avances.applima.R.layout.splash_screen);

        FirebaseApp.initializeApp(this);
        getIdTokenFCM();
        initUI();
        loadPresenter();
        clickEvents();
        checkSync();

        //   getPlacesDb();
    }


    void getIdTokenFCM()
    {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(Splash.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String token = instanceIdResult.getToken();

                UserPreference userPreference= Helper.getUserAppPreference(getContext());
                userPreference.setTokenFCM(token);
                Helper.saveUserAppPreference(getContext(),userPreference);
            }
        });
    }


    void registerTemporalUser(String token,String idTokenFCM)
    {
        usuarioPresenter.registerTemporalUser(token,idTokenFCM);
    }

    void showNoInternetDialog() {
        NoInternetDialog df = new NoInternetDialog();
        df.show(getSupportFragmentManager(), "NoInternetDialog");
    }

    void checkSync() {
        if (userPreference.isFirstSyncSuccess()) {
            next(MainActivity.class, null);
        } else {
            playVideo();
            generateToken();
        }
    }


    void generateToken()
    {
        usuarioPresenter.generateToken();
    }

    void initUI() {
        firstSyncSuccess = false;
       // videoHolder = (VideoView) findViewById(R.id.videoView);
        btnEmpezar = (Button) findViewById(R.id.btnEmpezar);
        userPreference = Helper.getUserAppPreference(getContext());
        lltextSplash=(LinearLayout) findViewById(R.id.lltextSplash);

        lltextSplash.setVisibility(View.VISIBLE);
        final Animation animTranslate = AnimationUtils.loadAnimation(getContext(), R.anim.anim_scale_text_splash);
        lltextSplash.startAnimation(animTranslate);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(lltextSplash, "alpha",0, 1);
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(0);
        objectAnimator.start();

        vvVideo= (TextureVideoView) findViewById(R.id.videoView);
        vvVideo.setScaleType(TextureVideoView.ScaleType.TOP);

        startService(new Intent(getBaseContext(), AppKilledService.class));
    }


    void playVideo() {
        try {
            // VideoView videoHolder = new VideoView(this);
            // setContentView(videoHolder);
            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videolima);
            vvVideo.setDataSource(getContext(),video);

            vvVideo.setListener(new TextureVideoView.MediaPlayerListener() {
                @Override
                public void onVideoPrepared() {

                }

                @Override
                public void onVideoEnd() {

                    playVideo();
                }
            });
            vvVideo.play();

        } catch (Exception ex) {
            playVideo();
        }



    }


    void sync() {

        if (isConnectedToInternet(getContext())) {
            synchronizationPresenter.syncAll(Helper.getUserAppPreference(getContext()).getToken(),Constants.STORE.CLOUD);
        } else {
            showNoInternetDialog();
        }
    }

    void getPlacesDb() {
        placePresenter.getPlaces(Constants.STORE.DB);
    }

    void loadPresenter() {
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

        usuarioPresenter= new UsuarioPresenter();
        usuarioPresenter.addView(this);

        countryPresenter= new CountryPresenter();
        countryPresenter.addView(this);

        genderPresenter= new GenderPresenter();
        genderPresenter.addView(this);

        suggestedTagPresenter= new SuggestedTagPresenter();
        suggestedTagPresenter.addView(this);
    }

    void analitica() {

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "PruebaName");
        // bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "");
        //  bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        next(MainActivity.class, null);
        finish();
        // analitica();
    }


    void clickEvents() {
        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vvVideo.stop();
                next(MainActivity.class, null);
               // finish();
            }
        });
    }

    @Override
    public void syncSuccesfull(WsSynchronization wsSynchronization) {

        WsData wsData=wsSynchronization.getWsData();

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

        CountryDataMapper countryDataMapper= new CountryDataMapper();
        ArrayList<DbCountry> dbCountries=countryDataMapper.transformWsToDb(wsData);
        countryPresenter.createCountry(dbCountries,Constants.STORE.DB);

        GenderDataMapper genderDataMapper= new GenderDataMapper();
        ArrayList<DbGender> dbGenders=genderDataMapper.transformWsToDb(wsData);
        genderPresenter.createGender(dbGenders,Constants.STORE.DB);

        SuggestedTagDataMapper suggestedTagDataMapper= new SuggestedTagDataMapper();
        ArrayList<DbSuggestedTag> dbSuggestedTags=suggestedTagDataMapper.transformWsToDb(wsData);
        suggestedTagPresenter.createSuggestedTag(dbSuggestedTags,Constants.STORE.DB);

        userPreference = Helper.getUserAppPreference(getContext());
        userPreference.setFirstSyncSuccess(true);
        Helper.saveUserAppPreference(getContext(), userPreference);


        registerTemporalUser(Helper.getUserAppPreference(getContext()).getToken(),Helper.getUserAppPreference(getContext()).getTokenFCM());

      //  btnEmpezar.setVisibility(View.VISIBLE);
    }


    @Override
    public void distritNeighborhoodListLoaded(List<DistritNeighborhood> dbDistritNeighborhoods) {

    }

    @Override
    public void distritCreated(String message) {

    }

    @Override
    public void distritUpdated(String message) {

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
    public void interestListLoaded(List<Interest> dbInterests) {

    }

    @Override
    public void interestCreated(String message) {

    }

    @Override
    public void interestUpdated(String message) {

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
    public void eventListLoaded(List<Event> events) {

    }

    @Override
    public void eventCreated(String message) {

    }

    @Override
    public void eventUpdated(String message) {

    }

    @Override
    public void temporalUserRegistered(String idTempUser) {
        String idTemp=idTempUser;
        UserPreference userPreference= Helper.getUserAppPreference(getContext());
        userPreference.setIdTemporal(idTemp);
        Helper.saveUserAppPreference(getContext(),userPreference);

        btnEmpezar.setVisibility(View.VISIBLE);
    }

    @Override
    public void tokenGenerated(String token) {

        UserPreference userPreference=Helper.getUserAppPreference(getContext());
        userPreference.setToken(token);
        Helper.saveUserAppPreference(getContext(),userPreference);

        sync();
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
    public void validateCodeSuccess(Usuario message) {

    }

    @Override
    public void routesByInterestSuccess(List<String> idRoutes) {

    }

    @Override
    public void userUpdated(Usuario usuario) {

    }

    @Override
    public void countryListLoaded(List<Country> countries) {

    }

    @Override
    public void countryCreated(String message) {

    }

    @Override
    public void genderListLoaded(List<Gender> genders) {

    }

    @Override
    public void genderCreated(String message) {

    }

    @Override
    public void suggestedTagListLoaded(List<SuggestedTag> suggestedTags) {

    }

    @Override
    public void suggestedTagCreated(String message) {

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

    @Override
    public void onClose(String token) {

       // sync();
        UserPreference userPreference=Helper.getUserAppPreference(getContext());
        userPreference.setTokenFCM(token);
        Helper.saveUserAppPreference(getApplicationContext(),userPreference);

    }
}