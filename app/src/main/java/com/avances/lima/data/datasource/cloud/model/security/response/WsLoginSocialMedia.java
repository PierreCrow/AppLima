package com.avances.lima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsLoginSocialMedia {

    @SerializedName("Data")
    @Expose
    private WsDataUserLoginSocialMedia wsDataUserLoginSocialMedia;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;


    public WsDataUserLoginSocialMedia getWsDataUserLoginSocialMedia() {
        return wsDataUserLoginSocialMedia;
    }

    public void setWsDataUserLoginSocialMedia(WsDataUserLoginSocialMedia wsDataUserLoginSocialMedia) {
        this.wsDataUserLoginSocialMedia = wsDataUserLoginSocialMedia;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
