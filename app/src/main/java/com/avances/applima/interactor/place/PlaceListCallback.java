package com.avances.applima.interactor.place;

import com.avances.applima.data.datasource.db.model.DbPlace;
import com.avances.applima.domain.model.Place;

import java.util.ArrayList;
import java.util.List;

public interface PlaceListCallback {

    void onGetPlaceListSuccess(List<Place> places);
    void onGetPlaceListError(String message);
}
