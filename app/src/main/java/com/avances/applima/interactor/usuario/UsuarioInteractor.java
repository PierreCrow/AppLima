package com.avances.applima.interactor.usuario;

import com.avances.applima.domain.repository.UsuarioRepository;

import java.util.List;

public class UsuarioInteractor {

    private final UsuarioRepository usuarioRepository;

    public UsuarioInteractor(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void registerTemporalUser(String token,String idTokenFCM, RegisterTemporalUserCallback registerTemporalUserCallback) {
        usuarioRepository.registerTemporalUser(token,idTokenFCM, registerTemporalUserCallback);
    }

    public void registerUser(String token,String name, String birthDay, String sex, String country,
                             String email, String password, String idTemporal, String registerType,
                             String idSystem, RegisterUserCallback registerTemporalUserCallback) {
        usuarioRepository.registerUser(token,name, birthDay, sex, country,
                email, password, idTemporal, registerType, idSystem, registerTemporalUserCallback);
    }

    public void login(String token,String email, String password, String idSystem, String registerType,
                      LoginCallback loginCallback) {
        usuarioRepository.login(token,email, password, idSystem, registerType, loginCallback);
    }

    public void loginSocialMedia(String token,String email, String name, String idSystem, String registerType,
                                 String idTemporal, LoginSocialMediaCallback loginSocialMediaCallback) {
        usuarioRepository.loginSocialMedia(token,email, name, idSystem, registerType, idTemporal, loginSocialMediaCallback);
    }


    public void forgotPassword(String token,String idSystem, String registerType, String email,
                                 ForgotPasswordCallback forgotPasswordCallback) {
        usuarioRepository.forgotPassword(token,email, forgotPasswordCallback);
    }

    public void reSendCode(String token,String idSystem, String registerType, String email,
                               ResendCodeCallback resendCodeCallback) {
        usuarioRepository.reSendCode(token,idSystem, registerType, email, resendCodeCallback);
    }


    public void getUser(String token,String email,String idSystem,
                           GetUserCallback getUserCallback) {
        usuarioRepository.getUser(token,email, idSystem, getUserCallback);
    }


    public void validateCode(String token,String email,String recoveryCode,
                        ValidateCodeCallback validateCodeCallback) {
        usuarioRepository.validateCode(token,email, recoveryCode, validateCodeCallback);
    }


    public void generateToken(GenerateTokenCallback generateTokenCallback) {
        usuarioRepository.generateToken( generateTokenCallback);
    }

    public void getVersionApp(String token,VersionAppCallback versionAppCallback) {
        usuarioRepository.versionApp(token, versionAppCallback);
    }

    public void updateUser(String token, String name, String birthDate, String gender, String country, String email, String password, String registerType, String idSystem,UpdateUserCallback updateUserCallback) {
        usuarioRepository.updateUser(token,name,birthDate,gender,country,email,password,registerType,idSystem, updateUserCallback);
    }

    public void routesByInterest(String token, List<String> interestList, String permanencyDays, RoutesByInterestCallback routesByInterestCallback)
    {
        usuarioRepository.routesByInterest(token,interestList,permanencyDays,routesByInterestCallback);
    }


}
