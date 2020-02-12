package com.avances.applima.presentation.view;

import com.avances.applima.data.datasource.db.model.DbUsuario;
import com.avances.applima.domain.model.Usuario;

public interface UsuarioView extends BaseView {

    void temporalUserRegistered(String idTempUser);
    void tokenGenerated(String token);
    void userRegistered(Usuario usuario);
    void loginSuccess(Usuario usuario);
    void loginSocialMediaSuccess(Usuario usuario);
    void forgotPasswordSuccess(String message);
    void reSendCodeSuccess(String message);
    void userGot(Usuario usuario);
    void validateCodeSuccess(Usuario usuario);

    void userUpdated(Usuario usuario);

    void showLoading();
    void hideLoading();
    void showErrorMessage(String message);
}
