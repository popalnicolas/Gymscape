package com.example.gymscape.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "exercise_table")
public class Exercise {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int category;
    private String description;
    private String picture;
    private String position;

    @Ignore
    public Exercise(int id, String name, int category, String description, String picture, String position) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.picture = picture;
        this.position = position;
    }

    public Exercise(String name, int category, String description, String picture, String position) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.picture = picture;
        this.position = position;
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

    public String getPosition() { return position; }

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

    public void setPosition(String position)
    {
        this.position = position;
    }
}
