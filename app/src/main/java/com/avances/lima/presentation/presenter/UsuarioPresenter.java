package com.avances.lima.presentation.presenter;

import com.avances.lima.data.datasource.datastore.UsuarioDataStoreFactory;
import com.avances.lima.data.repository.UsuarioDataRepository;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.domain.repository.UsuarioRepository;
import com.avances.lima.interactor.usuario.ForgotPasswordCallback;
import com.avances.lima.interactor.usuario.GenerateTokenCallback;
import com.avances.lima.interactor.usuario.GetUserCallback;
import com.avances.lima.interactor.usuario.LoginCallback;
import com.avances.lima.interactor.usuario.LoginSocialMediaCallback;
import com.avances.lima.interactor.usuario.RegisterTemporalUserCallback;
import com.avances.lima.interactor.usuario.RegisterUserCallback;
import com.avances.lima.interactor.usuario.ResendCodeCallback;
import com.avances.lima.interactor.usuario.RoutesByInterestCallback;
import com.avances.lima.interactor.usuario.UpdateUserCallback;
import com.avances.lima.interactor.usuario.UsuarioInteractor;
import com.avances.lima.interactor.usuario.ValidateCodeCallback;
import com.avances.lima.interactor.usuario.VersionAppCallback;
import com.avances.lima.presentation.view.UsuarioView;

import java.util.List;

public class UsuarioPresenter implements Presenter<UsuarioView>, RegisterTemporalUserCallback,
        LoginCallback,LoginSocialMediaCallback,ForgotPasswordCallback,ResendCodeCallback,
        GetUserCallback,ValidateCodeCallback,RegisterUserCallback, GenerateTokenCallback,
        UpdateUserCallback, RoutesByInterestCallback, VersionAppCallback
{

    private UsuarioView usuarioView;
    private UsuarioInteractor usuarioInteractor;


    public void registerTemporalUser(String token,String idTokenFCM) {
        usuarioInteractor.registerTemporalUser(token,idTokenFCM,this);
    }


    public void registerUser(String token,String name, String birthDay, String sex, String country,
                             String email, String password, String idTemporal, String registerType,
                             String idSystem) {
        usuarioInteractor.registerUser(token,name, birthDay, sex, country,
                email, password, idTemporal, registerType, idSystem, this);
    }

    public void login(String token,String email, String password, String idSystem, String registerType) {
        usuarioInteractor.login(token,email, password, idSystem, registerType, this);
    }

    public void loginSocialMedia(String token,String email, String name, String idSystem, String registerType,
                                 String idTemporal) {
        usuarioInteractor.loginSocialMedia(token,email, name, idSystem, registerType, idTemporal,this);
    }


    public void forgotPassword(String token,String idSystem, String registerType, String email) {
        usuarioInteractor.forgotPassword(token,idSystem, registerType, email, this);
    }

    public void reSendCode(String token,String idSystem, String registerType, String email) {
        usuarioInteractor.reSendCode(token,idSystem, registerType, email, this);
    }


    public void getUser(String token,String email,String idSystem) {
        usuarioInteractor.getUser(token,email, idSystem, this);
    }


    public void validateCode(String token,String email,String recoveryCode) {
        usuarioInteractor.validateCode(token,email, recoveryCode, this);
    }


    public void updateUser(String token, String name, String birthDate, String gender, String country, String email, String password, String registerType, String idSystem) {
        usuarioInteractor.updateUser(token,name, birthDate,gender,country,email,password,registerType,idSystem, this);
    }

    public void routesByInterest(String token, List<String> interestList, String permanencyDays)
    {
        usuarioInteractor.routesByInterest(token,interestList,permanencyDays,this);
    }


    public void generateToken() {
        usuarioInteractor.generateToken( this);
    }

    public void versionApp(String token) {
        usuarioInteractor.getVersionApp( token,this);
    }


    @Override
    public void addView(UsuarioView view) {
        this.usuarioView = view;
        UsuarioRepository requestRepository = new UsuarioDataRepository(new UsuarioDataStoreFactory(this.usuarioView.getContext()));
        usuarioInteractor = new UsuarioInteractor(requestRepository);
    }

    @Override
    public void removeView(UsuarioView view) {

    }


    @Override
    public void onRegisterTemporalUserSuccess(String idTempUser) {
        usuarioView.temporalUserRegistered(idTempUser);
    }

    @Override
    public void onRegisterTemporalUserError(String message) {
        usuarioView.showErrorMessage(message);
    }


    @Override
    public void onGetUserSuccess(Usuario usuario) {
        usuarioView.userGot(usuario);
    }

    @Override
    public void onGetUserError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onLoginSuccess(Usuario usuario) {
        usuarioView.loginSuccess(usuario);
    }


    @Override
    public void onLoginError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onForgotPasswordSuccess(String message) {
        usuarioView.forgotPasswordSuccess(message);
    }

    @Override
    public void onForgotPasswordError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onResendCodeSuccess(String message) {
        usuarioView.reSendCodeSuccess(message);
    }

    @Override
    public void onResendCodeError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onValidateCodeSuccess(Usuario usuario) {
        usuarioView.validateCodeSuccess(usuario);
    }

    @Override
    public void onValidateCodeError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onLoginSocialMediaSuccess(Usuario usuario) {
        usuarioView.loginSocialMediaSuccess(usuario);
    }

    @Override
    public void onLoginSocialMediaError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onRegisterUserSuccess(Usuario usuario) {
        usuarioView.userRegistered(usuario);
    }

    @Override
    public void onRegisterUserError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onGenerateTokenSuccess(String token) {
        usuarioView.tokenGenerated(token);
    }

    @Override
    public void onGenerateTokenError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onUpdateUserSuccess(Usuario usuario) {
        usuarioView.userUpdated(usuario);
    }

    @Override
    public void onUpdateUserError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onRoutesByInterestSuccess(List<String> idRoutes) {
        usuarioView.routesByInterestSuccess(idRoutes);
    }

    @Override
    public void onRoutesByInterestError(String message) {
        usuarioView.showErrorMessage(message);
    }

    @Override
    public void onVersionAppSuccess(String version) {
        usuarioView.versionApp(version);
    }

    @Override
    public void onVersionAppError(String message) {
        usuarioView.showErrorMessage(message);
    }
}
