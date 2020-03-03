package com.avances.lima.interactor.place;

public interface PlaceUpdatedCallback {

    void onPlaceUpdatedSuccess(String message);

    void onPlaceUpdatedError(String message);
}
