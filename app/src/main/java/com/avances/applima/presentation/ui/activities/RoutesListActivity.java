package com.avances.applima.presentation.ui.activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.model.Route;
import com.avances.applima.presentation.presenter.PlacePresenter;
import com.avances.applima.presentation.ui.adapters.RoutePlacesVerticalListDataAdapter;
import com.avances.applima.presentation.ui.dialogfragment.InfografiaDialog;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.PlaceView;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;

import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class RoutesListActivity extends BaseActivity implements RoutePlacesVerticalListDataAdapter.OnRutasPlacesVerticalClickListener, PlaceView {

    ImageView btnBack;

    private MapView mapView;
    private MapboxMap mapboxMap;

    // Variables needed to handle location permissions
    private PermissionsManager permissionsManager;
    // Variables needed to add the location engine
    private LocationEngine locationEngine;
    private long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;

    LatLng actualPosition;

    private static final String GEOJSON_SOURCE_ID = "GEOJSON_SOURCE_ID";
    private static final String MARKER_IMAGE_ID = "MARKER_IMAGE_ID";
    private static final String MARKER_LAYER_ID = "MARKER_LAYER_ID";
    private static final String CALLOUT_LAYER_ID = "CALLOUT_LAYER_ID";
    private static final String PROPERTY_SELECTED = "selected";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_CAPITAL = "capital";

    private GeoJsonSource source;
    private FeatureCollection featureCollection;

 //   private MainActivityLocationCallback callback = new MainActivityLocationCallback(this);

    private RoutePlacesVerticalListDataAdapter.OnRutasPlacesVerticalClickListener mlistener;

    List<Feature> symbolLayerIconFeatureList;

    RecyclerView rvLugares;
    ArrayList<DbPlace> dbPlaces;

    private OfflineManager offlineManager;

    // JSON encoding/decoding
    public static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";

    private boolean isEndNotified;
    private ProgressBar progressBar;

    GeoJsonSource geoJsonSource;


    PlacePresenter placePresenter;

    ImageView ivGoToMap,ivBack,ivinfografia;
    Route route;
    TextView tvRouteName;

    List<Place> miPlaces;


    void loadPresenter()
    {
        placePresenter= new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.route_list_activity);


        Bundle bundle= getIntent().getBundleExtra("extra");

        route=(Route)bundle.getSerializable("route");

        initUI();

        clickEvents();

        loadPresenter();


    }


    private void initUI() {

        mlistener=this;
        rvLugares = (RecyclerView) findViewById(R.id.rv_lugaress);
        ivGoToMap=(ImageView)findViewById(R.id.ivGoToMap);
        ivBack=(ImageView)findViewById(R.id.ivBack);
        ivinfografia=(ImageView)findViewById(R.id.ivinfografia);
        tvRouteName=(TextView)findViewById(R.id.tvRouteName);

        tvRouteName.setText(route.getRouteName());
    }


    void clickEvents()
    {

        ivGoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle= new Bundle();
                bundle.putSerializable("route",route);
                next(RoutesMapActivity.class,bundle);

            }
        });


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  next(MainActivity.class,null);
                next(MainActivity.class,null);

            }
        });

        ivinfografia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("route", route);

                InfografiaDialog df = new InfografiaDialog();
                 df.setArguments(bundle);
                df.show(getSupportFragmentManager(), "InfografiaDialog");

            }
        });

    }







    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();

    }



    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }


    @Override
    public void onRutasPlacesVerticalClicked(View v, Integer position) {
        Place place = miPlaces.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", place);
        bundle.putBoolean("fromDistrit",false);
        // loadPlaceDetailFragment(bundle);
        next(PlaceDetailActivity.class,bundle);
    }

    @Override
    public void placeListLoaded(List<Place> places) {


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

         miPlaces= new ArrayList<>();


        for(Place place:places)
        {
            for(String idPlace:route.getIdPlaceList())
            {
                if(place.getId().equals(idPlace))
                {
                    miPlaces.add(place);
                }
            }
        }

        int[] ATTRS = new int[]{android.R.attr.listDivider};

        TypedArray a = getApplicationContext().obtainStyledAttributes(ATTRS);
        Drawable divider = a.getDrawable(0);
        int inset = getResources().getDimensionPixelSize(R.dimen.list_vertical_margin);
        InsetDrawable insetDivider = new InsetDrawable(divider, inset, 0, inset, 0);
        a.recycle();

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(insetDivider);
        rvLugares.addItemDecoration(itemDecoration);

        RoutePlacesVerticalListDataAdapter routesHorizontalDataAdapter = new RoutePlacesVerticalListDataAdapter(mlistener,getApplicationContext(), miPlaces,userHasLocation);

        rvLugares.setHasFixedSize(true);
        rvLugares.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rvLugares.setAdapter(routesHorizontalDataAdapter);
    }

    @Override
    public void placeCreated(String message) {

    }

    @Override
    public void placeUpdated(String message) {

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
}
