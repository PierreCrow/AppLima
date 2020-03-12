package com.avances.lima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsVerifySynchronization {

    @SerializedName("Data")
    @Expose
    private WsDataVerifySynchronization wsDataVerifySynchronization;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;

    public WsDataVerifySynchronization getWsDataVerifySynchronization() {
        return wsDataVerifySynchronization;
    }

    public void setWsDataVerifySynchronization(WsDataVerifySynchronization wsDataVerifySynchronization) {
        this.wsDataVerifySynchronization = wsDataVerifySynchronization;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
