package com.avances.applima.data.datasource.cloud.model.security.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WsDataUserTemporal {


    @SerializedName("Id")
    @Expose
    private String idTemporal;

    public String getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(String idTemporal) {
        this.idTemporal = idTemporal;
    }
}
