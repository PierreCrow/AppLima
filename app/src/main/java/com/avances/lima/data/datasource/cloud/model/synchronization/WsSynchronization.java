package com.avances.lima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsSynchronization {


    @SerializedName("Data")
    @Expose
    private WsData wsData = null;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;


/*
    public WsSynchronization() {
    }


    public WsSynchronization(WsData wsData, WsResponse wsResponse) {
        this.wsData = wsData;
        this.wsResponse = wsResponse;
    }
*/
    public WsData getWsData() {
        return wsData;
    }

    public void setWsData(WsData wsData) {
        this.wsData = wsData;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
