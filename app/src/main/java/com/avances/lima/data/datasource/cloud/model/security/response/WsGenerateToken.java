package com.avances.lima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsGenerateToken {

    @SerializedName("Data")
    @Expose
    private String token;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
