package com.avances.applima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsVersionApp {

    @SerializedName("Data")
    @Expose
    private WsDataVersionApp wsDataVersionApp;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;

    public WsDataVersionApp getWsDataVersionApp() {
        return wsDataVersionApp;
    }

    public void setWsDataVersionApp(WsDataVersionApp wsDataVersionApp) {
        this.wsDataVersionApp = wsDataVersionApp;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
