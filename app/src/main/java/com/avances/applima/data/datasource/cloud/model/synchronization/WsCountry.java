package com.avances.applima.data.datasource.cloud.model.synchronization;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WsCountry {


    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("IdParametro")
    @Expose
    private String idParameter;

    @SerializedName("NombreParametroValor")
    @Expose
    private String nameParameterValue;

    @SerializedName("DetalleParametroValor")
    @Expose
    private String detailParameterValue;

    @SerializedName("Activo")
    @Expose
    private boolean active;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(String idParameter) {
        this.idParameter = idParameter;
    }

    public String getNameParameterValue() {
        return nameParameterValue;
    }

    public void setNameParameterValue(String nameParameterValue) {
        this.nameParameterValue = nameParameterValue;
    }

    public String getDetailParameterValue() {
        return detailParameterValue;
    }

    public void setDetailParameterValue(String detailParameterValue) {
        this.detailParameterValue = detailParameterValue;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
