package com.avances.lima.domain.model;


import java.io.Serializable;

public class FilterTag implements Serializable {

    private String name;
    private Boolean showed;
    private Boolean added;


    public FilterTag() {
    }

    public FilterTag(String name,Boolean showed,Boolean added) {
        this.name = name;
        this.showed=showed;
        this.added=added;
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

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }
}
