package com.avances.lima.data.datasource.db.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DbGender")
public class DbGender {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idCloud;
    private String nameParameterValue;
    private String detailParameterValue;
    private boolean active;

    public DbGender() {
    }

    public DbGender(String idCloud, String nameParameterValue, String detailParameterValue, boolean active) {
        this.idCloud = idCloud;
        this.nameParameterValue = nameParameterValue;
        this.detailParameterValue = detailParameterValue;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCloud() {
        return idCloud;
    }

    public void setIdCloud(String idCloud) {
        this.idCloud = idCloud;
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
