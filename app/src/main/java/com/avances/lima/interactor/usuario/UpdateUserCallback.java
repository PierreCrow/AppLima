package com.avances.lima.interactor.usuario;

import com.avances.lima.domain.model.Usuario;

public interface UpdateUserCallback {

    void onUpdateUserSuccess(Usuario usuario);

    void onUpdateUserError(String message);
}
