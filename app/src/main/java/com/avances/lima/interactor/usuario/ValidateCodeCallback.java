package com.avances.lima.interactor.usuario;

import com.avances.lima.domain.model.Usuario;

public interface ValidateCodeCallback {

    void onValidateCodeSuccess(Usuario usuario);

    void onValidateCodeError(String message);
}
