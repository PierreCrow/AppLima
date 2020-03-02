package com.avances.applima.domain.model;


import java.io.Serializable;
import java.util.List;


public class DistritNeighborhood implements Serializable {

    private String idCloud;
    private String idDistritType;
    private String distrit;
    private String shortDescription;
    private String completeDescription;
    private List<String> imageList = null;
    private boolean active;
    private List<String> idPlaceList = null;
    private String latitude;
    private String longitude;
    private String urlVideo;
    private String tags;
    private List<String> tagList = null;

    private boolean filterShowed;

    public DistritNeighborhood(String idCloud, String idDistritType, String distrit,
                               String shortDescription, String completeDescription,List<String> imageList,
                               boolean active, List<String> idPlaceList,String latitude,
                               String longitude,String urlVideo,String tags,List<String> tagList,boolean filterShowed) {
        this.idCloud = idCloud;
        this.idDistritType = idDistritType;
        this.distrit = distrit;
        this.shortDescription = shortDescription;
        this.completeDescription = completeDescription;
        this.imageList=imageList;
        this.active = active;
        this.idPlaceList = idPlaceList;
        this.latitude=latitude;
        this.longitude=longitude;
        this.urlVideo=urlVideo;
        this.tags=tags;
        this.tagList=tagList;
        this.filterShowed=filterShowed;
    }


    public String getIdCloud() {
        return idCloud;
    }

    public void setIdCloud(String idCloud) {
        this.idCloud = idCloud;
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

    public boolean isFilterShowed() {
        return filterShowed;
    }

    public void setFilterShowed(boolean filterShowed) {
        this.filterShowed = filterShowed;
    }
}
