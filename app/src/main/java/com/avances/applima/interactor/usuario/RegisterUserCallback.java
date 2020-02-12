package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

public interface RegisterUserCallback {

    void onRegisterUserSuccess(Usuario usuario);

    void onRegisterUserError(String message);
}
