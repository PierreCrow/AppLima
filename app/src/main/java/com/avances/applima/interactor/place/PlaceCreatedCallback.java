package com.avances.applima.interactor.place;

public interface PlaceCreatedCallback {

    void onPlaceCreatedSuccess(String message);

    void onPlaceCreatedError(String message);
}
