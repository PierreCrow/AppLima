package com.avances.applima.interactor.route;

public interface RouteUpdatedCallback {

    void onRouteUpdatedSuccess(String message);

    void onRouteUpdatedError(String message);
}
