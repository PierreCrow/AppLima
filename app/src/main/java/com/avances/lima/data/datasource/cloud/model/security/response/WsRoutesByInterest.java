package com.avances.lima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WsRoutesByInterest {

    @SerializedName("Data")
    @Expose
    private WsDataRoute wsDataRoute;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;

    public WsDataRoute getWsDataRoute() {
        return wsDataRoute;
    }

    public void setWsDataRoute(WsDataRoute wsDataRoute) {
        this.wsDataRoute = wsDataRoute;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
