package com.avances.applima.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.applima.R;
import com.avances.applima.domain.model.Place;
import com.avances.applima.presentation.presenter.PlacePresenter;
import com.avances.applima.presentation.ui.activities.EditProfileActivity;
import com.avances.applima.presentation.ui.activities.MainActivity;
import com.avances.applima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.applima.presentation.ui.activities.PreferencesActivity;
import com.avances.applima.presentation.ui.adapters.FavoritesVerticalListDataAdapter;
import com.avances.applima.presentation.utils.Constants;
import com.avances.applima.presentation.utils.Helper;
import com.avances.applima.presentation.view.PlaceView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends BaseFragment implements
        View.OnClickListener,
        FavoritesVerticalListDataAdapter.OnFavoritosVerticalListClickListener, PlaceView, MainActivity.LikeAPlaceAddFavorite {


    TextView btnMenosImperdibles;
    public static RecyclerView rv_imperdibles;
    PlacePresenter placePresenter;

    private FavoritesVerticalListDataAdapter.OnFavoritosVerticalListClickListener mlistener;
    public static List<Place> favoritesPlaces;

    LinearLayout llNoFavorites;


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.llEditarPerfil:
                next(EditProfileActivity.class, getContext(), null);
                break;
            case R.id.llPreferencias:
                next(PreferencesActivity.class, getContext(), null);
                break;
            case R.id.llValoraApp:

                break;
            case R.id.llCerrarSesion:

                break;

        }
    }


    @Override
    public void onPause() {
        super.onPause();


    }

    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }

    void initUI(View v) {

        rv_imperdibles = (RecyclerView) v.findViewById(R.id.rv_favoritos);
        llNoFavorites = (LinearLayout) v.findViewById(R.id.llNoFavorites);
        mlistener = this;

    }


    void clickEvents() {


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            updateList();
        } else {
        }
    }


    void GoBack() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //   fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_out_down);
        HomeFragment accountFragment = new HomeFragment();
        fragmentTransaction.replace(R.id.containerView, accountFragment);
        fragmentTransaction.commit();
    }

    public void refreshList(Place place) {

        favoritesPlaces.add(place);


    }


    void updateList() {
        boolean userHasLocation = false;

        if (Helper.getUserAppPreference(getContext()).isHasLocation()) {
            if (Helper.gpsIsEnabled(getContext())) {
                userHasLocation = true;
            } else {
                userHasLocation = false;
            }
        } else {
            userHasLocation = false;
        }


        FavoritesVerticalListDataAdapter routesHorizontalDataAdapter = new FavoritesVerticalListDataAdapter(mlistener, getContext(), favoritesPlaces, userHasLocation);

        if (favoritesPlaces.size() == 0) {

        } else {
            llNoFavorites.setVisibility(View.GONE);
            rv_imperdibles.setVisibility(View.VISIBLE);
            rv_imperdibles.setHasFixedSize(true);
            rv_imperdibles.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rv_imperdibles.setAdapter(routesHorizontalDataAdapter);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View x = inflater.inflate(R.layout.favoritos_fragment, null);


        initUI(x);

        clickEvents();

        loadPresenter();


        return x;

    }


    @Override
    public void placeListLoaded(List<Place> mPlaces) {

        favoritesPlaces = new ArrayList<>();

        for (Place place : mPlaces) {
            if (place.isFavorite()) {
                favoritesPlaces.add(place);
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


        FavoritesVerticalListDataAdapter routesHorizontalDataAdapter = new FavoritesVerticalListDataAdapter(mlistener, getContext(), favoritesPlaces, userHasLocation);

        if (favoritesPlaces.size() == 0) {

        } else {

            llNoFavorites.setVisibility(View.GONE);
            rv_imperdibles.setVisibility(View.VISIBLE);
            rv_imperdibles.setHasFixedSize(true);
            rv_imperdibles.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rv_imperdibles.setAdapter(routesHorizontalDataAdapter);
        }


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
    public void onFavoritosVerticalListClicked(View v, Integer position) {

        Place place = favoritesPlaces.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", place);
        bundle.putBoolean("fromDistrit", false);

        next(PlaceDetailActivity.class, getContext(), bundle);

    }

    @Override
    public void onLikeAPlaceAddFavorite(Place place) {

        refreshList(place);
    }
}
