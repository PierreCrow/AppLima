package com.avances.lima.domain.model;


import java.io.Serializable;

public class SuggestedTag implements Serializable {

    private int id;
    private String name;


    public SuggestedTag() {
    }

    public SuggestedTag(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
