package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.model.Usuario;

public interface UpdateUserCallback {

    void onUpdateUserSuccess(Usuario usuario);

    void onUpdateUserError(String message);
}
