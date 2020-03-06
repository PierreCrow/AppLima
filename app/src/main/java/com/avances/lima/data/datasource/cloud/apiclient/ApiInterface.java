package com.avances.lima.data.datasource.cloud.apiclient;

import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterForgotPassword;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterLoginEmail;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterLoginSocialMedia;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterReSendCode;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterRegisterUser;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterRoutesByInterest;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterTemporalUser;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterUpdateUser;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterUploadPicture;
import com.avances.lima.data.datasource.cloud.model.security.parameter.WsParameterValidateCode;
import com.avances.lima.data.datasource.cloud.model.security.response.WsFavoritesPlacesByUser;
import com.avances.lima.data.datasource.cloud.model.security.response.WsForgotPassword;
import com.avances.lima.data.datasource.cloud.model.security.response.WsGenerateToken;
import com.avances.lima.data.datasource.cloud.model.security.response.WsGetUser;
import com.avances.lima.data.datasource.cloud.model.security.response.WsLogin;
import com.avances.lima.data.datasource.cloud.model.security.response.WsLoginSocialMedia;
import com.avances.lima.data.datasource.cloud.model.security.response.WsRegisterTemporalUser;
import com.avances.lima.data.datasource.cloud.model.security.response.WsRegisterUser;
import com.avances.lima.data.datasource.cloud.model.security.response.WsResendCode;
import com.avances.lima.data.datasource.cloud.model.security.response.WsRoutesByInterest;
import com.avances.lima.data.datasource.cloud.model.security.response.WsUpdateUser;
import com.avances.lima.data.datasource.cloud.model.security.response.WsValidateCode;
import com.avances.lima.data.datasource.cloud.model.security.response.WsVersionApp;
import com.avances.lima.data.datasource.cloud.model.synchronization.WsSynchronization;
import com.avances.lima.presentation.utils.Constants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET(Constants.URLS.TOTAL_SYNCHRONIZATION)
    Call<WsSynchronization> getTotalSynchronization();

    @GET(Constants.URLS.GENERATE_TOKEN)
    Call<WsGenerateToken> generateToken();

    @GET(Constants.URLS.VERSION_APP)
    Call<WsVersionApp> getVersionApp();

    @POST(Constants.URLS.REGISTER_TEMPORAL_USER)
    Call<WsRegisterTemporalUser> registerTemporalUser(@Body WsParameterTemporalUser wsInsertAsset);

    @POST(Constants.URLS.REGISTER_USER)
    Call<WsRegisterUser> registerUser(@Body WsParameterRegisterUser wsParameterRegisterUser);

    @POST(Constants.URLS.RESEND_CODE)
    Call<WsResendCode> resendCode(@Body WsParameterReSendCode wsParameterReSendCode);

    @POST(Constants.URLS.LOGIN)
    Call<WsLogin> login(@Body WsParameterLoginEmail wsParameterLoginEmail);

    @POST(Constants.URLS.GET_USER)
    Call<WsGetUser> getUser(String email, String idSystem);

    @POST(Constants.URLS.FORGOT_PASSWORD)
    Call<WsForgotPassword> forgotPassword(@Body WsParameterForgotPassword wsParameterForgotPassword);

    @POST(Constants.URLS.VALIDATE_CODE)
    Call<WsValidateCode> validateCode(@Body WsParameterValidateCode wsParameterValidateCode);

    @POST(Constants.URLS.LOGIN_SOCIAL_MEDIA)
    Call<WsLoginSocialMedia> loginSocialMedia(@Body WsParameterLoginSocialMedia wsParameterLoginSocialMedia);

    @POST(Constants.URLS.ROUTES_BY_INTEREST)
    Call<WsRoutesByInterest> routesByInterest(@Body WsParameterRoutesByInterest wsParameterRoutesByInterest);

    @POST(Constants.URLS.FAVORITES_BY_USER)
    Call<WsFavoritesPlacesByUser> favoritesPlacesByUser(String idUser);

    @POST(Constants.URLS.UPDATE_USER)
    Call<WsUpdateUser> updateUser(@Body WsParameterUpdateUser wsParameterUpdateUser);

    @POST(Constants.URLS.UPLOAD_PICTURE)
    Call<WsUpdateUser> uploadPicture(@Body WsParameterUploadPicture wsParameterUploadPicture);

}
