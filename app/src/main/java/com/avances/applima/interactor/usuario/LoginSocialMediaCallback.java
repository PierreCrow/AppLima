package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

public interface LoginSocialMediaCallback {

    void onLoginSocialMediaSuccess(Usuario usuario);

    void onLoginSocialMediaError(String message);
}
