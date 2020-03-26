package com.avances.lima.presentation.ui.activities;

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

import com.avances.lima.R;
import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.model.Route;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.ui.adapters.RoutePlacesMapHorizontalListDataAdapter;
import com.avances.lima.presentation.ui.dialogfragment.InfografiaDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.view.PlaceView;
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

import butterknife.BindView;
import timber.log.Timber;

import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.expressions.Expression.sin;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ANCHOR_BOTTOM;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class RoutesMapActivity extends BaseActivity implements
        OnMapReadyCallback, PermissionsListener, MapboxMap.OnMapClickListener,
        PlaceView, RoutePlacesMapHorizontalListDataAdapter.OnPlacesMapHorizontalClickListener {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.ivGoToList)
    ImageView ivGoToList;
    @BindView(R.id.ivinfografia)
    ImageView ivinfografia;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.rv_lugaress)
    RecyclerView rvLugares;

    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;
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
    String idPlaceSelected;
    private GeoJsonSource source;
    private FeatureCollection featureCollection;
    private MainActivityLocationCallback callback = new MainActivityLocationCallback(this);
    private OfflineManager offlineManager;
    public static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";
    private boolean isEndNotified;
    private ProgressBar progressBar;
    GeoJsonSource geoJsonSource;
    Route route;
    PlacePresenter placePresenter;
    static List<Place> places;
    List<String> idPlaces;
    LatLng routePosition;
    private RoutePlacesMapHorizontalListDataAdapter.OnPlacesMapHorizontalClickListener mlistenerPlacesMapHorizontal;
    SingleClick singleClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String accesToken = getResources().getString(R.string.mapbox_access_token);
        Mapbox.getInstance(this, accesToken);
        setContentView(R.layout.route_map_activity);
        injectView();
        initUI(savedInstanceState);
        loadPresenter();

    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.btnBack:
                        next(MainActivity.class, null);
                        break;
                    case R.id.ivGoToList:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("route", route);
                        next(RoutesListActivity.class, bundle);
                        break;
                    case R.id.ivinfografia:
                        Bundle bundle2 = new Bundle();
                        bundle2.putSerializable("route", route);

                        InfografiaDialog df = new InfografiaDialog();
                        df.setArguments(bundle2);
                        df.show(getSupportFragmentManager(), "");
                        break;
                }
            }
        };
    }


    void initUI(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getBundleExtra("extra");
        route = (Route) bundle.getSerializable("route");
        routePosition = new LatLng();
        idPlaces = route.getIdPlaceList();
        places = new ArrayList<>();
        mlistenerPlacesMapHorizontal = this;
        onClickListener();
        ivGoToList.setOnClickListener(singleClick);
        btnBack.setOnClickListener(singleClick);
        ivinfografia.setOnClickListener(singleClick);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        tvTittle.setText(route.getRouteName());
    }


    static int getPositionOfPlaceSelected(String idPlace) {
        Integer idPl = null;
        for (int i = 0; i < places.size(); i++) {
            if (places.get(i).getId().equals(idPlace)) {
                idPl = i;
            }
        }
        return idPl;
    }

    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
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
        LatLng distritMap = new LatLng();
        // distritMap.setLatitude(Double.parseDouble(route.getLatitude()));
        // distritMap.setLongitude(Double.parseDouble(route.getLongitude()));

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
                        new LoadGeoJsonDataTask(RoutesMapActivity.this).execute();
                        mapboxMap.addOnMapClickListener(RoutesMapActivity.this);

                        //offline
                        //  offlineMap(style);

                        enableLocationComponent(style);
                    }
                });
    }


    void offlineMap(Style style) {

        offlineManager = OfflineManager.getInstance(RoutesMapActivity.this);
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
                RoutesMapActivity.this.getResources().getDisplayMetrics().density);


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
        if (isEndNotified) {
            return;
        }
        isEndNotified = true;
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
        Toast.makeText(RoutesMapActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void startProgress() {
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


    private void setupSource(@NonNull Style loadedStyle) {
        source = new GeoJsonSource(GEOJSON_SOURCE_ID, featureCollection);
        loadedStyle.addSource(source);
    }


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

    private void refreshSource() {
        if (source != null && featureCollection != null) {
            source.setGeoJson(featureCollection);
        }
    }

    private void setUpMarkerLayer(@NonNull Style loadedStyle) {
        loadedStyle.addLayer(new SymbolLayer(MARKER_LAYER_ID, GEOJSON_SOURCE_ID)
                .withProperties(
                        iconImage(MARKER_IMAGE_ID),
                        iconAllowOverlap(true),
                        iconOffset(new Float[]{0f, -8f})
                ));
    }


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

    private boolean handleClickIcon(PointF screenPoint) {
        List<Feature> features = mapboxMap.queryRenderedFeatures(screenPoint, MARKER_LAYER_ID);
        if (!features.isEmpty()) {
            String name = features.get(0).getStringProperty(PROPERTY_NAME);
            List<Feature> featureList = featureCollection.features();
            if (featureList != null) {
                for (int i = 0; i < featureList.size(); i++) {
                    if (featureList.get(i).getStringProperty(PROPERTY_NAME).equals(name)) {
                        Integer posisicon = getPositionOfPlaceSelected(featureList.get(i).getStringProperty(PROPERTY_ID));
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

    private void setFeatureSelectState(Feature feature, boolean selectedState) {
        if (feature.properties() != null) {
            feature.properties().addProperty(PROPERTY_SELECTED, selectedState);
            refreshSource();
        }
    }

    private boolean featureSelectStatus(int index) {
        if (featureCollection == null) {
            return false;
        }
        return featureCollection.features().get(index).getBooleanProperty(PROPERTY_SELECTED);
    }


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
        next(PlaceDetailActivity.class, bundle);
    }

    private static class SymbolGenerator {
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
        private final WeakReference<RoutesMapActivity> activityRef;
        private final boolean refreshSource;

        GenerateViewIconTask(RoutesMapActivity activity, boolean refreshSource) {
            this.activityRef = new WeakReference<>(activity);
            this.refreshSource = refreshSource;
        }

        GenerateViewIconTask(RoutesMapActivity activity) {
            this(activity, false);
        }

        @SuppressWarnings("WrongThread")
        @Override
        protected HashMap<String, Bitmap> doInBackground(FeatureCollection... params) {
            RoutesMapActivity activity = activityRef.get();
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
            RoutesMapActivity activity = activityRef.get();
            if (activity != null && bitmapHashMap != null) {
                activity.setImageGenResults(bitmapHashMap);
                if (refreshSource) {
                    activity.refreshSource();
                }
            }
            //  Toast.makeText(activity, "Tap on marker instruction", Toast.LENGTH_SHORT).show();
        }
    }

    private static class LoadGeoJsonDataTask extends AsyncTask<Void, Void, FeatureCollection> {

        private final WeakReference<RoutesMapActivity> activityRef;

        LoadGeoJsonDataTask(RoutesMapActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }

        @Override
        protected FeatureCollection doInBackground(Void... params) {
            RoutesMapActivity activity = activityRef.get();

            if (activity == null) {
                return null;
            }
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
            RoutesMapActivity activity = activityRef.get();
            if (featureCollection == null || activity == null) {
                return;
            }

            for (Feature singleFeature : featureCollection.features()) {
                singleFeature.addBooleanProperty(PROPERTY_SELECTED, false);
            }

            activity.setUpData(featureCollection);
            new GenerateViewIconTask(activity).execute(featureCollection);
        }

        static String loadGeoJsonFromAsset(Context context, String filename) {
            try {
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


    private void setSelected(int index) {
        if (featureCollection.features() != null) {
            Feature feature = featureCollection.features().get(index);
            setFeatureSelectState(feature, true);
            refreshSource();
        }
    }


    private static class MainActivityLocationCallback
            implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<RoutesMapActivity> activityWeakReference;

        MainActivityLocationCallback(RoutesMapActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(LocationEngineResult result) {
            RoutesMapActivity activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();
                activity.actualPosition = activity.mapboxMap.getCameraPosition().target;

                if (location == null) {
                    return;
                }

                if (activity.mapboxMap != null && result.getLastLocation() != null) {
                    activity.mapboxMap.getLocationComponent().forceLocationUpdate(result.getLastLocation());
                }
            }
        }

        @Override
        public void onFailure(@NonNull Exception exception) {
            RoutesMapActivity activity = activityWeakReference.get();
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
                lat = place.getLat();
                lomn = place.getLng();
                nameee = place.getTittle();
                JSONObject objProperties = new JSONObject();
                objProperties.put("marker-color", "#7e7e7e");
                objProperties.put("marker-size", "medium");
                objProperties.put("marker-symbol", "");
                objProperties.put("name", nameee);
                objProperties.put("id", place.getId());

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
                                Toast.makeText(RoutesMapActivity.this, "deleteed",
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
