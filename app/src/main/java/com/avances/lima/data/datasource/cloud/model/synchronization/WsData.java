package com.avances.lima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WsData {


    @SerializedName("ListaInteres")
    @Expose
    private List<WsInterest> wsInterests = null;

    @SerializedName("ListaEventos")
    @Expose
    private List<WsEvent> wsEvents = null;

    @SerializedName("ListaLugares")
    @Expose
    private List<WsPlace> wsPlaces = null;

    @SerializedName("ListaDistritoBarrios")
    @Expose
    private List<WsDistritNeighborhood> wsDistritNeighborhoods = null;

    @SerializedName("ListaRuta")
    @Expose
    private List<WsRoute> wsRoutes = null;

    @SerializedName("ListaPais")
    @Expose
    private List<WsParameterValue> wsCountries = null;

    @SerializedName("ListaSexo")
    @Expose
    private List<WsParameterValue> wsGenders = null;

    @SerializedName("ListaPermanencia")
    @Expose
    private List<WsParameterValue> wsPermanencyDay = null;

    @SerializedName("ListaTagsSugeridos")
    @Expose
    private List<String> suggestedTags = null;

    public WsData() {
    }


    public WsData(List<WsInterest> wsInterests, List<WsEvent> wsEvents,
                  List<WsPlace> wsPlaces, List<WsDistritNeighborhood> wsDistritNeighborhoods,
                  List<WsRoute> wsRoutes, List<WsParameterValue> wsCountries, List<WsParameterValue> wsGenders,
                  List<WsParameterValue> wsPermanencyDay, List<String> suggestedTags) {
        this.wsInterests = wsInterests;
        this.wsEvents = wsEvents;
        this.wsPlaces = wsPlaces;
        this.wsDistritNeighborhoods = wsDistritNeighborhoods;
        this.wsRoutes = wsRoutes;
        this.wsCountries = wsCountries;
        this.wsGenders = wsGenders;
        this.wsPermanencyDay = wsPermanencyDay;
        this.suggestedTags = suggestedTags;

    }

    public List<WsInterest> getWsInterests() {
        return wsInterests;
    }

    public void setWsInterests(List<WsInterest> wsInterests) {
        this.wsInterests = wsInterests;
    }

    public List<WsEvent> getWsEvents() {
        return wsEvents;
    }

    public void setWsEvents(List<WsEvent> wsEvents) {
        this.wsEvents = wsEvents;
    }

    public List<WsPlace> getWsPlaces() {
        return wsPlaces;
    }

    public void setWsPlaces(List<WsPlace> wsPlaces) {
        this.wsPlaces = wsPlaces;
    }

    public List<WsDistritNeighborhood> getWsDistritNeighborhoods() {
        return wsDistritNeighborhoods;
    }

    public void setWsDistritNeighborhoods(List<WsDistritNeighborhood> wsDistritNeighborhoods) {
        this.wsDistritNeighborhoods = wsDistritNeighborhoods;
    }

    public List<WsRoute> getWsRoutes() {
        return wsRoutes;
    }

    public void setWsRoutes(List<WsRoute> wsRoutes) {
        this.wsRoutes = wsRoutes;
    }

    public List<WsParameterValue> getWsCountries() {
        return wsCountries;
    }

    public void setWsCountries(List<WsParameterValue> wsCountries) {
        this.wsCountries = wsCountries;
    }

    public List<WsParameterValue> getWsGenders() {
        return wsGenders;
    }

    public void setWsGenders(List<WsParameterValue> wsGenders) {
        this.wsGenders = wsGenders;
    }

    public List<String> getSuggestedTags() {
        return suggestedTags;
    }

    public void setSuggestedTags(List<String> suggestedTags) {
        this.suggestedTags = suggestedTags;
    }

    public List<WsParameterValue> getWsPermanencyDay() {
        return wsPermanencyDay;
    }

    public void setWsPermanencyDay(List<WsParameterValue> wsPermanencyDay) {
        this.wsPermanencyDay = wsPermanencyDay;
    }
}
