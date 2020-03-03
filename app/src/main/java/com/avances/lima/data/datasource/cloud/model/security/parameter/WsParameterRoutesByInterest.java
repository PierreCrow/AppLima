package com.avances.lima.data.datasource.cloud.model.security.parameter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsParameterRoutesByInterest {



    @SerializedName("ListaInteres")
    @Expose
    private List<String> interestsId = null;

    @SerializedName("DiaPermanencia")
    @Expose
    private String permanencyDays = null;

    public List<String> getInterestsId() {
        return interestsId;
    }

    public void setInterestsId(List<String> interestsId) {
        this.interestsId = interestsId;
    }

    public String getPermanencyDays() {
        return permanencyDays;
    }

    public void setPermanencyDays(String permanencyDays) {
        this.permanencyDays = permanencyDays;
    }
}
