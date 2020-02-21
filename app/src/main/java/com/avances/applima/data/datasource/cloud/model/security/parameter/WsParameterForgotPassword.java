package com.avances.applima.data.datasource.cloud.model.security.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterForgotPassword {

    @SerializedName("Correo")
    @Expose
    private String email = null;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
