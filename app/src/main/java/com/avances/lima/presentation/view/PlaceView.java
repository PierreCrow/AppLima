package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.Place;


import java.util.List;

public interface PlaceView extends BaseView {

    void placeListLoaded(List<Place> places);

    void placeCreated(String message);

    void placeUpdated(String message);

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);
}
