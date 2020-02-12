package com.avances.applima.data.datasource.cloud.model.security.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WsDataUserLoginSocialMedia {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Nombre")
    @Expose
    private String name;

    @SerializedName("FechaNacimiento")
    @Expose
    private String birthDate;

    @SerializedName("Sexo")
    @Expose
    private String sex;

    @SerializedName("Pais")
    @Expose
    private String country;

    @SerializedName("Correo")
    @Expose
    private String email;

    @SerializedName("TipoRegistro")
    @Expose
    private String registerType;


    @SerializedName("RutaImagen")
    @Expose
    private String image;


    @SerializedName("EstadoRegistro")
    @Expose
    private String registerState;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegisterState() {
        return registerState;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }
}
