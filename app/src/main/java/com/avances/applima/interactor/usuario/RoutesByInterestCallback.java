package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

import java.util.List;

public interface RoutesByInterestCallback {

    void onRoutesByInterestSuccess(List<String> idRoutes);

    void onRoutesByInterestError(String message);
}
