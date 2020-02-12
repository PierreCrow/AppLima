package com.avances.applima.interactor.route;

import com.avances.applima.domain.model.Route;

import java.util.List;

public interface RouteListCallback {

    void onGetRouteListSuccess(List<Route> routes);

    void onGetRouteListError(String message);
}
