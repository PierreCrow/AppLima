package com.avances.applima.data.datasource.cloud.model.security.parameter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
