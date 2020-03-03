package com.avances.lima.interactor.route;

public interface RouteUpdatedCallback {

    void onRouteUpdatedSuccess(String message);

    void onRouteUpdatedError(String message);
}
