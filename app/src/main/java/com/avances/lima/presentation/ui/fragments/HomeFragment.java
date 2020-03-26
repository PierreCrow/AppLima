package com.avances.lima.presentation.ui.fragments;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.avances.lima.domain.model.FilterTag;
import com.avances.lima.domain.model.Gender;
import com.avances.lima.domain.model.Interest;
import com.avances.lima.domain.model.PermanencyDay;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.model.Route;
import com.avances.lima.domain.model.SuggestedTag;
import com.avances.lima.domain.model.UserPreference;
import com.avances.lima.domain.model.Usuario;
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
import com.avances.lima.presentation.ui.activities.MainActivity;
import com.avances.lima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.lima.presentation.ui.activities.RoutesMapActivity;
import com.avances.lima.presentation.ui.adapters.DistritHorizontalListDataAdapter;
import com.avances.lima.presentation.ui.adapters.PlacesHorizontalListDataAdapter;
import com.avances.lima.presentation.ui.adapters.RoutesHorizontalListDataAdapter;
import com.avances.lima.presentation.ui.adapters.TagHorizontalListDataAdapter;
import com.avances.lima.presentation.ui.dialogfragment.FilterDialog;
import com.avances.lima.presentation.ui.dialogfragment.NewSyncDialog;
import com.avances.lima.presentation.ui.dialogfragment.NewVersionDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.utils.TransparentProgressDialog;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements
        PlaceView, RouteView, DistritNeighborhoodView, InterestView,
        DistritHorizontalListDataAdapter.OnDistritHorizontalClickListener,
        RoutesHorizontalListDataAdapter.OnRutasTematicasHorizontalClickListener,
        PlacesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener,
        TagHorizontalListDataAdapter.OnTagClickListener,
        UsuarioView, SynchronizationView,
         EventView, CountryView, GenderView, SuggestedTagView, PermanencyDayView,NewSyncDialog.CloseNewSync {

    SingleClick singleClick;
    @BindView(R.id.btnSedarch)
    ImageView ivFilter;
    @BindView(R.id.btnMoreImperdibles)
    TextView btnMoreImperdibles;
    @BindView(R.id.btnMoreTematicas)
    TextView btnMoreTematicas;
    @BindView(R.id.editTextSearchCode)
    TextView etBuscador;

    public static RecyclerView rvDistritos, rvLugares, rvTags, rvMejoresRutas;
    public static List<DistritNeighborhood> distritNeighborhoods;
    Context mContext;
    public static List<Place> places;
    public static List<Route> routes;
    public static List<FilterTag> tags = new ArrayList<>();
    PlacePresenter placePresenter;
    RoutePresenter routePresenter;
    DistritNeighborhoodPresenter distritNeighborhoodPresenter;
    InterestPresenter interestPresenter;
    UsuarioPresenter usuarioPresenter;

    public static DistritHorizontalListDataAdapter.OnDistritHorizontalClickListener mlistenerDistritHorizontal;
    public static RoutesHorizontalListDataAdapter.OnRutasTematicasHorizontalClickListener mlistenerRutasTematicasHorizontal;
    public static PlacesHorizontalListDataAdapter.OnImperdiblesHorizontalClickListener mlistenerImperdiblesHorizontal;
    public static TagHorizontalListDataAdapter.OnTagClickListener mlistenerTag;
    public static List<DistritNeighborhood> distritNeighborhoodsFilter = new ArrayList<>();
    public static List<Place> placesFilter = new ArrayList<>();
    public static List<Route> routesFilter = new ArrayList<>();
    public static boolean fromTagFilter = false;
    public static boolean fromSearch = false;
    View x;


    SynchronizationPresenter synchronizationPresenter;
    EventPresenter eventPresenter;
    CountryPresenter countryPresenter;
    GenderPresenter genderPresenter;
    PermanencyDayPresenter permanencyDayPresenter;
    SuggestedTagPresenter suggestedTagPresenter;
    TransparentProgressDialog loading;

    public static boolean pasoPorAca=false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        x = inflater.inflate(R.layout.home_fragment, null);
        injectView(x);
        initUI(x);
        verifitySync();
        loadPresenter();
        validateComeWithDistritDetailPlaceDetail();
        return x;
    }

    void timerSync(Integer seconds) {

            long secondsMill = seconds * 10;

            if(loading==null)
            {
           //     loading= new TransparentProgressDialog(getA());
            }
            if(!loading.isShowing())
            {
                loading.show();
            }

            new CountDownTimer(10000, secondsMill) {

                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {

                    if (loading.isShowing()) {
                        loading.dismiss();
                    }

                    next(MainActivity.class,getContext(),null);
                }

            }.start();


    }

    void verifitySync()
    {
        UserPreference userPreference = Helper.getUserAppPreference(getContext());
        String lastDateSync = userPreference.getLastDateSynchronization();
        String token=userPreference.getToken();

        synchronizationPresenter = new SynchronizationPresenter();
        synchronizationPresenter.addView(this);
        synchronizationPresenter.verifySync(token,lastDateSync);
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
        userPreference.setLastDateSynchronization(lastDateSync);
        Helper.saveUserAppPreference(getContext(), userPreference);


        NewSyncDialog df = new NewSyncDialog();
        df.setCancelable(false);
        df.show(getFragmentManager(), "TermsConditionsDialog");

    }


    void loadPresenter() {

        if (!fromTagFilter) {
            placePresenter = new PlacePresenter();
            placePresenter.addView(this);
            placePresenter.getPlaces(Constants.STORE.DB);

            routePresenter = new RoutePresenter();
            routePresenter.addView(this);
            routePresenter.getRoutes(Constants.STORE.DB);

            interestPresenter = new InterestPresenter();
            interestPresenter.addView(this);
            interestPresenter.getInterests(Constants.STORE.DB);

            distritNeighborhoodPresenter = new DistritNeighborhoodPresenter();
            distritNeighborhoodPresenter.addView(this);
            distritNeighborhoodPresenter.getDistritNeighborhoods(Constants.STORE.DB);

            usuarioPresenter = new UsuarioPresenter();
            usuarioPresenter.addView(this);
            usuarioPresenter.versionApp(Helper.getUserAppPreference(getContext()).getToken());
        }
    }


    private void initUI(View v) {


        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {
                switch (v.getId()) {
                    case R.id.btnMoreImperdibles:
                        sendCallbackImperdibles();
                        break;
                    case R.id.btnMoreTematicas:
                        sendCallBackRutasTematicas();
                        break;
                    case R.id.btnSedarch:
                        loadFilterHomeFragment();
                        break;
                    case R.id.editTextSearchCode:
                        sendCallbackBuscador();
                        break;
                }
            }
        };

        loading= new TransparentProgressDialog(getContext());

        btnMoreImperdibles.setOnClickListener(singleClick);
        btnMoreTematicas.setOnClickListener(singleClick);
        ivFilter.setOnClickListener(singleClick);
        etBuscador.setOnClickListener(singleClick);

        rvDistritos = (RecyclerView) v.findViewById(R.id.rv_distritos);
        rvLugares = (RecyclerView) v.findViewById(R.id.rv_lugares);
        rvTags = (RecyclerView) v.findViewById(R.id.rvTags);
        rvMejoresRutas = (RecyclerView) v.findViewById(R.id.rv_mejoresrutas);

        mContext = getContext();

        setEditTextSize(etBuscador);

        ivFilter.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivFilter, "alpha", 0, 1);
        objectAnimator.setDuration(1000);
        objectAnimator.setStartDelay(0);
        objectAnimator.start();

        mlistenerDistritHorizontal = this;
        mlistenerRutasTematicasHorizontal = this;
        mlistenerImperdiblesHorizontal = this;
        mlistenerTag = this;

        if (tags != null) {
            if (tags.size() == 0) {
                rvTags.setVisibility(View.GONE);
                cargaListasDEFAULT();
            } else {
                addTagsPrueba(fromSearch);
            }
        }
    }


    void cargaListasDEFAULT() {

        boolean userHasLocation;
        if (Helper.getUserAppPreference(mContext).isHasLocation()) {
            if (Helper.gpsIsEnabled(mContext)) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }

        DistritHorizontalListDataAdapter distritHorizontalListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, mContext, distritNeighborhoods);
        rvDistritos.setHasFixedSize(true);
        rvDistritos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvDistritos.setAdapter(distritHorizontalListDataAdapter);

        RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, mContext, routes);
        rvMejoresRutas.setHasFixedSize(true);
        rvMejoresRutas.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);

        PlacesHorizontalListDataAdapter placesHorizontalListDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, mContext, places, userHasLocation);
        rvLugares.setHasFixedSize(true);
        rvLugares.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        rvLugares.setAdapter(placesHorizontalListDataAdapter);
    }

    @Override
    public void placeListLoaded(List<Place> places) {

        this.places = places;
        boolean userHasLocation;
        if (Helper.getUserAppPreference(getContext()).isHasLocation()) {
            if (Helper.gpsIsEnabled(getContext())) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }
        PlacesHorizontalListDataAdapter routesHorizontalDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, getContext(), places, userHasLocation);
        rvLugares.setHasFixedSize(true);
        rvLugares.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvLugares.setAdapter(routesHorizontalDataAdapter);
    }

    @Override
    public void placeCreated(String message) {
    }

    @Override
    public void placeUpdated(String message) {

    }

    @Override
    public void distritNeighborhoodListLoaded(List<DistritNeighborhood> distritNeighborhoods) {

        this.distritNeighborhoods = distritNeighborhoods;
        DistritHorizontalListDataAdapter itemListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, getContext(), distritNeighborhoods);

        rvDistritos.setHasFixedSize(true);
        rvDistritos.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvDistritos.setAdapter(itemListDataAdapter);
    }

    @Override
    public void distritCreated(String message) {

    }

    @Override
    public void distritUpdated(String message) {

    }

    @Override
    public void routeListLoaded(List<Route> routes) {
        this.routes = routes;
        RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, getContext(), routes);

        rvMejoresRutas.setHasFixedSize(true);
        rvMejoresRutas.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);
    }

    @Override
    public void routeCreated(String message) {

    }

    @Override
    public void routeUpdated(String message) {

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

        goVerifyVersion(getContext(), version, "");


    }

    @Override
    public void imageUploaded(String message) {

    }

    public void goVerifyVersion(final Context context, String minimumVersion, final String url) {

        String myVersion = "";
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String appVersion = "";
            appVersion = pInfo.versionName;
            myVersion = appVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (minimumVersion != null) {
            if (!myVersion.equals("") && !minimumVersion.equals("")) {
                int response = Helper.compareVersions(myVersion, minimumVersion);
                if (response == Integer.parseInt(Constants.APP_VERSION.MINOR)) {
                    UserPreference userPreference = Helper.getUserAppPreference(context);
                    userPreference.setLastVersion(false);
                    Helper.saveUserAppPreference(context, userPreference);


                    NewVersionDialog df = new NewVersionDialog();
                    df.show(getFragmentManager(), "ClientDetail");

                    Toast.makeText(getContext(), "Hay una nueva version del app", Toast.LENGTH_LONG).show();

                }
                if (response == Integer.parseInt(Constants.APP_VERSION.EQUAL) || response == Integer.parseInt(Constants.APP_VERSION.MAJOR)) {
                    UserPreference userPreference = Helper.getUserAppPreference(context);
                    userPreference.setLastVersion(true);
                    Helper.saveUserAppPreference(context, userPreference);
                    //   saveVersionUpdated(context, true);
                }
            }
        }
    }

    @Override
    public void syncSuccesfull(WsSynchronization wsSynchronization) {

        syncall(wsSynchronization);

    }

    @Override
    public void verifiedSync(boolean sync) {

        if(sync)
        {
            deleteSQLITE();

            UserPreference userPreference = Helper.getUserAppPreference(getContext());
            String lastDateSync = userPreference.getLastDateSynchronization();

            if (synchronizationPresenter != null) {
                synchronizationPresenter.syncAll(Helper.getUserAppPreference(getContext()).getToken(), Constants.STORE.CLOUD);
            }

            pasoPorAca=true;
        }

    }

    private void deleteSQLITE() {


        AppLimaDb appLimaDb=AppLimaDb.getDatabase(getContext());
        appLimaDb.clearAllTables();

    }

    @Override
    public void countryListLoaded(List<Country> countries) {

    }

    @Override
    public void countryCreated(String message) {

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
    public void permanencyDayListLoaded(List<PermanencyDay> permanencyDays) {

    }

    @Override
    public void permanencyDayCreated(String message) {

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
    public void onDistritHorizontalClicked(View v, Integer position) {

        DistritNeighborhood distritNeighborhood = distritNeighborhoods.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("distritNeighborhood", distritNeighborhood);
        loadDistritDetailFragment(bundle);

    }


    void loadDistritDetailFragment(Bundle bundle) {

        FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DistritDetailFragment accountFragment = new DistritDetailFragment();
        accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }


    void loadPlaceDetailFragment(Bundle bundle) {

        next(PlaceDetailActivity.class, getContext(), bundle);
    }

    @Override
    public void onRutasTematicasHorizontalClicked(View v, Integer position) {
        Route route = routes.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("route", route);
        //  next(RoutesListActivity.class, getContext(), bundle);
        next(RoutesMapActivity.class, getContext(), bundle);

    }

    @Override
    public void onImperdiblesHorizontalClicked(View v, Integer position) {

        Place place = places.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", place);
        loadPlaceDetailFragment(bundle);
    }

    @Override
    public void onTagClicked(View v, String tag) {

        if (tags != null) {
            for (int i = 0; i < tags.size(); i++) {
                if (tag.equals(tags.get(i).getName())) {
                    //    tags.get(i).setShowed(false);
                    //tags.get(i).setShowed(false);//.remove(i);
                    tags.remove(i);
                }
            }
        }


        if (tags != null) {
            if (tags.size() == 0) {
                rvTags.setVisibility(View.GONE);
                fromTagFilter = false;
                loadPresenter();
            } else {
                addTagsPrueba(fromSearch);
            }
        }

    }

    @Override
    public void onCloseNewSync() {

        timerSync(4);
    }


    public interface GoToImperdiblesFragment {
        public void goToImperdibles();
    }

    public interface GoToRutasTematicasFragment {
        public void goToRutasTematicas();
    }

    public interface GoToBuscador {
        public void goToBuscador();
    }


    void sendCallbackImperdibles() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToImperdiblesFragment) {
            ((GoToImperdiblesFragment) ahhh).goToImperdibles();
        }

    }


    void sendCallBackRutasTematicas() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToRutasTematicasFragment) {
            ((GoToRutasTematicasFragment) ahhh).goToRutasTematicas();
        }
    }


    void sendCallbackBuscador() {
        Activity ahhh = getActivity();

        if (ahhh instanceof GoToBuscador) {
            ((GoToBuscador) ahhh).goToBuscador();
        }

    }


    void validateComeWithDistritDetailPlaceDetail() {
        SharedPreferences preferences = getContext().getSharedPreferences("PlaceDistritView", Context.MODE_PRIVATE);
        boolean fromDistritDetail = preferences.getBoolean("FromDistritDetail", false);
        if (fromDistritDetail) {
            loadDistritDetailFragment(null);
        }
        // String idPlace=preferences.getString("idPlace",place.getId());
    }


    void setEditTextSize(TextView et) {
        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) et.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        ;
        params.width = getResources().getDimensionPixelSize(R.dimen.edit_text_search_home_width);
        et.setLayoutParams(params);
    }

    void addTagsPrueba(boolean fromSearch) {

        //si entra aca tags siempre tiene por lo menos 1 registro


        fromTagFilter = true;

        rvTags.setVisibility(View.VISIBLE);

        boolean userHasLocation;

        if (Helper.getUserAppPreference(mContext).isHasLocation()) {
            if (Helper.gpsIsEnabled(mContext)) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }


        TagHorizontalListDataAdapter itemListDataAdapter = new TagHorizontalListDataAdapter(getContext(), tags, mlistenerTag);

        rvTags.setHasFixedSize(true);
        rvTags.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvTags.setAdapter(itemListDataAdapter);


        if (fromSearch) {

            distritNeighborhoodsFilter = new ArrayList<>();

            for (DistritNeighborhood distritNeighborhood : distritNeighborhoods) {

                String longTags = distritNeighborhood.getTags();
                for (FilterTag tagInserted : tags) {
                    if (!tagInserted.getShowed()) {
                        if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {
                            distritNeighborhoodsFilter.add(distritNeighborhood);

                        }
                    } else {
                        if (tagInserted.getAdded()) {
                            if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {
                                boolean existe = false;
                                for (int i = 0; i < distritNeighborhoodsFilter.size(); i++) {
                                    if (distritNeighborhood.getIdCloud().equals(distritNeighborhoodsFilter.get(i).getIdCloud())) {
                                        existe = true;
                                    }
                                }
                                if (!existe) {
                                    distritNeighborhoodsFilter.add(distritNeighborhood);
                                }
                            }
                        }
                    }
                }
            }


            routesFilter = new ArrayList<>();

            for (Route route : routes) {

                String longTags = route.getTags();
                for (FilterTag tagInserted : tags) {
                    if (!tagInserted.getShowed()) {
                        if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {
                            routesFilter.add(route);
                        }
                    } else {
                        if (tagInserted.getAdded()) {
                            if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {
                                boolean existe = false;
                                for (int i = 0; i < routesFilter.size(); i++) {
                                    if (route.getId().equals(routesFilter.get(i).getId())) {
                                        existe = true;
                                    }
                                }
                                if (!existe) {
                                    routesFilter.add(route);
                                }
                            }
                        }
                    }
                }
            }


            placesFilter = new ArrayList<>();

            int i = 0;

            for (Place place : places) {

                String longTags = place.getTextTags();
                for (i = 0; i < tags.size(); i++) {
                    if (!tags.get(i).getShowed()) {
                        if (longTags.toLowerCase().contains(tags.get(i).getName().toLowerCase())) {
                            placesFilter.add(place);
                        }
                    } else {
                        if (tags.get(i).getAdded()) {
                            if (longTags.toLowerCase().contains(tags.get(i).getName().toLowerCase())) {
                                boolean existe = false;
                                for (int j = 0; j < placesFilter.size(); j++) {
                                    if (place.getId().equals(placesFilter.get(j).getId())) {
                                        existe = true;
                                    }
                                }
                                if (!existe) {
                                    placesFilter.add(place);
                                }
                            }
                        }
                    }

                }


            }

            if (tags.size() != 0) {
                tags.get(i - 1).setShowed(true);

                DistritHorizontalListDataAdapter distritHorizontalListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, mContext, distritNeighborhoodsFilter);

                rvDistritos.setHasFixedSize(true);
                rvDistritos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvDistritos.setAdapter(distritHorizontalListDataAdapter);


                RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, mContext, routesFilter);

                rvMejoresRutas.setHasFixedSize(true);
                rvMejoresRutas.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);


                PlacesHorizontalListDataAdapter placesHorizontalListDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, mContext, placesFilter, userHasLocation);

                rvLugares.setHasFixedSize(true);
                rvLugares.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvLugares.setAdapter(placesHorizontalListDataAdapter);

            } else {

                DistritHorizontalListDataAdapter distritHorizontalListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, mContext, distritNeighborhoods);

                rvDistritos.setHasFixedSize(true);
                rvDistritos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvDistritos.setAdapter(distritHorizontalListDataAdapter);

                RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, mContext, routes);

                rvMejoresRutas.setHasFixedSize(true);
                rvMejoresRutas.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);

                PlacesHorizontalListDataAdapter placesHorizontalListDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, mContext, places, userHasLocation);

                rvLugares.setHasFixedSize(true);
                rvLugares.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvLugares.setAdapter(placesHorizontalListDataAdapter);

            }
        } else {

            if (distritNeighborhoodsFilter.size() == 0) {
                distritNeighborhoodsFilter = distritNeighborhoods;
            }


            List<DistritNeighborhood> mDistrits = new ArrayList<>();
            for (DistritNeighborhood distritNeighborhood : distritNeighborhoodsFilter) {
                String longTags = distritNeighborhood.getTags();
                for (FilterTag tagInserted : tags) {
                    if (!tagInserted.getShowed()) {
                        if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {
                            boolean existe = false;
                            for (int i = 0; i < mDistrits.size(); i++) {
                                if (distritNeighborhood.getIdCloud().equals(mDistrits.get(i).getIdCloud())) {
                                    existe = true;
                                }
                            }
                            if (!existe) {
                                mDistrits.add(distritNeighborhood);
                            }
                        }
                    } else {
                        if (tagInserted.getAdded()) {
                            if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {
                                boolean existe = false;
                                for (int i = 0; i < mDistrits.size(); i++) {
                                    if (distritNeighborhood.getIdCloud().equals(mDistrits.get(i).getIdCloud())) {
                                        existe = true;
                                    }
                                }
                                if (!existe) {
                                    mDistrits.add(distritNeighborhood);
                                }
                            }
                        }
                    }
                }
            }


            if (routesFilter.size() == 0) {
                routesFilter = routes;
            }

            List<Route> mRoutes = new ArrayList<>();
            for (Route route : routesFilter) {
                String longTags = route.getTags();
                for (FilterTag tagInserted : tags) {
                    if (!tagInserted.getShowed()) {
                        if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {

                            boolean existe = false;
                            for (int i = 0; i < mRoutes.size(); i++) {
                                if (route.getId().equals(mRoutes.get(i).getId())) {
                                    existe = true;
                                }
                            }
                            if (!existe) {
                                mRoutes.add(route);
                            }
                        }
                    } else {
                        if (tagInserted.getAdded()) {
                            if (longTags.toLowerCase().contains(tagInserted.getName().toLowerCase())) {

                                boolean existe = false;
                                for (int i = 0; i < mRoutes.size(); i++) {
                                    if (route.getId().equals(mRoutes.get(i).getId())) {
                                        existe = true;
                                    }
                                }
                                if (!existe) {
                                    mRoutes.add(route);
                                }
                            }
                        }
                    }

                }
            }


            if (placesFilter.size() == 0) {
                placesFilter = places;
            }

            int i = 0;


            List<Place> mPlaces = new ArrayList<>();

            for (Place place : placesFilter) {

                String longTags = place.getTextTags();
                for (i = 0; i < tags.size(); i++) {
                    if (!tags.get(i).getShowed()) {
                        if (longTags.toLowerCase().contains(tags.get(i).getName().toLowerCase())) {


                            boolean existe = false;
                            for (int j = 0; j < mPlaces.size(); j++) {
                                if (place.getId().equals(mPlaces.get(j).getId())) {
                                    existe = true;
                                }
                            }
                            if (!existe) {
                                mPlaces.add(place);
                            }

                        }
                    } else {
                        if (tags.get(i).getAdded()) {
                            if (longTags.toLowerCase().contains(tags.get(i).getName().toLowerCase())) {

                                boolean existe = false;
                                for (int j = 0; j < mPlaces.size(); j++) {
                                    if (place.getId().equals(mPlaces.get(j).getId())) {
                                        existe = true;
                                    }
                                }
                                if (!existe) {
                                    mPlaces.add(place);
                                }
                            }
                        }
                    }
                }


            }

            if (tags.size() != 0) {
                if (i > 0) {
                    tags.get(i - 1).setShowed(true);

                    if (mDistrits.size() == 0) {
                        mDistrits = distritNeighborhoodsFilter;
                    }

                    DistritHorizontalListDataAdapter distritHorizontalListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, mContext, mDistrits);

                    rvDistritos.setHasFixedSize(true);
                    rvDistritos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                    rvDistritos.setAdapter(distritHorizontalListDataAdapter);

                    if (mRoutes.size() == 0) {
                        mRoutes = routesFilter;
                    }

                    RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, mContext, mRoutes);

                    rvMejoresRutas.setHasFixedSize(true);
                    rvMejoresRutas.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                    rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);

                    if (mPlaces.size() == 0) {
                        mPlaces = placesFilter;
                    }

                    PlacesHorizontalListDataAdapter placesHorizontalListDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, mContext, mPlaces, userHasLocation);

                    rvLugares.setHasFixedSize(true);
                    rvLugares.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                    rvLugares.setAdapter(placesHorizontalListDataAdapter);
                }

            } else {

                DistritHorizontalListDataAdapter distritHorizontalListDataAdapter = new DistritHorizontalListDataAdapter(mlistenerDistritHorizontal, mContext, distritNeighborhoods);

                rvDistritos.setHasFixedSize(true);
                rvDistritos.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvDistritos.setAdapter(distritHorizontalListDataAdapter);

                RoutesHorizontalListDataAdapter routesHorizontalListDataAdapter = new RoutesHorizontalListDataAdapter(mlistenerRutasTematicasHorizontal, mContext, routes);

                rvMejoresRutas.setHasFixedSize(true);
                rvMejoresRutas.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvMejoresRutas.setAdapter(routesHorizontalListDataAdapter);


                PlacesHorizontalListDataAdapter placesHorizontalListDataAdapter = new PlacesHorizontalListDataAdapter(mlistenerImperdiblesHorizontal, mContext, places, userHasLocation);

                rvLugares.setHasFixedSize(true);
                rvLugares.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                rvLugares.setAdapter(placesHorizontalListDataAdapter);

            }
        }


    }


    void loadFilterHomeFragment() {

        FilterDialog df = new FilterDialog();
        // df.setArguments(args);
        df.show(getFragmentManager(), "ClientDetail");
    }

    @Override
    public void onPause() {
        super.onPause();

        //MainActivity.FRAGMENT_VIEWING=Constants.FRAGMENTS_TABS.HOME;
        String hola = "";
        //    sendCallback();

    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {


        }
    }


}