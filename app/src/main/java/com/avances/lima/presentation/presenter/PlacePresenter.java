package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.PlaceDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.data.repository.PlaceDataRepository;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.repository.PlaceRepository;
import com.avances.lima.interactor.place.PlaceCreatedCallback;
import com.avances.lima.interactor.place.PlaceInteractor;
import com.avances.lima.interactor.place.PlaceListCallback;
import com.avances.lima.interactor.place.PlaceUpdatedCallback;
import com.avances.lima.presentation.view.PlaceView;

import java.util.List;

public class PlacePresenter implements Presenter<PlaceView>, PlaceListCallback, PlaceCreatedCallback, PlaceUpdatedCallback {

    private PlaceView placeView;
    private PlaceInteractor placeInteractor;


    public void getPlaces(int store) {
        placeInteractor.getPlaces(store, this);
    }

    public void createPlaces(List<DbPlace> dbPlaces, int store) {
        placeInteractor.createPlaces(dbPlaces, store, this);
    }

    public void updatePlaceFavorite(Place place,boolean favorite, int store) {
        placeInteractor.updatePlaceFavorite(place,favorite, store, this);
    }


    @Override
    public void onGetPlaceListSuccess(List<Place> places) {
        placeView.placeListLoaded(places);
    }

    @Override
    public void onGetPlaceListError(String message) {
        placeView.showErrorMessage(message);
    }

    @Override
    public void addView(PlaceView view) {
        this.placeView = view;
        PlaceRepository placeRepository = new PlaceDataRepository(new PlaceDataStoreFactory(this.placeView.getContext()));
        placeInteractor = new PlaceInteractor(placeRepository);
    }

    @Override
    public void removeView(PlaceView view) {

    }

    @Override
    public void onPlaceCreatedSuccess(String message) {
        placeView.placeCreated(message);
    }

    @Override
    public void onPlaceCreatedError(String message) {
        placeView.showErrorMessage(message);
    }

    @Override
    public void onPlaceUpdatedSuccess(String message) {
        placeView.placeUpdated(message);
    }

    @Override
    public void onPlaceUpdatedError(String message) {
        placeView.showErrorMessage(message);
    }
}
