package com.avances.lima.domain.repository;

import com.avances.lima.data.datasource.db.model.DbPlace;
import com.avances.lima.domain.model.Place;
import com.avances.lima.interactor.place.PlaceCreatedCallback;
import com.avances.lima.interactor.place.PlaceListCallback;
import com.avances.lima.interactor.place.PlaceUpdatedCallback;

import java.util.List;

public interface PlaceRepository {

    void getPlaces(int store, PlaceListCallback placeListCallback);

    void createPlaces(List<DbPlace> dbPlaces, int store, PlaceCreatedCallback placeCreatedCallback);

    void updatePlaceFavorite(Place place,boolean favorite, int store, PlaceUpdatedCallback placeUpdatedCallback);
}
