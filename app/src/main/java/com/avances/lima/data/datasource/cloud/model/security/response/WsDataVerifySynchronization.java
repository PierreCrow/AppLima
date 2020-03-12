package com.avances.lima.data.datasource.cloud.model.security.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WsDataVerifySynchronization {

    @SerializedName("Sincronizacion")
    @Expose
    private boolean sync;

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
