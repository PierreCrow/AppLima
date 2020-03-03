package com.avances.lima.data.datasource.cloud.model.security.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WsDataRoute {


    @SerializedName("ListaRutas")
    @Expose
    private List<String> routesIds = null;

    public List<String> getRoutesIds() {
        return routesIds;
    }

    public void setRoutesIds(List<String> routesIds) {
        this.routesIds = routesIds;
    }
}
