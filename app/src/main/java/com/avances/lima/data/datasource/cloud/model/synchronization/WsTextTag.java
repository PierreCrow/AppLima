package com.avances.lima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsTextTag {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Nombre")
    @Expose
    private String name;


}
