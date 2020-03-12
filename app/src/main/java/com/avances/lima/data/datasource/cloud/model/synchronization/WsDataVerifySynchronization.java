package com.avances.lima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsDataVerifySynchronization {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("FechaSincronizacion")
    @Expose
    private String dateLastSynchronization;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateLastSynchronization() {
        return dateLastSynchronization;
    }

    public void setDateLastSynchronization(String dateLastSynchronization) {
        this.dateLastSynchronization = dateLastSynchronization;
    }
}
