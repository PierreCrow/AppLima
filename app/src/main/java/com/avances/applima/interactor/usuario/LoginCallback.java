package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

public interface LoginCallback {

    void onLoginSuccess(Usuario usuario);

    void onLoginError(String message);
}
