package com.avances.lima.interactor.route;

import com.avances.lima.domain.model.Route;

import java.util.List;

public interface RouteListCallback {

    void onGetRouteListSuccess(List<Route> routes);

    void onGetRouteListError(String message);
}
