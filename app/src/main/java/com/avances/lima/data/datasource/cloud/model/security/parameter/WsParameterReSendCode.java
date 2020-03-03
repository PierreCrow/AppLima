package com.avances.lima.data.datasource.cloud.model.security.parameter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterReSendCode {


    @SerializedName("IdSistema")
    @Expose
    private String idSystem = null;

    @SerializedName("TipoRegistro")
    @Expose
    private String registerType = null;

    @SerializedName("Correo")
    @Expose
    private String email = null;


    public String getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(String idSystem) {
        this.idSystem = idSystem;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
