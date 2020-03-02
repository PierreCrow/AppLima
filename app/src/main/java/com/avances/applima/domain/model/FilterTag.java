package com.avances.applima.domain.model;


import java.io.Serializable;

public class FilterTag implements Serializable {

    private String name;
    private Boolean showed;


    public FilterTag() {
    }

    public FilterTag(String name,Boolean showed) {
        this.name = name;
        this.showed=showed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getShowed() {
        return showed;
    }

    public void setShowed(Boolean showed) {
        this.showed = showed;
    }
}
