package com.avances.applima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsPlace {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Titulo")
    @Expose
    private String tittle;

    @SerializedName("Resumen")
    @Expose
    private String resume;

    @SerializedName("Detalle")
    @Expose
    private String detail;

    @SerializedName("Direccion")
    @Expose
    private String address;

    @SerializedName("PaginaWeb")
    @Expose
    private String webPage;

    @SerializedName("Telefono")
    @Expose
    private String phone;

    @SerializedName("IdDistritoBarrio")
    @Expose
    private String IdDistritoBarrio;

    @SerializedName("Latitud")
    @Expose
    private String lat;

    @SerializedName("Longitud")
    @Expose
    private String lng;

    @SerializedName("Activo")
    @Expose
    private boolean active;

    @SerializedName("EsEliminado")
    @Expose
    private boolean isDeleted;

    @SerializedName("TextoTags")
    @Expose
    private String textTags;

    @SerializedName("TxtTags")
    @Expose
    private List<String> testTagList = null;

    @SerializedName("Entrevistado")
    @Expose
    private WsInterviewed wsInterviewed = null;

    @SerializedName("ListaImagenes")
    @Expose
    private List<String> imageList = null;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdDistritoBarrio() {
        return IdDistritoBarrio;
    }

    public void setIdDistritoBarrio(String idDistritoBarrio) {
        IdDistritoBarrio = idDistritoBarrio;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<String> getTestTagList() {
        return testTagList;
    }

    public void setTestTagList(List<String> testTagList) {
        this.testTagList = testTagList;
    }

    public String getTextTags() {
        return textTags;
    }

    public void setTextTags(String textTags) {
        this.textTags = textTags;
    }

    public WsInterviewed getWsInterviewed() {
        return wsInterviewed;
    }

    public void setWsInterviewed(WsInterviewed wsInterviewed) {
        this.wsInterviewed = wsInterviewed;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
