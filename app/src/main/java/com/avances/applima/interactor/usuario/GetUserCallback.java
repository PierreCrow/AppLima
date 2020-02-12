package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

public interface GetUserCallback {

    void onGetUserSuccess(Usuario usuario);

    void onGetUserError(String message);
}
