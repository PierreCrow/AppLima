package com.avances.applima.interactor.place;

import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Place;
import com.avances.applima.domain.repository.PlaceRepository;

import java.util.List;

public class PlaceInteractor {

    private final PlaceRepository placeRepository;

    public PlaceInteractor(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public void getPlaces(int store, PlaceListCallback placeListCallback) {
        placeRepository.getPlaces(store, placeListCallback);
    }

    public void createPlaces(List<DbPlace> dbPlaces, int store, PlaceCreatedCallback placeCreatedCallback) {
        placeRepository.createPlaces(dbPlaces,store, placeCreatedCallback);
    }

    public void updatePlaceFavorite(Place place,boolean favorite, int store, PlaceUpdatedCallback placeUpdatedCallback) {
        placeRepository.updatePlaceFavorite(place,favorite,store, placeUpdatedCallback);
    }

}
