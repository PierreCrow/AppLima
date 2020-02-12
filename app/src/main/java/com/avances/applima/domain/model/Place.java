package com.avances.applima.domain.model;



import java.io.Serializable;
import java.util.List;


public class Place implements Serializable {

    private String id;
    private String tittle;
    private String resume;
    private String detail;
    private String address;
    private String webPage;
    private String phone;
    private String idDistritNeighborhood;
    private String lat;
    private String lng;
    private boolean active;
    private boolean isDeleted;
    private String textTags;
    private List<String> textTagsList = null;
    private List<String> interviewed;
    private List<String> imageList = null;
    private boolean favorite;

    public Place(String id, String tittle, String resume,
                 String detail, String address, String webPage,
                 String phone, String idDistritNeighborhood, String lat,
                 String lng, boolean active, boolean isDeleted,String textTags,
                 List<String> textTagsList,List<String> interviewed,List<String> imageList,boolean favorite) {
        this.id = id;
        this.tittle = tittle;
        this.resume = resume;
        this.detail = detail;
        this.address = address;
        this.webPage = webPage;
        this.phone = phone;
        this.idDistritNeighborhood = idDistritNeighborhood;
        this.lat = lat;
        this.lng = lng;
        this.active = active;
        this.isDeleted = isDeleted;
        this.textTags=textTags;
        this.textTagsList = textTagsList;
        this.interviewed=interviewed;
        this.imageList =imageList;
        this.favorite=favorite;
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

    public String getIdDistritNeighborhood() {
        return idDistritNeighborhood;
    }

    public void setIdDistritNeighborhood(String idDistritNeighborhood) {
        this.idDistritNeighborhood = idDistritNeighborhood;
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

    public List<String> getTextTagsList() {
        return textTagsList;
    }

    public void setTextTagsList(List<String> textTagsList) {
        this.textTagsList = textTagsList;
    }

    public String getTextTags() {
        return textTags;
    }

    public void setTextTags(String textTags) {
        this.textTags = textTags;
    }

    public List<String> getInterviewed() {
        return interviewed;
    }

    public void setInterviewed(List<String> interviewed) {
        this.interviewed = interviewed;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
