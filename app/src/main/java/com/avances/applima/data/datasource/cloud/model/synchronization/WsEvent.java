package com.avances.applima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WsEvent {

    @SerializedName("Id")
    @Expose
    private String id;

    @SerializedName("Titulo")
    @Expose
    private String tittle;

    @SerializedName("DescripcionBreve")
    @Expose
    private String shortDecription;

    @SerializedName("Descripcion")
    @Expose
    private String description;

    @SerializedName("FechaEventoInicio")
    @Expose
    private String eventDate;

    @SerializedName("FechaEventoFin")
    @Expose
    private String eventTime;

    @SerializedName("IdTipoEvento")
    @Expose
    private String idEventType;

    @SerializedName("Activo")
    @Expose
    private boolean active;

    @SerializedName("EsEliminado")
    @Expose
    private boolean isDeleted;


    @SerializedName("Imagen")
    @Expose
    private String image;
    @SerializedName("FormatoFechaEventoInicio")
    @Expose
    private String startDate;
    @SerializedName("FormatoFechaEventoFin")
    @Expose
    private String finalDate;


    public WsEvent() {
    }


    public WsEvent(String id, String tittle, String shortDecription,
                   String description, String eventDate, String eventTime, String idEventType,
                   boolean active, boolean isDeleted) {
        this.id = id;
        this.tittle = tittle;
        this.shortDecription = shortDecription;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.idEventType = idEventType;
        this.active = active;
        this.isDeleted = isDeleted;
    }

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

    public String getShortDecription() {
        return shortDecription;
    }

    public void setShortDecription(String shortDecription) {
        this.shortDecription = shortDecription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getIdEventType() {
        return idEventType;
    }

    public void setIdEventType(String idEventType) {
        this.idEventType = idEventType;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }
}
