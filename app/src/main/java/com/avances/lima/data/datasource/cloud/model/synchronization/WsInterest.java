package com.avances.lima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsInterest {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("NombreParametroValor")
    @Expose
    private String nameParameterValue;

    @SerializedName("DetalleParametroValor")
    @Expose
    private String detailParameterValue;

    @SerializedName("Orden")
    @Expose
    private int order;

    @SerializedName("Activo")
    @Expose
    private boolean active;

    @SerializedName("ActivoNombre")
    @Expose
    private String nameActive;

    @SerializedName("IdParametro")
    @Expose
    private String idParameter;

    @SerializedName("NombreParametro")
    @Expose
    private String nameParameter;

    public WsInterest() {
    }

    public WsInterest(String id, String nameParameterValue, String detailParameterValue,
                      int order, boolean active, String nameActive, String idParameter, String nameParameter) {
        this.id = id;
        this.nameParameterValue = nameParameterValue;
        this.detailParameterValue = detailParameterValue;
        this.order = order;
        this.nameActive = nameActive;
        this.idParameter = idParameter;
        this.nameParameter = nameParameter;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNameActive() {
        return nameActive;
    }

    public void setNameActive(String nameActive) {
        this.nameActive = nameActive;
    }

    public String getIdParameter() {
        return idParameter;
    }

    public void setIdParameter(String idParameter) {
        this.idParameter = idParameter;
    }

    public String getNameParameter() {
        return nameParameter;
    }

    public void setNameParameter(String nameParameter) {
        this.nameParameter = nameParameter;
    }
}
