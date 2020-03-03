package com.avances.lima.data.datasource.cloud.model.security.parameter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterValidateCode {


    @SerializedName("Correo")
    @Expose
    private String email = null;

    @SerializedName("ClaveRecuperacion")
    @Expose
    private String recoveryCode = null;

    @SerializedName("TipoRegistro")
    @Expose
    private String registerType = null;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRecoveryCode() {
        return recoveryCode;
    }

    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }
}
