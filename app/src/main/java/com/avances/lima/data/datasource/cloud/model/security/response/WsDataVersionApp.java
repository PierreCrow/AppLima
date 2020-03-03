package com.avances.lima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsDataVersionApp {


    @SerializedName("NumeroVersion")
    @Expose
    private String version = null;

    @SerializedName("EnlaceIOS")
    @Expose
    private String linkIOS = null;

    @SerializedName("EnlaceAndroid")
    @Expose
    private String linkAndroid = null;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLinkIOS() {
        return linkIOS;
    }

    public void setLinkIOS(String linkIOS) {
        this.linkIOS = linkIOS;
    }

    public String getLinkAndroid() {
        return linkAndroid;
    }

    public void setLinkAndroid(String linkAndroid) {
        this.linkAndroid = linkAndroid;
    }
}
