package com.avances.lima.domain.repository;

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
import com.avances.lima.interactor.usuario.UploadImageCallback;
import com.avances.lima.interactor.usuario.ValidateCodeCallback;
import com.avances.lima.interactor.usuario.VersionAppCallback;

import java.util.List;


public interface UsuarioRepository {

    void registerTemporalUser(String token, String idTokenFCM, RegisterTemporalUserCallback registerTemporalUserCallback);

    void registerUser(String token, String name, String birthDay, String sex, String country, String email, String password, String idTemporal, String registerType, String idSystem, RegisterUserCallback registerUserCallback);

    void login(String token, String email, String password, String idSystem, String registerType, LoginCallback loginCallback);

    void getUser(String token, String email, String idSystem, GetUserCallback getUserCallback);

    void forgotPassword(String token, String email, ForgotPasswordCallback forgotPasswordCallback);

    void validateCode(String token, String email, String recoveryCode, ValidateCodeCallback validateCodeCallback);

    void reSendCode(String token, String idSystem, String registerType, String email, ResendCodeCallback resendCodeCallback);

    void loginSocialMedia(String token, String email, String name, String idSystem, String registerType, String idTemporal, LoginSocialMediaCallback loginSocialMediaCallback);

    void routesByInterest(String token, List<String> interestList, String permanencyDays, RoutesByInterestCallback routesByInterestCallback);

    void favoritesPlacesByUser(String token, String idUser, RepositoryCallback repositoryCallback);

    void generateToken(GenerateTokenCallback generateTokenCallback);

    void updateUser(String token, String name, String birthDate, String gender, String country, String email, String password, String registerType, String idSystem, UpdateUserCallback updateUserCallback);

    void versionApp(String token, VersionAppCallback versionAppCallback);

    void uploadPicture(String token, String imageName, String image, UploadImageCallback uploadImageCallback);
}
