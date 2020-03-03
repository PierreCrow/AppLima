package com.avances.lima.data.repository;

import com.avances.lima.data.datasource.datastore.PlaceDataStore;
import com.avances.lima.data.datasource.datastore.PlaceDataStoreFactory;
import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.domain.model.Place;
import com.avances.lima.domain.repository.PlaceRepository;
import com.avances.lima.domain.repository.RepositoryCallback;
import com.avances.lima.interactor.place.PlaceCreatedCallback;
import com.avances.lima.interactor.place.PlaceListCallback;
import com.avances.lima.interactor.place.PlaceUpdatedCallback;

import java.util.List;

public class PlaceDataRepository implements PlaceRepository {

    private final PlaceDataStoreFactory placeDataStoreFactory;

    public PlaceDataRepository(PlaceDataStoreFactory placeDataStoreFactory) {
        this.placeDataStoreFactory = placeDataStoreFactory;
    }

    @Override
    public void getPlaces(int store, PlaceListCallback placeListCallback) {
        final PlaceDataStore placeDataStore = placeDataStoreFactory.create(store);
        placeDataStore.getPlaces(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                placeListCallback.onGetPlaceListError(message);
            }

            @Override
            public void onSuccess(Object object) {

                List<Place> places1 = (List<Place>) object;
                placeListCallback.onGetPlaceListSuccess(places1);
            }
        });
    }

    @Override
    public void createPlaces(List<DbPlace> dbPlaces, int store, PlaceCreatedCallback placeCreatedCallback) {
        final PlaceDataStore placeDataStore = placeDataStoreFactory.create(store);
        placeDataStore.createPlaces(dbPlaces, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                placeCreatedCallback.onPlaceCreatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                placeCreatedCallback.onPlaceCreatedSuccess(message);
            }
        });
    }

    @Override
    public void updatePlaceFavorite(Place place,boolean favorite, int store, PlaceUpdatedCallback placeUpdatedCallback) {
        final PlaceDataStore placeDataStore = placeDataStoreFactory.create(store);
        placeDataStore.updatePlace(place,favorite, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                placeUpdatedCallback.onPlaceUpdatedError(message);
            }

            @Override
            public void onSuccess(Object object) {

                String message = (String) object;
                placeUpdatedCallback.onPlaceUpdatedSuccess(message);
            }
        });
    }
}
