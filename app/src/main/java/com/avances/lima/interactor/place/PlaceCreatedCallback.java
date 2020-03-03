package com.avances.lima.interactor.place;

public interface PlaceCreatedCallback {

    void onPlaceCreatedSuccess(String message);

    void onPlaceCreatedError(String message);
}
