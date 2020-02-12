package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

public interface ValidateCodeCallback {

    void onValidateCodeSuccess(Usuario usuario);

    void onValidateCodeError(String message);
}
