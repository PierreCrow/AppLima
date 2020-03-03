package com.avances.lima.data.datasource.cloud.model.security.parameter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterLoginEmail {

    @SerializedName("Correo")
    @Expose
    private String email = null;

    @SerializedName("Clave")
    @Expose
    private String password = null;

    @SerializedName("IdSistema")
    @Expose
    private String idSystem = null;

    @SerializedName("TipoRegistro")
    @Expose
    private String registerType = null;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
}
