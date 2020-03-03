package com.avances.lima.data.datasource.db.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "DbRoute")
public class DbRoute implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idCloud;
    private String idRouteType;
    private String routeName;
    private String idUserRegister;
    private String idUserModify;
    private String registerDate;
    private String modifyDate;
    private boolean isDeleted;
    private String image;
    private String iconImage;
    private List<String> idPlaceList = null;
    private String tags;
    private List<String> tagList = null;
    private String infoghraphy;

    public DbRoute(String idCloud, String idRouteType, String routeName,
                 String idUserRegister, String idUserModify, String registerDate,
                 String modifyDate,boolean isDeleted,String image,String iconImage,
                   List<String> idPlaceList,String tags,List<String> tagList,String infoghraphy) {
        this.idCloud = idCloud;
        this.idRouteType = idRouteType;
        this.routeName = routeName;
        this.idUserRegister = idUserRegister;
        this.idUserModify = idUserModify;
        this.registerDate = registerDate;
        this.modifyDate = modifyDate;
        this.isDeleted = isDeleted;
        this.image=image;
        this.iconImage=iconImage;
        this.idPlaceList=idPlaceList;
        this.tags=tags;
        this.tagList=tagList;
        this.infoghraphy=infoghraphy;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCloud() {
        return idCloud;
    }

    public void setIdCloud(String idCloud) {
        this.idCloud = idCloud;
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

    public String getInfoghraphy() {
        return infoghraphy;
    }

    public void setInfoghraphy(String infoghraphy) {
        this.infoghraphy = infoghraphy;
    }
}
