package com.avances.lima.presentation.view;

import com.avances.lima.domain.model.Usuario;

import java.util.List;

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
    void routesByInterestSuccess(List<String> idRoutes);
    void userUpdated(Usuario usuario);
    void versionApp(String version);
    void imageUploaded(String message);

    void showLoading();
    void hideLoading();
    void showErrorMessage(String message);
}
