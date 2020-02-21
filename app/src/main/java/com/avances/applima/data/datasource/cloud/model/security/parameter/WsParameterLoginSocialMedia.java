package com.avances.applima.data.datasource.cloud.model.security.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterLoginSocialMedia {

    @SerializedName("Correo")
    @Expose
    private String email = null;

    @SerializedName("nombre")
    @Expose
    private String name = null;

    @SerializedName("IdSistema")
    @Expose
    private String idSystem = null;

    @SerializedName("TipoRegistro")
    @Expose
    private String registerType = null;

    @SerializedName("Temporal")
    @Expose
    private String idTemporal = null;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(String idTemporal) {
        this.idTemporal = idTemporal;
    }
}
