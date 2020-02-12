package com.avances.applima.data.datasource.cloud.model.security.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsDataFavoritePlace {


    @SerializedName("ListaFavorito")
    @Expose
    private List<String> placesId = null;

    public List<String> getPlacesId() {
        return placesId;
    }

    public void setPlacesId(List<String> placesId) {
        this.placesId = placesId;
    }
}
