package com.avances.lima.interactor.usuario;

import com.avances.lima.domain.model.Usuario;

public interface RegisterUserCallback {

    void onRegisterUserSuccess(Usuario usuario);

    void onRegisterUserError(String message);
}
