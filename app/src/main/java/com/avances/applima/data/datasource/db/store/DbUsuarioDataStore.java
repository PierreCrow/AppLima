package com.avances.applima.data.datasource.db.store;

import android.content.Context;

import com.avances.applima.data.datasource.datastore.UsuarioDataStore;
import com.avances.applima.data.datasource.db.model.DbUsuario;
import com.avances.applima.domain.repository.RepositoryCallback;

import java.util.List;

public class DbUsuarioDataStore implements UsuarioDataStore {


    public DbUsuarioDataStore(Context context) {

    }






    @Override
    public void registerTemporalUser(String token, String idTokenFCM, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void registerUser(String token, String name, String birthDay, String sex, String country, String email, String password, String idTemporal, String registerType, String idSystem, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void login(String token, String email, String password, String idSystem, String registerType, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void getUser(String token, String email, String idSystem, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void forgotPassword(String token, String email, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void validateCode(String token, String email, String recoveryCode, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void reSendCode(String token, String idSystem, String registerType, String email, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void loginSocialMedia(String token, String email, String name, String idSystem, String registerType, String idTemporal, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void routesByInterest(String token, List<String> interestList, String permanencyDays, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void favoritesPlacesByUser(String token, String idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void generateToken(RepositoryCallback repositoryCallback) {


    }

    @Override
    public void updateUser(String token, String name, String birthDate, String gender, String country, String email, String password, String registerType, String idSystem, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void versionApp(String token, RepositoryCallback repositoryCallback) {

    }
}
