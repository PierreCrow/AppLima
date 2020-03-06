package com.avances.lima.data.datasource.cloud.model.security.parameter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsParameterUploadPicture {

    @SerializedName("NombreArchivo")
    @Expose
    private String imageName = null;

    @SerializedName("Archivo")
    @Expose
    private String image = null;


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
