package com.avances.lima.interactor.usuario;

public interface UsuarioCreatedCallback {

    void onUserCreatedSuccess(String message);

    void onUserCreatedError(String message);
}
