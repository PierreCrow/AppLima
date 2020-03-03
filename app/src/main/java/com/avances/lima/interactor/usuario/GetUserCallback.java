package com.avances.lima.interactor.usuario;

import com.avances.lima.domain.model.Usuario;

public interface GetUserCallback {

    void onGetUserSuccess(Usuario usuario);

    void onGetUserError(String message);
}
