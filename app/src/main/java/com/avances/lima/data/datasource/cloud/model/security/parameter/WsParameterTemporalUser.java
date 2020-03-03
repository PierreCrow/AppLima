package com.avances.lima.data.datasource.cloud.model.security.parameter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterTemporalUser {


    @SerializedName("IdToken")
    @Expose
    private String idTokenFCM = null;

    public String getIdTokenFCM() {
        return idTokenFCM;
    }

    public void setIdTokenFCM(String idTokenFCM) {
        this.idTokenFCM = idTokenFCM;
    }
}
