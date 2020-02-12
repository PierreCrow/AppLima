package com.avances.applima.interactor.usuario;

public interface RegisterTemporalUserCallback {

    void onRegisterTemporalUserSuccess(String idCloud);

    void onRegisterTemporalUserError(String message);
}
