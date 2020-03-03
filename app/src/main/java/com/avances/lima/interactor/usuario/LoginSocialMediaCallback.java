package com.avances.lima.interactor.usuario;

import com.avances.lima.domain.model.Usuario;

public interface LoginSocialMediaCallback {

    void onLoginSocialMediaSuccess(Usuario usuario);

    void onLoginSocialMediaError(String message);
}
