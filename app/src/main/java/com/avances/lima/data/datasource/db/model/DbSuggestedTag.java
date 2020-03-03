package com.avances.lima.data.datasource.db.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DbSuggestedTag")
public class DbSuggestedTag {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;


    public DbSuggestedTag() {
    }

    public DbSuggestedTag(String name) {
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
