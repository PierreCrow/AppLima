package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

import java.util.List;

public interface RoutesByInterestCallback {

    void onLoginSuccess(List<String> idRoutes);

    void onLoginError(String message);
}
