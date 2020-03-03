package com.avances.lima.interactor.route;

public interface RouteCreatedCallback {

    void onRouteCreatedSuccess(String message);

    void onRouteCreatedError(String message);
}
