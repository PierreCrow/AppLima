package com.avances.lima.interactor.place;

import com.avances.lima.domain.model.Place;

import java.util.List;

public interface PlaceListCallback {

    void onGetPlaceListSuccess(List<Place> places);
    void onGetPlaceListError(String message);
}
