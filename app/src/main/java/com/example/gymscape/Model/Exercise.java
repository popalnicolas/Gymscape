package com.example.gymscape.Model;

public class Exercise {
    private int id;
    private String name;
    private int category;
    private String description;
    private String picture;

    public Exercise(int id, String name, int category, String description, String picture) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.picture = picture;
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
}
