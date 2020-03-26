package com.avances.lima.presentation.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avances.lima.R;
import com.avances.lima.domain.model.Place;
import com.avances.lima.presentation.presenter.PlacePresenter;
import com.avances.lima.presentation.ui.activities.EditProfileActivity;
import com.avances.lima.presentation.ui.activities.MainActivity;
import com.avances.lima.presentation.ui.activities.PlaceDetailActivity;
import com.avances.lima.presentation.ui.activities.PreferencesActivity;
import com.avances.lima.presentation.ui.adapters.FavoritesVerticalListDataAdapter;
import com.avances.lima.presentation.utils.Constants;
import com.avances.lima.presentation.utils.Helper;
import com.avances.lima.presentation.view.PlaceView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends BaseFragment implements
        View.OnClickListener,
        FavoritesVerticalListDataAdapter.OnFavoritosVerticalListClickListener, PlaceView, MainActivity.LikeAPlaceAddFavorite {

    public static RecyclerView rvFavorites;
    PlacePresenter placePresenter;
    private FavoritesVerticalListDataAdapter.OnFavoritosVerticalListClickListener mlistener;
    public static List<Place> favoritesPlaces;
    LinearLayout llNoFavorites;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.favoritos_fragment, null);
        injectView(x);
        initUI(x);
        loadPresenter();
        return x;
    }

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

    void loadPresenter() {
        placePresenter = new PlacePresenter();
        placePresenter.addView(this);
        placePresenter.getPlaces(Constants.STORE.DB);
    }

    void initUI(View v) {
        rvFavorites = (RecyclerView) v.findViewById(R.id.rvFavorites);
        llNoFavorites = (LinearLayout) v.findViewById(R.id.llNoFavorites);
        mlistener = this;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            updateList();
        }
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
            rvFavorites.setVisibility(View.VISIBLE);
            rvFavorites.setHasFixedSize(true);
            rvFavorites.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rvFavorites.setAdapter(routesHorizontalDataAdapter);
        }
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
            rvFavorites.setVisibility(View.VISIBLE);
            rvFavorites.setHasFixedSize(true);
            rvFavorites.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            rvFavorites.setAdapter(routesHorizontalDataAdapter);
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            MainActivity.FRAGMENT_VIEWING = Constants.FRAGMENTS_TABS.FAVORITES;
        }
    }

}
