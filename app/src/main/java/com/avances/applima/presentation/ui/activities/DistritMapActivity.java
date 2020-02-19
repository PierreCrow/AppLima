package com.avances.applima.presentation.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.DistritNeighborhood;
import com.avances.applima.domain.model.Place;
import com.avances.applima.presentation.presenter.PlacePresenter;
import com.avances.applima.presentation.ui.adapters.RoutePlacesMapHorizontalListDataAdapter;
//import com.avances.applima.presentation.ui.fragments.PlaceDetailFragment;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.PlaceView;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.BubbleLayout;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import timber.log.Timber;

import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ANCHOR_BOTTOM;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class DistritMapActivity extends BaseActivity implements
        OnMapReadyCallback, PermissionsListener, MapboxMap.OnMapClickListener, PlaceView, RoutePlacesMapHorizontalListDataAdapter.OnPlacesMapHorizontalClickListener {

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
    private static final String PROPERTY_ID = "id";

    private GeoJsonSource source;
    private FeatureCollection featureCollection;

    private MainActivityLocationCallback callback = new MainActivityLocationCallback(this);

    List<Feature> symbolLayerIconFeatureList;

    RecyclerView rvLugares;
    ArrayList<DbPlace> dbPlaces;
    String idPlaceSelected;

    private OfflineManager offlineManager;

    // JSON encoding/decoding
    public static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";

    private boolean isEndNotified;
    private ProgressBar progressBar;

    GeoJsonSource geoJsonSource;

    ImageView ivGoToList;
    DistritNeighborhood distritNeighborhood;
    TextView tvDistritName;
    LatLng distritPosition;
    static List<Place> places;

    List<String> idPlaces;
    PlacePresenter placePresenter;

    private RoutePlacesMapHorizontalListDataAdapter.OnPlacesMapHorizontalClickListener mlistenerPlacesMapHorizontal;


    static int getPositionOfPlaceSelected(String idPlace) {
        Integer idPl = null;
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getId().equals(idPlace)) {
                idPl = i;
            }
        }
        return idPl;
    }


    void initUI(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getBundleExtra("extra");
        distritNeighborhood = (DistritNeighborhood) bundle.getSerializable("distritNeighborhood");
        distritPosition = new LatLng();
        // routePosition.setLatitude(Double.parseDouble(distritNeighborhood.get));
        idPlaces = distritNeighborhood.getIdPlaceList();
        places = new ArrayList<>();
        mlistenerPlacesMapHorizontal = this;

        rvLugares = (RecyclerView) findViewById(R.id.rv_lugaress);
        ivGoToList = (ImageView) findViewById(R.id.ivGoToList);
        tvDistritName = (TextView) findViewById(R.id.tvDistritName);
        tvDistritName.setText(distritNeighborhood.getDistrit());
        mapView = (MapView) findViewById(R.id.mapView);
        btnBack = (ImageView) findViewById(R.id.btnBack);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


    }

    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }

    void clickEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  next(MainActivity.class);
                finish();
            }
        });

        ivGoToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(RoutesListActivity.class, null);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   setContentView(R.layout.route_map_activity);

        String accesToken = "pk.eyJ1IjoiYXZhbmNlc3RlY25vbG9naWNvcyIsImEiOiJjazN1b3R1MmswM3psM3Fvd2xudDM3NmdrIn0.MkMCDtDKevC9Uq3rwfZekw";

        Mapbox.getInstance(this, accesToken);

        setContentView(R.layout.distrit_map_activity);

        initUI(savedInstanceState);
        loadPresenter();
        clickEvents();


    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {

        this.mapboxMap = mapboxMap;

   /*     if (actualPosition != null) {
            CameraPosition cameraPosition = new CameraPosition.Builder().target(actualPosition).tilt(90).zoom(15.0).//posicion actual// tilt(90).//angulo de inclinacionzoom(ZOOM_MAX).//zoom
                    build();

            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000);
            //  mapboxMap.setCameraPosition(cameraPosition);


            geoJsonSource = new GeoJsonSource("source-id",
                    Feature.fromGeometry(Point.fromLngLat(actualPosition.getLongitude(),
                            actualPosition.getLatitude())));

        } else {



        }
*/

        LatLng distritMap = new LatLng();
        distritMap.setLatitude(Double.parseDouble(distritNeighborhood.getLatitude()));
        distritMap.setLongitude(Double.parseDouble(distritNeighborhood.getLongitude()));


        CameraPosition cameraPosition = new CameraPosition.Builder().target(distritMap).tilt(90).zoom(15.0).//posicion actual// tilt(90).//angulo de inclinacionzoom(ZOOM_MAX).//zoom
                build();

        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000);

        geoJsonSource = new GeoJsonSource("source-id",
                Feature.fromGeometry(Point.fromLngLat(distritMap.getLongitude(),
                        distritMap.getLatitude())));


        mapboxMap.setStyle(Style.LIGHT,
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        //online
                        new LoadGeoJsonDataTask(DistritMapActivity.this).execute();
                        mapboxMap.addOnMapClickListener(DistritMapActivity.this);


/*
                        Bitmap mibu = getBitmapFromVectorDrawable(getApplicationContext(), R.drawable.ic_gps);
                        style.addImage(("marker_icon"),mibu);
                        style.addSource(geoJsonSource);
*/

                        //offline
                        //  offlineMap(style);


                        enableLocationComponent(style);


                    }
                });

      /*  symbolLayerIconFeatureList = new ArrayList<>();
        symbolLayerIconFeatureList.add(Feature.fromGeometry(
                Point.fromLngLat(-57.225365, -33.213144)));
        symbolLayerIconFeatureList.add(Feature.fromGeometry(
                Point.fromLngLat(-54.14164, -33.981818)));
        symbolLayerIconFeatureList.add(Feature.fromGeometry(
                Point.fromLngLat(-56.990533, -30.583266)));
*/
        //  GeoJsonSource source = mapboxMap.getSourceAs(SOURCE_ID);

    }


    void offlineMap(Style style) {

        offlineManager = OfflineManager.getInstance(DistritMapActivity.this);

        // Create a bounding box for the offline region
        LatLngBounds latLngBounds = new LatLngBounds.Builder()
                .include(new LatLng(37.7897, -119.5073)) // Northeast
                .include(new LatLng(37.6744, -119.6815)) // Southwest
                .build();

        // Define the offline region
        OfflineTilePyramidRegionDefinition definition = new OfflineTilePyramidRegionDefinition(
                style.getUri(),
                latLngBounds,
                10,
                20,
                DistritMapActivity.this.getResources().getDisplayMetrics().density);


        // Set the metadata
        byte[] metadata;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JSON_FIELD_REGION_NAME, "Yosemite National Park");
            String json = jsonObject.toString();
            metadata = json.getBytes(JSON_CHARSET);
        } catch (Exception exception) {
            Timber.e("Failed to encode metadata: %s", exception.getMessage());
            metadata = null;
        }


        // Create the region asynchronously
        if (metadata != null) {
            offlineManager.createOfflineRegion(
                    definition,
                    metadata,
                    new OfflineManager.CreateOfflineRegionCallback() {
                        @Override
                        public void onCreate(OfflineRegion offlineRegion) {
                            offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);

                            // Display the download progress bar
                            progressBar = findViewById(R.id.progress_bar);
                            startProgress();

                            // Monitor the download progress using setObserver
                            offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
                                @Override
                                public void onStatusChanged(OfflineRegionStatus status) {

                                    // Calculate the download percentage and update the progress bar
                                    double percentage = status.getRequiredResourceCount() >= 0
                                            ? (100.0 * status.getCompletedResourceCount() / status.getRequiredResourceCount()) :
                                            0.0;

                                    if (status.isComplete()) {
                                        // Download complete
                                        endProgress("simple_offline_end_progress_success");
                                    } else if (status.isRequiredResourceCountPrecise()) {
                                        // Switch to determinate state
                                        setPercentage((int) Math.round(percentage));
                                    }
                                }

                                @Override
                                public void onError(OfflineRegionError error) {
                                    // If an error occurs, print to logcat
                                    Timber.e("onError reason: %s", error.getReason());
                                    Timber.e("onError message: %s", error.getMessage());
                                }

                                @Override
                                public void mapboxTileCountLimitExceeded(long limit) {
                                    // Notify if offline region exceeds maximum tile count
                                    Timber.e("Mapbox tile count limit exceeded: %s", limit);
                                }
                            });
                        }

                        @Override
                        public void onError(String error) {
                            Timber.e("Error: %s", error);
                        }
                    });
        }
    }

    private void endProgress(final String message) {
// Don't notify more than once
        if (isEndNotified) {
            return;
        }

// Stop and hide the progress bar
        isEndNotified = true;
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);

// Show a toast
        Toast.makeText(DistritMapActivity.this, message, Toast.LENGTH_LONG).show();
    }

    // Progress bar methods
    private void startProgress() {

// Start and show the progress bar
        isEndNotified = false;
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void setPercentage(final int percentage) {
        progressBar.setIndeterminate(false);
        progressBar.setProgress(percentage);
    }

    public void setUpData(final FeatureCollection collection) {
        featureCollection = collection;
        if (mapboxMap != null) {
            mapboxMap.getStyle(style -> {
                setupSource(style);
                setUpImage(style);
                setUpMarkerLayer(style);
                setUpInfoWindowLayer(style);
            });
        }
    }


    /**
     * Adds the GeoJSON source to the map
     */
    private void setupSource(@NonNull Style loadedStyle) {
        source = new GeoJsonSource(GEOJSON_SOURCE_ID, featureCollection);
        loadedStyle.addSource(source);
    }

    /**
     * Adds the marker image to the map for use as a SymbolLayer icon
     */
    private void setUpImage(@NonNull Style loadedStyle) {

        Bitmap mibu = getBitmapFromVectorDrawable(getApplicationContext(), R.drawable.ic_gps);
        loadedStyle.addImage(MARKER_IMAGE_ID, mibu);
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    void addListaHoriozontal() {
        dbPlaces = new ArrayList<DbPlace>();
        for (int j = 0; j <= 5; j++) {
//            dbPlaces.add(new DbPlace("La noche de barranco" + j, "URL " + j));
        }

        //  RoutePlacesMapHorizontalListDataAdapter routesHorizontalDataAdapter = new RoutePlacesMapHorizontalListDataAdapter(getApplicationContext(), dbPlaces);

        rvLugares.setHasFixedSize(true);
        rvLugares.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        //  rvLugares.setAdapter(routesHorizontalDataAdapter);
    }


    /**
     * Updates the display of data on the map after the FeatureCollection has been modified
     */
    private void refreshSource() {
        if (source != null && featureCollection != null) {
            source.setGeoJson(featureCollection);
        }
    }

    /**
     * Setup a layer with maki icons, eg. west coast city.
     */
    private void setUpMarkerLayer(@NonNull Style loadedStyle) {
        loadedStyle.addLayer(new SymbolLayer(MARKER_LAYER_ID, GEOJSON_SOURCE_ID)
                .withProperties(
                        iconImage(MARKER_IMAGE_ID),
                        iconAllowOverlap(true),
                        iconOffset(new Float[]{0f, -8f})
                ));
    }

    /**
     * Setup a layer with Android SDK call-outs
     * <p>
     * name of the feature is used as key for the iconImage
     * </p>
     */
    private void setUpInfoWindowLayer(@NonNull Style loadedStyle) {
        loadedStyle.addLayer(new SymbolLayer(CALLOUT_LAYER_ID, GEOJSON_SOURCE_ID)
                .withProperties(
                        /* show image with id title based on the value of the name feature property */
                        iconImage("{name}"),

                        /* set anchor of icon to bottom-left */
                        iconAnchor(ICON_ANCHOR_BOTTOM),

                        /* all info window and marker image to appear at the same time*/
                        iconAllowOverlap(true),

                        /* offset the info window to be above the marker */
                        iconOffset(new Float[]{-2f, -28f})
                )
/* add a filter to show only when selected feature property is true */
                .withFilter(eq((get(PROPERTY_SELECTED)), literal(true))));
    }


    /**
     * This method handles click events for SymbolLayer symbols.
     * <p>
     * When a SymbolLayer icon is clicked, we moved that feature to the selected state.
     * </p>
     *
     * @param screenPoint the point on screen clicked
     */
    private boolean handleClickIcon(PointF screenPoint) {
        List<Feature> features = mapboxMap.queryRenderedFeatures(screenPoint, MARKER_LAYER_ID);
        if (!features.isEmpty()) {
            String name = features.get(0).getStringProperty(PROPERTY_NAME);
            List<Feature> featureList = featureCollection.features();
            if (featureList != null) {
                for (int i = 0; i < featureList.size(); i++) {
                    if (featureList.get(i).getStringProperty(PROPERTY_NAME).equals(name)) {
                        Integer posisicon = getPositionOfPlaceSelected(featureList.get(i).getStringProperty(PROPERTY_ID));
                        rvLugares.getLayoutManager().scrollToPosition(posisicon);
                        if (featureSelectStatus(i)) {
                            setFeatureSelectState(featureList.get(i), false);
                        } else {
                            setSelected(i);
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Selects the state of a feature
     *
     * @param feature the feature to be selected.
     */
    private void setFeatureSelectState(Feature feature, boolean selectedState) {
        if (feature.properties() != null) {
            feature.properties().addProperty(PROPERTY_SELECTED, selectedState);
            refreshSource();
        }
    }

    /**
     * Checks whether a Feature's boolean "selected" property is true or false
     *
     * @param index the specific Feature's index position in the FeatureCollection's list of Features.
     * @return true if "selected" is true. False if the boolean property is false.
     */
    private boolean featureSelectStatus(int index) {
        if (featureCollection == null) {
            return false;
        }
        return featureCollection.features().get(index).getBooleanProperty(PROPERTY_SELECTED);
    }

    /**
     * Invoked when the bitmaps have been generated from a view.
     */
    public void setImageGenResults(HashMap<String, Bitmap> imageMap) {
        if (mapboxMap != null) {
            mapboxMap.getStyle(style -> {
// calling addImages is faster as separate addImage calls for each bitmap.
                style.addImages(imageMap);
            });
        }
    }

    @Override
    public boolean onMapClick(@NonNull LatLng point) {
        return handleClickIcon(mapboxMap.getProjection().toScreenLocation(point));
    }

    @Override
    public void placeListLoaded(List<Place> mPlaces) {

        for (Place place : mPlaces) {
            for (String idPlace : idPlaces) {
                if (idPlace.equals(place.getId())) {
                    places.add(place);
                }
            }
        }

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

        RoutePlacesMapHorizontalListDataAdapter routesHorizontalDataAdapter = new RoutePlacesMapHorizontalListDataAdapter(mlistenerPlacesMapHorizontal, getApplicationContext(), places, userHasLocation);

        rvLugares.setHasFixedSize(true);
        rvLugares.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
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

    @Override
    public void onPlacesMapHorizontalClicked(View v, Integer position) {

        Place place = places.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", place);
        bundle.putBoolean("fromDistrit", false);
        // loadPlaceDetailFragment(bundle);
        next(PlaceDetailActivity.class, bundle);
    }
/*
    void loadPlaceDetailFragment(Bundle bundle) {

        FragmentManager fragmentManager = ((AppCompatActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PlaceDetailFragment accountFragment = new PlaceDetailFragment();
        accountFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }
*/

    /**
     * Utility class to generate Bitmaps for Symbol.
     */
    private static class SymbolGenerator {

        /**
         * Generate a Bitmap from an Android SDK View.
         *
         * @param view the View to be drawn to a Bitmap
         * @return the generated bitmap
         */
        static Bitmap generate(@NonNull View view) {
            int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(measureSpec, measureSpec);

            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();

            view.layout(0, 0, measuredWidth, measuredHeight);
            Bitmap bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(Color.TRANSPARENT);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            return bitmap;
        }
    }


    private static class GenerateViewIconTask extends AsyncTask<FeatureCollection, Void, HashMap<String, Bitmap>> {

        private final HashMap<String, View> viewMap = new HashMap<>();
        private final WeakReference<DistritMapActivity> activityRef;
        private final boolean refreshSource;

        GenerateViewIconTask(DistritMapActivity activity, boolean refreshSource) {
            this.activityRef = new WeakReference<>(activity);
            this.refreshSource = refreshSource;
        }

        GenerateViewIconTask(DistritMapActivity activity) {
            this(activity, false);
        }

        @SuppressWarnings("WrongThread")
        @Override
        protected HashMap<String, Bitmap> doInBackground(FeatureCollection... params) {
            DistritMapActivity activity = activityRef.get();
            if (activity != null) {
                HashMap<String, Bitmap> imagesMap = new HashMap<>();
                LayoutInflater inflater = LayoutInflater.from(activity);

                FeatureCollection featureCollection = params[0];

                for (Feature feature : featureCollection.features()) {

                    BubbleLayout bubbleLayout = (BubbleLayout)
                            inflater.inflate(R.layout.symbolllllloo, null);

                    String name = feature.getStringProperty(PROPERTY_NAME);
                    TextView titleTextView = bubbleLayout.findViewById(R.id.info_window_title);
                    TextView idTextView = bubbleLayout.findViewById(R.id.info_window_id);
                    titleTextView.setText(name);
                    idTextView.setText(feature.getStringProperty(PROPERTY_ID));

                    activity.idPlaceSelected = feature.getStringProperty(PROPERTY_ID);

                    //  activity.rvLugares.getLayoutManager().scrollToPosition(getPositionOfPlaceSelected(activity.idPlaceSelected));


                    // TextView descriptionTextView = bubbleLayout.findViewById(R.id.info_window_description);
                    // descriptionTextView.setText(String.format("Descripcion", style));

                    int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    bubbleLayout.measure(measureSpec, measureSpec);

                    float measuredWidth = bubbleLayout.getMeasuredWidth();

                    bubbleLayout.setArrowPosition(measuredWidth / 2 - 5);

                    Bitmap bitmap = SymbolGenerator.generate(bubbleLayout);
                    imagesMap.put(name, bitmap);
                    viewMap.put(name, bubbleLayout);
                }

                return imagesMap;
            } else {
                return null;
            }
        }

        @Override
        protected void onPostExecute(HashMap<String, Bitmap> bitmapHashMap) {
            super.onPostExecute(bitmapHashMap);
            DistritMapActivity activity = activityRef.get();
            if (activity != null && bitmapHashMap != null) {
                activity.setImageGenResults(bitmapHashMap);
                if (refreshSource) {
                    activity.refreshSource();
                }
            }
            //  Toast.makeText(activity, "Tap on marker instruction", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * AsyncTask to load data from the assets folder.
     */
    private static class LoadGeoJsonDataTask extends AsyncTask<Void, Void, FeatureCollection> {

        private final WeakReference<DistritMapActivity> activityRef;

        LoadGeoJsonDataTask(DistritMapActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }

        @Override
        protected FeatureCollection doInBackground(Void... params) {
            DistritMapActivity activity = activityRef.get();

            if (activity == null) {
                return null;
            }

            //   String geoJson = loadGeoJsonFromAsset(activity, "us_west_coast.geojson");

            String geoJson = null;
            try {
                geoJson = crearJson(activity.places);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return FeatureCollection.fromJson(geoJson);


        }

        @Override
        protected void onPostExecute(FeatureCollection featureCollection) {
            super.onPostExecute(featureCollection);
            DistritMapActivity activity = activityRef.get();
            if (featureCollection == null || activity == null) {
                return;
            }

// This example runs on the premise that each GeoJSON Feature has a "selected" property,
// with a boolean value. If your data's Features don't have this boolean property,
// add it to the FeatureCollection 's features with the following code:
            for (Feature singleFeature : featureCollection.features()) {
                singleFeature.addBooleanProperty(PROPERTY_SELECTED, false);
            }

            activity.setUpData(featureCollection);
            new GenerateViewIconTask(activity).execute(featureCollection);
        }

        static String loadGeoJsonFromAsset(Context context, String filename) {
            try {
// Load GeoJSON file from local asset folder
                InputStream is = context.getAssets().open(filename);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                return new String(buffer, Charset.forName("UTF-8"));
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }
    }


    /**
     * Set a feature selected state.
     *
     * @param index the index of selected feature
     */
    private void setSelected(int index) {
        if (featureCollection.features() != null) {
            Feature feature = featureCollection.features().get(index);
            setFeatureSelectState(feature, true);
            refreshSource();
        }
    }


    private static class MainActivityLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<DistritMapActivity> activityWeakReference;

        MainActivityLocationCallback(DistritMapActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location has changed.
         *
         * @param result the LocationEngineResult object which has the last known location within it.
         */
        @Override
        public void onSuccess(LocationEngineResult result) {
            DistritMapActivity activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();


                activity.actualPosition = activity.mapboxMap.getCameraPosition().target;


                if (location == null) {
                    return;
                }


                // Create a Toast which displays the new location's coordinates
                //    Toast.makeText(activity, String.format(activity.getString(R.string.new_location),
                //          String.valueOf(result.getLastLocation().getLatitude()), String.valueOf(result.getLastLocation().getLongitude())),
                //         Toast.LENGTH_SHORT).show();

                // Pass the new location to the Maps SDK's LocationComponent
                if (activity.mapboxMap != null && result.getLastLocation() != null) {
                    activity.mapboxMap.getLocationComponent().forceLocationUpdate(result.getLastLocation());
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
            DistritMapActivity activity = activityWeakReference.get();
            if (activity != null) {
                Toast.makeText(activity, exception.getLocalizedMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static String crearJson(List<Place> placesMap) throws JSONException {

        String lat;
        String lomn;
        String nameee;

        if (placesMap != null && placesMap.size() > 0) {
            JSONArray objArray = new JSONArray();

            JSONObject obj = new JSONObject();
            obj.put("type", "FeatureCollection");

            for (Place place : placesMap) {
                //  lat=dbPlace.getCreated_at();
                //  lomn=dbPlace.getCreated_at();
                //  nameee=dbPlace.getCreated_at();

                lat = place.getLat();
                lomn = place.getLng();
                nameee = place.getTittle();

                JSONObject objProperties = new JSONObject();
                objProperties.put("marker-color", "#7e7e7e");
                objProperties.put("marker-size", "medium");
                objProperties.put("marker-symbol", "");
                objProperties.put("name", nameee);
                objProperties.put("id", place.getId());
                //  objProperties.put("position", place.getId());

                JSONArray objCoordinates = new JSONArray();
                objCoordinates.put(lomn);
                objCoordinates.put(lat);

                JSONObject objGeometry = new JSONObject();
                objGeometry.put("type", "Point");
                objGeometry.put("coordinates", objCoordinates);

                JSONObject objGeneral = new JSONObject();
                objGeneral.put("type", "Feature");
                objGeneral.put("properties", objProperties);
                objGeneral.put("geometry", objGeometry);

                objArray.put(objGeneral);

            }

            obj.put("features", objArray);

            return obj.toString();
        } else {
            return null;
        }
    }


    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Set the LocationComponent activation options
            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(this, loadedMapStyle)
                            .useDefaultLocationEngine(false)
                            .build();

            // Activate with the LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(locationComponentActivationOptions);

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            initLocationEngine();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
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


    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
/*
//online mode
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
*/

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        if (offlineManager != null) {
            offlineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
                @Override
                public void onList(OfflineRegion[] offlineRegions) {
                    if (offlineRegions.length > 0) {
                        // delete the last item in the offlineRegions list which will be yosemite offline map
                        offlineRegions[(offlineRegions.length - 1)].delete(new OfflineRegion.OfflineRegionDeleteCallback() {
                            @Override
                            public void onDelete() {
                                Toast.makeText(
                                        DistritMapActivity.this,
                                        "nose deleteed creo",
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            @Override
                            public void onError(String error) {
                                Timber.e("On delete error: %s", error);
                            }
                        });
                    }
                }

                @Override
                public void onError(String error) {
                    Timber.e("onListError: %s", error);
                }
            });
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


}
