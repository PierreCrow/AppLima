package com.avances.applima.domain.model;


import java.io.Serializable;

public class Interest implements Serializable {

    private String id;
    private String nameParameterValue;
    private String detailParameterValue;
    private boolean active;

    public Interest() {
    }

    public Interest(String id, String nameParameterValue, String detailParameterValue,boolean active) {
        this.id = id;
        this.nameParameterValue = nameParameterValue;
        this.detailParameterValue = detailParameterValue;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
