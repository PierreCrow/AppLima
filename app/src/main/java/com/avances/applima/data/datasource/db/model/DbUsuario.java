package com.avances.applima.data.datasource.db.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "DbUsuario")
public class DbUsuario implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idCloud;
    private String name;
    private String birthDate;
    private String sex;
    private String country;
    private String email;
    private String registerType;
    private String image;
    private String registerState;


    public DbUsuario(String idCloud, String name, String birthDate, String sex, String country,
                     String email, String registerType, String image, String registerState) {
        this.idCloud = idCloud;
        this.name = name;
        this.birthDate = birthDate;
        this.sex = sex;
        this.country = country;
        this.email = email;
        this.registerType = registerType;
        this.image = image;
        this.registerState=registerState;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegisterState() {
        return registerState;
    }

    public void setRegisterState(String registerState) {
        this.registerState = registerState;
    }
}
