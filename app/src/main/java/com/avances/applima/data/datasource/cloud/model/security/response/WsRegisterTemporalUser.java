package com.avances.applima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsRegisterTemporalUser {

    @SerializedName("Data")
    @Expose
    private WsDataUserTemporal wsDataUserTemporal;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;

    public WsDataUserTemporal getWsDataUserTemporal() {
        return wsDataUserTemporal;
    }

    public void setWsDataUserTemporal(WsDataUserTemporal wsDataUserTemporal) {
        this.wsDataUserTemporal = wsDataUserTemporal;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
