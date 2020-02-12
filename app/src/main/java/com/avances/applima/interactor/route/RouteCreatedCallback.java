package com.avances.applima.interactor.route;

public interface RouteCreatedCallback {

    void onRouteCreatedSuccess(String message);

    void onRouteCreatedError(String message);
}
