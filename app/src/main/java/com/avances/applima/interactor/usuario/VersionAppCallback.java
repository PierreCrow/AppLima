package com.avances.applima.interactor.usuario;


public interface VersionAppCallback {

    void onVersionAppSuccess(String version);

    void onVersionAppError(String message);
}
