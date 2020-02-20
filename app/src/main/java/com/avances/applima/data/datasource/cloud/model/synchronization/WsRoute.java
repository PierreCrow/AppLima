package com.avances.applima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsRoute {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("IdTipoRuta")
    @Expose
    private String idRouteType;

    @SerializedName("NombreRuta")
    @Expose
    private String routeName;

    @SerializedName("IdUsuarioRegistro")
    @Expose
    private String idUserRegister;

    @SerializedName("IdUsuarioModifico")
    @Expose
    private String idUserModify;

    @SerializedName("FechaRegistro")
    @Expose
    private String registerDate;

    @SerializedName("FechaModifico")
    @Expose
    private String modifyDate;

    @SerializedName("EsEliminado")
    @Expose
    private boolean isDeleted;

    @SerializedName("Imagen")
    @Expose
    private String image;

    @SerializedName("ImagenIcono")
    @Expose
    private String iconImage;

    @SerializedName("ListaLugares")
    @Expose
    private List<String> idPlaceList = null;


    @SerializedName("Tags")
    @Expose
    private String tags;

    @SerializedName("ListaTags")
    @Expose
    private List<String> tagList = null;


    @SerializedName("ImagenInfografia")
    @Expose
    private String infoghraphy;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdRouteType() {
        return idRouteType;
    }

    public void setIdRouteType(String idRouteType) {
        this.idRouteType = idRouteType;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getIdUserRegister() {
        return idUserRegister;
    }

    public void setIdUserRegister(String idUserRegister) {
        this.idUserRegister = idUserRegister;
    }

    public String getIdUserModify() {
        return idUserModify;
    }

    public void setIdUserModify(String idUserModify) {
        this.idUserModify = idUserModify;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIconImage() {
        return iconImage;
    }

    public void setIconImage(String iconImage) {
        this.iconImage = iconImage;
    }

    public List<String> getIdPlaceList() {
        return idPlaceList;
    }

    public void setIdPlaceList(List<String> idPlaceList) {
        this.idPlaceList = idPlaceList;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getInfoghraphy() {
        return infoghraphy;
    }

    public void setInfoghraphy(String infoghraphy) {
        this.infoghraphy = infoghraphy;
    }
}
