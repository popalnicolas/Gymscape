package com.example.gymscape.Model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "workout_table")
public class Workout implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String exerciseName;
    private int category;
    private int date;
    private int sets;
    private int weight;

    @Ignore
    public Workout(int id, String exerciseName, int category, int date, int sets, int weight) {
        this.id = id;
        this.exerciseName = exerciseName;
        this.category = category;
        this.date = date;
        this.sets = sets;
        this.weight = weight;
    }

    @Ignore
    public Workout()
    {

    }

    public Workout(String exerciseName, int category, int date, int sets, int weight) {
        this.exerciseName = exerciseName;
        this.category = category;
        this.date = date;
        this.sets = sets;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Workout{" +
                ", exerciseName='" + exerciseName + '\'' +
                ", category=" + category +
                ", date=" + date +
                ", sets=" + sets +
                ", weight=" + weight +
                '}';
    }
}
