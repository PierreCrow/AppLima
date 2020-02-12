package com.avances.applima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsDistritNeighborhood {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("IdTipoDistrito")
    @Expose
    private String idDistritType;

    @SerializedName("Distrito")
    @Expose
    private String distrit;

    @SerializedName("DescripcionBreve")
    @Expose
    private String shortDescription;

    @SerializedName("DescripcionCompleta")
    @Expose
    private String completeDescription;

    @SerializedName("Activo")
    @Expose
    private boolean active;

    @SerializedName("Latitud")
    @Expose
    private String latitude;

    @SerializedName("Longitud")
    @Expose
    private String longitude;

    @SerializedName("Imagen")
    @Expose
    private List<String> imageList = null;

    @SerializedName("ListaLugares")
    @Expose
    private List<String> idPlaceList = null;

    @SerializedName("RutaVideo")
    @Expose
    private String urlVideo;

    @SerializedName("Tags")
    @Expose
    private String tags;

    @SerializedName("ListaTags")
    @Expose
    private List<String> tagList = null;





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdDistritType() {
        return idDistritType;
    }

    public void setIdDistritType(String idDistritType) {
        this.idDistritType = idDistritType;
    }

    public String getDistrit() {
        return distrit;
    }

    public void setDistrit(String distrit) {
        this.distrit = distrit;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCompleteDescription() {
        return completeDescription;
    }

    public void setCompleteDescription(String completeDescription) {
        this.completeDescription = completeDescription;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getIdPlaceList() {
        return idPlaceList;
    }

    public void setIdPlaceList(List<String> idPlaceList) {
        this.idPlaceList = idPlaceList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
