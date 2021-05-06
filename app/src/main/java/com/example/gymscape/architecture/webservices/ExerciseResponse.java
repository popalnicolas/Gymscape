package com.example.gymscape.architecture.webservices;

import com.example.gymscape.Model.Exercise;

public class ExerciseResponse {
    private int id;
    private String name;
    private int cat;
    private String description;
    private String picture;

    public Exercise getExercise()
    {
        return new Exercise(id, name, cat, description, picture, false);
    }
}
