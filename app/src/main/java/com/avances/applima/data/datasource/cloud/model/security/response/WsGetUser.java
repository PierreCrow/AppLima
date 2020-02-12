package com.avances.applima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsGetUser {

    @SerializedName("Data")
    @Expose
    private WsDataUser wsDataUser;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;

    public WsDataUser getWsDataUser() {
        return wsDataUser;
    }

    public void setWsDataUser(WsDataUser wsDataUser) {
        this.wsDataUser = wsDataUser;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
