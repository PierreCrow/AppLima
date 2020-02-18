package com.avances.applima.data.datasource.cloud.model.security.parameter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterRegisterUser {


    @SerializedName("Nombre")
    @Expose
    private String name = null;

    @SerializedName("FechaNacimiento")
    @Expose
    private String birthDate = null;

    @SerializedName("Sexo")
    @Expose
    private String gonder = null;


    @SerializedName("Pais")
    @Expose
    private String country = null;

    @SerializedName("Correo")
    @Expose
    private String email = null;

    @SerializedName("Clave")
    @Expose
    private String password = null;

   /* @SerializedName("Temporal")
    @Expose
    private String idTemporal = null;
*/

    @SerializedName("TipoRegistro")
    @Expose
    private String registerType = null;


    @SerializedName("IdSistema")
    @Expose
    private String idSystem = null;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGonder() {
        return gonder;
    }

    public void setGonder(String gonder) {
        this.gonder = gonder;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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
/*
    public String getIdTemporal() {
        return idTemporal;
    }

    public void setIdTemporal(String idTemporal) {
        this.idTemporal = idTemporal;
    }
*/
    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getIdSystem() {
        return idSystem;
    }

    public void setIdSystem(String idSystem) {
        this.idSystem = idSystem;
    }
}
