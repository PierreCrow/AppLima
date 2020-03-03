package com.avances.lima.interactor.usuario;

import java.util.List;

public interface RoutesByInterestCallback {

    void onRoutesByInterestSuccess(List<String> idRoutes);

    void onRoutesByInterestError(String message);
}
