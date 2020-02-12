package com.avances.applima.data.datasource.cloud.store;

import com.avances.applima.data.datasource.cloud.apiclient.ApiClient;
import com.avances.applima.data.datasource.cloud.apiclient.ApiInterface;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterForgotPassword;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterLoginEmail;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterLoginSocialMedia;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterReSendCode;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterRegisterUser;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterTemporalUser;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterUpdateUser;
import com.avances.applima.data.datasource.cloud.model.security.parameter.WsParameterValidateCode;
import com.avances.applima.data.datasource.cloud.model.security.response.WsFavoritesPlacesByUser;
import com.avances.applima.data.datasource.cloud.model.security.response.WsForgotPassword;
import com.avances.applima.data.datasource.cloud.model.security.response.WsGenerateToken;
import com.avances.applima.data.datasource.cloud.model.security.response.WsGetUser;
import com.avances.applima.data.datasource.cloud.model.security.response.WsLogin;
import com.avances.applima.data.datasource.cloud.model.security.response.WsLoginSocialMedia;
import com.avances.applima.data.datasource.cloud.model.security.response.WsRegisterTemporalUser;
import com.avances.applima.data.datasource.cloud.model.security.response.WsRegisterUser;
import com.avances.applima.data.datasource.cloud.model.security.response.WsResendCode;
import com.avances.applima.data.datasource.cloud.model.security.response.WsRoutesByInterest;
import com.avances.applima.data.datasource.cloud.model.security.response.WsUpdateUser;
import com.avances.applima.data.datasource.cloud.model.security.response.WsValidateCode;
import com.avances.applima.data.datasource.datastore.UsuarioDataStore;
import com.avances.applima.data.mapper.UsuarioDataMapper;
import com.avances.applima.domain.model.Usuario;
import com.avances.applima.domain.repository.RepositoryCallback;
import com.avances.applima.presentation.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CloudUsuarioDataStore implements UsuarioDataStore {
    private static final String TAG = "CloudUsuarioDataStore";

    private ApiInterface apiInterface;
    UsuarioDataMapper usuarioDataMapper;

    public CloudUsuarioDataStore() {
        // apiInterface = ApiClient.getApiClient("");
        usuarioDataMapper = new UsuarioDataMapper();
    }


    @Override
    public void registerTemporalUser(String token, String idTokenFCM, RepositoryCallback repositoryCallback) {

        WsParameterTemporalUser wsParameterTemporalUser = new WsParameterTemporalUser();
        wsParameterTemporalUser.setIdTokenFCM(idTokenFCM);

        Call<WsRegisterTemporalUser> call = ApiClient.getApiClient(token).registerTemporalUser(wsParameterTemporalUser);
        call.enqueue(new Callback<WsRegisterTemporalUser>() {
            @Override
            public void onResponse(Call<WsRegisterTemporalUser> call, Response<WsRegisterTemporalUser> response) {

                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsRegisterTemporalUser wsRegisterTemporalUser = response.body();
                        if (wsRegisterTemporalUser.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            repositoryCallback.onSuccess(wsRegisterTemporalUser.getWsDataUserTemporal().getIdTemporal());
                        } else {
                            repositoryCallback.onError(wsRegisterTemporalUser.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsRegisterTemporalUser> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });


    }

    @Override
    public void registerUser(String token, String name, String birthDay, String sex, String country, String email, String password, String idTemporal, String registerType, String idSystem, RepositoryCallback repositoryCallback) {

        WsParameterRegisterUser wsParameterRegisterUser = new WsParameterRegisterUser();
        wsParameterRegisterUser.setName(name);
        wsParameterRegisterUser.setBirthDate(birthDay);
        wsParameterRegisterUser.setGonder(sex);
        wsParameterRegisterUser.setCountry(country);
        wsParameterRegisterUser.setEmail(email);
        wsParameterRegisterUser.setPassword(password);
        wsParameterRegisterUser.setIdTemporal(idTemporal);
        wsParameterRegisterUser.setRegisterType(registerType);
        wsParameterRegisterUser.setIdSystem(idSystem);

        Call<WsRegisterUser> call = ApiClient.getApiClient(token).registerUser(wsParameterRegisterUser);
        call.enqueue(new Callback<WsRegisterUser>() {
            @Override
            public void onResponse(Call<WsRegisterUser> call, Response<WsRegisterUser> response) {


                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsRegisterUser wsRegisterUser = response.body();
                        if (wsRegisterUser.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            Usuario usuario = usuarioDataMapper.transformToEntity(wsRegisterUser.getWsDataUser());
                            repositoryCallback.onSuccess(usuario);
                        } else {
                            repositoryCallback.onError(wsRegisterUser.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsRegisterUser> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void login(String token, String email, String password, String idSystem, String registerType, RepositoryCallback repositoryCallback) {

        WsParameterLoginEmail wsParameterLoginEmail = new WsParameterLoginEmail();
        wsParameterLoginEmail.setEmail(email);
        wsParameterLoginEmail.setPassword(password);
        wsParameterLoginEmail.setIdSystem(idSystem);
        wsParameterLoginEmail.setRegisterType(registerType);

        Call<WsLogin> call = ApiClient.getApiClient(token).login(wsParameterLoginEmail);
        call.enqueue(new Callback<WsLogin>() {
            @Override
            public void onResponse(Call<WsLogin> call, Response<WsLogin> response) {

                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsLogin wsLogin = response.body();
                        if (wsLogin.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            Usuario usuario = usuarioDataMapper.transformToEntity(wsLogin.getWsDataUser());
                            repositoryCallback.onSuccess(usuario);
                        } else {
                            repositoryCallback.onError(wsLogin.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsLogin> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getUser(String token, String email, String idSystem, RepositoryCallback repositoryCallback) {

        Call<WsGetUser> call = ApiClient.getApiClient(token).getUser(email, idSystem);
        call.enqueue(new Callback<WsGetUser>() {
            @Override
            public void onResponse(Call<WsGetUser> call, Response<WsGetUser> response) {

                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsGetUser wsGetUser = response.body();
                        if (wsGetUser.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            Usuario usuario = usuarioDataMapper.transformToEntity(wsGetUser.getWsDataUser());
                            repositoryCallback.onSuccess(usuario);
                        } else {
                            repositoryCallback.onError(wsGetUser.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsGetUser> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void forgotPassword(String token, String email, RepositoryCallback repositoryCallback) {

        WsParameterForgotPassword wsParameterForgotPassword = new WsParameterForgotPassword();
        wsParameterForgotPassword.setEmail(email);

        Call<WsForgotPassword> call = ApiClient.getApiClient(token).forgotPassword(wsParameterForgotPassword);
        call.enqueue(new Callback<WsForgotPassword>() {
            @Override
            public void onResponse(Call<WsForgotPassword> call, Response<WsForgotPassword> response) {


                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsForgotPassword wsForgotPassword = response.body();
                        if (wsForgotPassword.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            repositoryCallback.onSuccess(wsForgotPassword.getWsResponse().getMessage());
                        } else {
                            repositoryCallback.onError(wsForgotPassword.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsForgotPassword> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }


    @Override
    public void validateCode(String token, String email, String recoveryCode, RepositoryCallback repositoryCallback) {

        WsParameterValidateCode wsParameterValidateCode = new WsParameterValidateCode();
        wsParameterValidateCode.setEmail(email);
        wsParameterValidateCode.setRecoveryCode(recoveryCode);

        Call<WsValidateCode> call = ApiClient.getApiClient(token).validateCode(wsParameterValidateCode);
        call.enqueue(new Callback<WsValidateCode>() {
            @Override
            public void onResponse(Call<WsValidateCode> call, Response<WsValidateCode> response) {


                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsValidateCode wsValidateCode = response.body();
                        if (wsValidateCode.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            Usuario usuario = usuarioDataMapper.transformToEntity(wsValidateCode.getWsDataUser());
                            repositoryCallback.onSuccess(usuario);
                        } else {
                            repositoryCallback.onError(wsValidateCode.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }
            }

            @Override
            public void onFailure(Call<WsValidateCode> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void reSendCode(String token, String idSystem, String registerType, String email, RepositoryCallback repositoryCallback) {

        WsParameterReSendCode wsParameterReSendCode = new WsParameterReSendCode();
        wsParameterReSendCode.setEmail(email);
        wsParameterReSendCode.setIdSystem(idSystem);
        wsParameterReSendCode.setRegisterType(registerType);

        Call<WsResendCode> call = ApiClient.getApiClient(token).resendCode(wsParameterReSendCode);
        call.enqueue(new Callback<WsResendCode>() {
            @Override
            public void onResponse(Call<WsResendCode> call, Response<WsResendCode> response) {


                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsResendCode wsResendCode = response.body();
                        if (wsResendCode.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            repositoryCallback.onSuccess(wsResendCode.getWsResponse().getMessage());
                        } else {
                            repositoryCallback.onError(wsResendCode.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsResendCode> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void loginSocialMedia(String token, String email, String name, String idSystem, String registerType, String idTemporal, RepositoryCallback repositoryCallback) {

        WsParameterLoginSocialMedia wsParameterLoginSocialMedia = new WsParameterLoginSocialMedia();
        wsParameterLoginSocialMedia.setEmail(email);
        wsParameterLoginSocialMedia.setName(name);
        wsParameterLoginSocialMedia.setIdSystem(idSystem);
        wsParameterLoginSocialMedia.setRegisterType(registerType);
        wsParameterLoginSocialMedia.setIdTemporal(idTemporal);

        Call<WsLoginSocialMedia> call = ApiClient.getApiClient(token).loginSocialMedia(wsParameterLoginSocialMedia);
        call.enqueue(new Callback<WsLoginSocialMedia>() {
            @Override
            public void onResponse(Call<WsLoginSocialMedia> call, Response<WsLoginSocialMedia> response) {


                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsLoginSocialMedia wsLoginSocialMedia = response.body();
                        if (wsLoginSocialMedia.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            Usuario usuario = usuarioDataMapper.transformFromSocialMediaToEntity(wsLoginSocialMedia.getWsDataUserLoginSocialMedia());
                            repositoryCallback.onSuccess(usuario);
                        } else {
                            repositoryCallback.onError(wsLoginSocialMedia.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsLoginSocialMedia> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void routesByInterest(String token, List<String> interestList, String permanencyDays, RepositoryCallback repositoryCallback) {

        Call<WsRoutesByInterest> call = ApiClient.getApiClient(token).routesByInterest(interestList, permanencyDays);
        call.enqueue(new Callback<WsRoutesByInterest>() {
            @Override
            public void onResponse(Call<WsRoutesByInterest> call, Response<WsRoutesByInterest> response) {


                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsRoutesByInterest wsRoutesByInterest = response.body();
                        if (wsRoutesByInterest.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            List<String> routesId = wsRoutesByInterest.getWsDataRoute().getRoutesIds();
                            repositoryCallback.onSuccess(routesId);
                        } else {
                            repositoryCallback.onError(wsRoutesByInterest.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsRoutesByInterest> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void favoritesPlacesByUser(String token, String idUser, RepositoryCallback repositoryCallback) {

        Call<WsFavoritesPlacesByUser> call = ApiClient.getApiClient(token).favoritesPlacesByUser(idUser);
        call.enqueue(new Callback<WsFavoritesPlacesByUser>() {
            @Override
            public void onResponse(Call<WsFavoritesPlacesByUser> call, Response<WsFavoritesPlacesByUser> response) {


                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsFavoritesPlacesByUser wsLoginSocialMedia = response.body();
                        if (wsLoginSocialMedia.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            List<String> routesId = wsLoginSocialMedia.getWsDataFavoritePlace().getPlacesId();
                            repositoryCallback.onSuccess(routesId);
                        } else {
                            repositoryCallback.onError(wsLoginSocialMedia.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsFavoritesPlacesByUser> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });
    }

    @Override
    public void generateToken(RepositoryCallback repositoryCallback) {


        Call<WsGenerateToken> call = ApiClient.getApiClient("").generateToken();
        call.enqueue(new Callback<WsGenerateToken>() {
            @Override
            public void onResponse(Call<WsGenerateToken> call, Response<WsGenerateToken> response) {

                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsGenerateToken wsGenerateToken = response.body();
                        if (wsGenerateToken.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            repositoryCallback.onSuccess(wsGenerateToken.getToken());
                        } else {
                            repositoryCallback.onError(wsGenerateToken.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsGenerateToken> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });

    }

    @Override
    public void updateUser(String token, String name, String birthDate, String gender, String country, String email, String password, String registerType, String idSystem, RepositoryCallback repositoryCallback) {


        WsParameterUpdateUser wsParameterUpdateUser= new WsParameterUpdateUser();
        wsParameterUpdateUser.setName(name);
        wsParameterUpdateUser.setBirthDate(birthDate);
        wsParameterUpdateUser.setGonder(gender);
        wsParameterUpdateUser.setCountry(country);
        wsParameterUpdateUser.setEmail(email);
        wsParameterUpdateUser.setPassword(password);
        wsParameterUpdateUser.setRegisterType(registerType);
        wsParameterUpdateUser.setIdSystem(idSystem);

        Call<WsUpdateUser> call = ApiClient.getApiClient(token).updateUser(wsParameterUpdateUser);
        call.enqueue(new Callback<WsUpdateUser>() {
            @Override
            public void onResponse(Call<WsUpdateUser> call, Response<WsUpdateUser> response) {

                if (response.code() == 200) {
                    if (response.body() != null) {
                        WsUpdateUser wsUpdateUser = response.body();
                        if (wsUpdateUser.getWsResponse().getCode().equals(Constants.RESPONSE_CODES.SUCCESS)) {
                            Usuario usuario = usuarioDataMapper.transformToEntity(wsUpdateUser.getWsDataUser());
                            repositoryCallback.onSuccess(usuario);
                        } else {
                            repositoryCallback.onError(wsUpdateUser.getWsResponse().getMessage());
                        }

                    } else {
                        repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                    }
                } else {
                    repositoryCallback.onError(Constants.RESPONSE_MESSAGES.ERROR);
                }

            }

            @Override
            public void onFailure(Call<WsUpdateUser> call, Throwable t) {
                repositoryCallback.onError(t.getMessage());
            }
        });


    }
}
