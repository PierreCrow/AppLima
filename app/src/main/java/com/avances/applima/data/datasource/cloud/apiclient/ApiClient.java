package com.avances.applima.data.datasource.cloud.apiclient;

import com.avances.applima.presentation.utils.Constants;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static ApiInterface retro;
    private static ApiInterface apiClientInterface;

    public static ApiInterface getApiClientt(String token) {

        Gson gson = new GsonBuilder().serializeNulls().create();


        if (apiClientInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.URLS.URL_BASE)
                    .client(getClient(token))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            apiClientInterface = retrofit.create(ApiInterface.class);
        }
        return apiClientInterface;
    }

    private static OkHttpClient getClient(String Token) {

      //     HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //   logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .addInterceptor(new BasicAuthInterceptor(Token))
               // .addInterceptor(logging)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        return client;
    }




    public static ApiInterface getApiClient(String token) {


        //  TokenAuthenticator tokenAuthenticator = new TokenAuthenticator();

        String url;

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new BasicAuthInterceptor(token))
                .addNetworkInterceptor(new StethoInterceptor())
                //     .authenticator(tokenAuthenticator)
                .build();

        //  okHttpClient.interceptors().add(interceptor);

        //  if(retrofit == null){

    /*    if (vouchers) {
            url = Constantes.URLS.URL_BASE_VOUCHERS;
        } else {
            url = Constantes.URLS.URL_BASE;
        }
*/
        url = Constants.URLS.URL_BASE;
        // Gson gson = new GsonBuilder().serializeNulls().create();
        Gson gson = new GsonBuilder()
                // .setLenient()
                // .serializeNulls()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //   }
        return retrofit.create(ApiInterface.class);
    }


}
