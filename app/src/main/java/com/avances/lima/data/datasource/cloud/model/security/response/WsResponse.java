package com.avances.lima.data.datasource.cloud.model.security.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsResponse {


    @SerializedName("IdOperacion")
    @Expose
    private String idOperation;

    @SerializedName("Codigo")
    @Expose
    private String code;

    @SerializedName("Mensaje")
    @Expose
    private String message;


    public String getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(String idOperation) {
        this.idOperation = idOperation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
