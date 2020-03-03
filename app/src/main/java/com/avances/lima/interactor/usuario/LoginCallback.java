package com.avances.lima.interactor.usuario;

import com.avances.lima.domain.model.Usuario;

public interface LoginCallback {

    void onLoginSuccess(Usuario usuario);

    void onLoginError(String message);
}
