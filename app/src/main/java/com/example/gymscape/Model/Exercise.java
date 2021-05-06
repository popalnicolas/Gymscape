package com.example.gymscape.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "exercise_table")
public class Exercise implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int category;
    private String description;
    private String picture;
    private boolean isDatabase;

    @Ignore
    public Exercise(int id, String name, int category, String description, String picture, boolean isDatabase) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.picture = picture;
        this.isDatabase = isDatabase;
    }

    public Exercise(String name, int category, String description, String picture, boolean isDatabase) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.picture = picture;
        this.isDatabase = isDatabase;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isDatabase() {
        return isDatabase;
    }

    public void setIsDatabase(boolean isDatabase) {
        this.isDatabase = isDatabase;
    }
}
