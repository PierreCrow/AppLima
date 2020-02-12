package com.avances.applima.data.datasource.datastore;

import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public interface UsuarioDataStore {

    void registerTemporalUser(String token,String idTokenFCM, RepositoryCallback repositoryCallback);

    void registerUser(String token,String name, String birthDay, String sex, String country, String email, String password, String idTemporal, String registerType, String idSystem, RepositoryCallback repositoryCallback);

    void login(String token,String email, String password, String idSystem, String registerType, RepositoryCallback repositoryCallback);

    void getUser(String token,String email, String idSystem, RepositoryCallback repositoryCallback);

    void forgotPassword(String token,String email, RepositoryCallback repositoryCallback);

    void validateCode(String token,String email, String recoveryCode, RepositoryCallback repositoryCallback);

    void reSendCode(String token,String idSystem, String registerType, String email, RepositoryCallback repositoryCallback);

    void loginSocialMedia(String token,String email, String name, String idSystem, String registerType, String idTemporal, RepositoryCallback repositoryCallback);

    void routesByInterest(String token,List<String> interestList, String permanencyDays, RepositoryCallback repositoryCallback);

    void favoritesPlacesByUser(String token,String idUser, RepositoryCallback repositoryCallback);

    void generateToken(RepositoryCallback repositoryCallback);

    void updateUser(String token,String name,String birthDate,String gender,String country,String email,String password,String registerType,String idSystem,RepositoryCallback repositoryCallback);
}
