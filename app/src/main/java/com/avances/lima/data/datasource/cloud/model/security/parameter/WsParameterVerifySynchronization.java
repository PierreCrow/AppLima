package com.avances.lima.data.datasource.cloud.model.security.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterVerifySynchronization {

    @SerializedName("FechaSincronizacion")
    @Expose
    private String dateLastSync = null;

    public String getDateLastSync() {
        return dateLastSync;
    }

    public void setDateLastSync(String dateLastSync) {
        this.dateLastSync = dateLastSync;
    }
}
