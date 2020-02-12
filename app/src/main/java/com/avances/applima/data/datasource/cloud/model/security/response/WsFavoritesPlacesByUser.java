package com.avances.applima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WsFavoritesPlacesByUser {

    @SerializedName("Data")
    @Expose
    private WsDataFavoritePlace wsDataFavoritePlace;

    @SerializedName("Response")
    @Expose
    private WsResponse wsResponse = null;


    public WsDataFavoritePlace getWsDataFavoritePlace() {
        return wsDataFavoritePlace;
    }

    public void setWsDataFavoritePlace(WsDataFavoritePlace wsDataFavoritePlace) {
        this.wsDataFavoritePlace = wsDataFavoritePlace;
    }

    public WsResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(WsResponse wsResponse) {
        this.wsResponse = wsResponse;
    }
}
