package com.avances.lima.data.repository;

import com.avances.lima.data.datasource.datastore.UsuarioDataStore;
import com.avances.lima.data.datasource.datastore.UsuarioDataStoreFactory;
import com.avances.lima.domain.model.Usuario;
import com.avances.lima.domain.repository.RepositoryCallback;
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
import com.avances.lima.interactor.usuario.ValidateCodeCallback;
import com.avances.lima.interactor.usuario.VersionAppCallback;
import com.avances.lima.presentation.utils.Constants;

import java.util.List;

public class UsuarioDataRepository implements UsuarioRepository {

    private final UsuarioDataStoreFactory usuarioDataStoreFactory;

    public UsuarioDataRepository(UsuarioDataStoreFactory usuarioDataStoreFactory) {
        this.usuarioDataStoreFactory = usuarioDataStoreFactory;
    }


    @Override
    public void registerTemporalUser(String token,String idTokenFCM, RegisterTemporalUserCallback registerTemporalUserCallback) {

        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.registerTemporalUser(token,idTokenFCM, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                registerTemporalUserCallback.onRegisterTemporalUserError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String idCloud  = (String) object;
                registerTemporalUserCallback.onRegisterTemporalUserSuccess(idCloud);
            }
        });
    }

    @Override
    public void registerUser(String token,String name, String birthDay, String sex, String country, String email, String password, String idTemporal, String registerType, String idSystem, RegisterUserCallback registerUserCallback) {

        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.registerUser(token,name,birthDay,sex,country,email,password,idTemporal,registerType,idSystem, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                registerUserCallback.onRegisterUserError(message);
            }

            @Override
            public void onSuccess(Object object) {
                Usuario usuario  = (Usuario) object;
                registerUserCallback.onRegisterUserSuccess(usuario);
            }
        });
    }

    @Override
    public void login(String token,String email, String password, String idSystem, String registerType, LoginCallback loginCallback) {
        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.login(token,email,password,idSystem,registerType, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                loginCallback.onLoginError(message);
            }

            @Override
            public void onSuccess(Object object) {
                Usuario usuario  = (Usuario) object;
                loginCallback.onLoginSuccess(usuario);
            }
        });
    }

    @Override
    public void getUser(String token,String email, String idSystem, GetUserCallback getUserCallback) {
        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.getUser(token,email,idSystem, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                getUserCallback.onGetUserError(message);
            }

            @Override
            public void onSuccess(Object object) {
                Usuario usuario  = (Usuario) object;
                getUserCallback.onGetUserSuccess(usuario);
            }
        });
    }

    @Override
    public void forgotPassword(String token,String email, ForgotPasswordCallback forgotPasswordCallback) {
        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.forgotPassword(token,email, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                forgotPasswordCallback.onForgotPasswordError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String message  = (String) object;
                forgotPasswordCallback.onForgotPasswordSuccess(message);
            }
        });
    }

    @Override
    public void validateCode(String token,String email, String recoveryCode, ValidateCodeCallback validateCodeCallback) {
        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.validateCode(token,email,recoveryCode, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                validateCodeCallback.onValidateCodeError(message);
            }

            @Override
            public void onSuccess(Object object) {
                Usuario usuario  = (Usuario) object;
                validateCodeCallback.onValidateCodeSuccess(usuario);
            }
        });
    }

    @Override
    public void reSendCode(String token,String idSystem, String registerType, String email, ResendCodeCallback resendCodeCallback) {
        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.reSendCode(token,idSystem,registerType,email, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                resendCodeCallback.onResendCodeError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String message  = (String) object;
                resendCodeCallback.onResendCodeSuccess(message);
            }
        });
    }

    @Override
    public void loginSocialMedia(String token,String email, String name, String idSystem, String registerType, String idTemporal, LoginSocialMediaCallback loginSocialMediaCallback) {
        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.loginSocialMedia(token,email, name, idSystem, registerType, idTemporal, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                loginSocialMediaCallback.onLoginSocialMediaError(message);
            }

            @Override
            public void onSuccess(Object object) {
                Usuario usuario  = (Usuario) object;
                loginSocialMediaCallback.onLoginSocialMediaSuccess(usuario);
            }
        });
    }

    @Override
    public void routesByInterest(String token,List<String> interestList, String permanencyDays, RoutesByInterestCallback routesByInterestCallback) {

        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.routesByInterest(token,interestList, permanencyDays, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                routesByInterestCallback.onRoutesByInterestError(message);
            }

            @Override
            public void onSuccess(Object object) {
                List<String> idRoutes  = (List<String>) object;
                routesByInterestCallback.onRoutesByInterestSuccess(idRoutes);
            }
        });
    }

    @Override
    public void favoritesPlacesByUser(String token,String idUser, RepositoryCallback repositoryCallback) {

    }

    @Override
    public void generateToken(GenerateTokenCallback generateTokenCallback) {
        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.generateToken(new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                generateTokenCallback.onGenerateTokenError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String token  = (String) object;
                generateTokenCallback.onGenerateTokenSuccess(token);
            }
        });
    }

    @Override
    public void updateUser(String token, String name, String birthDate, String gender, String country, String email, String password, String registerType, String idSystem, UpdateUserCallback updateUserCallback) {

        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.updateUser(token,name,birthDate,gender,country,email,password,registerType,idSystem,new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                updateUserCallback.onUpdateUserError(message);
            }

            @Override
            public void onSuccess(Object object) {
                Usuario usuario  = (Usuario) object;
                updateUserCallback.onUpdateUserSuccess(usuario);
            }
        });
    }

    @Override
    public void versionApp(String token, VersionAppCallback versionAppCallback) {

        final UsuarioDataStore usuarioDataStore=usuarioDataStoreFactory.create(Constants.STORE.CLOUD);
        usuarioDataStore.versionApp(token, new RepositoryCallback() {
            @Override
            public void onError(Object object) {
                String message = "";
                if (object != null) {
                    message = object.toString();
                }
                versionAppCallback.onVersionAppError(message);
            }

            @Override
            public void onSuccess(Object object) {
                String version  = (String) object;
                versionAppCallback.onVersionAppSuccess(version);
            }
        });
    }
}
