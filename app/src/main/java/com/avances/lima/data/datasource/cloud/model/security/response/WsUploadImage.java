package com.avances.lima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsUploadImage {

    @SerializedName("Data")
    @Expose
    private WsDataUploadImage wsDataUploadImage;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;

    public WsDataUploadImage getWsDataUploadImage() {
        return wsDataUploadImage;
    }

    public void setWsDataUploadImage(WsDataUploadImage wsDataUploadImage) {
        this.wsDataUploadImage = wsDataUploadImage;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
