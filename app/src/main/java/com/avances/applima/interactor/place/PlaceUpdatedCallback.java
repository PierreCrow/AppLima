package com.avances.applima.interactor.place;

public interface PlaceUpdatedCallback {

    void onPlaceUpdatedSuccess(String message);

    void onPlaceUpdatedError(String message);
}
