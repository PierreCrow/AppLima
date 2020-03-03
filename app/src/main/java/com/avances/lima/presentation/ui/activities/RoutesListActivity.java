package com.avances.lima.presentation.ui.activities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.model.Route;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.ui.adapters.RoutePlacesVerticalListDataAdapter;
import com.avances.lima.presentation.ui.dialogfragment.InfografiaDialog;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.utils.SingleClick;
import com.avances.lima.presentation.view.PlaceView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RoutesListActivity extends BaseActivity
        implements RoutePlacesVerticalListDataAdapter.OnRutasPlacesVerticalClickListener, PlaceView {


    @BindView(R.id.ivGoToMap)
    ImageView ivGoToMap;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivinfografia)
    ImageView ivinfografia;
    @BindView(R.id.rv_lugaress)
    RecyclerView rvLugares;
    @BindView(R.id.tvRouteName)
    TextView tvRouteName;

    SingleClick singleClick;
    private RoutePlacesVerticalListDataAdapter.OnRutasPlacesVerticalClickListener mlistener;
    PlacePresenter placePresenter;
    Route route;
    List<Place> miPlaces;


    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.route_list_activity);
        injectView();
        initUI();
        loadPresenter();

    }

    private void onClickListener() {
        singleClick = new SingleClick() {
            @Override
            public void onSingleClick(View v) {

                switch (v.getId()) {
                    case R.id.ivGoToMap:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("route", route);
                        next(RoutesMapActivity.class, bundle);
                        break;
                    case R.id.ivBack:
                        next(MainActivity.class, null);
                        break;
                    case R.id.ivinfografia:
                        Bundle bundle2 = new Bundle();
                        bundle2.putSerializable("route", route);

                        InfografiaDialog df = new InfografiaDialog();
                        df.setArguments(bundle2);
                        df.show(getSupportFragmentManager(), "InfografiaDialog");
                        break;
                }
            }
        };
    }


    private void initUI() {

        Bundle bundle = getIntent().getBundleExtra("extra");
        route = (Route) bundle.getSerializable("route");

        mlistener = this;
        tvRouteName.setText(route.getRouteName());

        onClickListener();
        ivGoToMap.setOnClickListener(singleClick);
        ivBack.setOnClickListener(singleClick);
        ivinfografia.setOnClickListener(singleClick);
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
        bundle.putBoolean("fromDistrit", false);
        // loadPlaceDetailFragment(bundle);
        next(PlaceDetailActivity.class, bundle);
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

        miPlaces = new ArrayList<>();


        for (Place place : places) {
            for (String idPlace : route.getIdPlaceList()) {
                if (place.getId().equals(idPlace)) {
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

        RoutePlacesVerticalListDataAdapter routesHorizontalDataAdapter = new RoutePlacesVerticalListDataAdapter(mlistener, getApplicationContext(), miPlaces, userHasLocation);

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
