package com.avances.lima.data.datasource.cloud.apiclient;

import com.google.common.net.HttpHeaders;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BasicAuthInterceptor implements Interceptor {

    private String token;

    public  BasicAuthInterceptor(String token) {
        this.token = token;
    }



    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
    /*    Request request = chain.request();
        Request authenticatedRequest = request.newBuilder()
                .header("Authorization","Bearer " + token).build();
        return chain.proceed(authenticatedRequest);
*/


        Request original = chain.request();
        // Request customization: add request headers
        Request.Builder requestBuilder =original.newBuilder()
                .header(HttpHeaders.AUTHORIZATION, token)
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
              //  .addHeader(HttpHeaders.CONTENT_TYPE, "charset=utf-8")
                .method(original.method(), original.body());

        Request request = requestBuilder.build();
        return chain.proceed(request);

    }
}

