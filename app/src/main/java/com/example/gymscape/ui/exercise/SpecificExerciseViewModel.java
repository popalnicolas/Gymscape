package com.example.gymscape.ui.exercise;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.webservices.ExerciseRepository;

import java.util.List;

public class SpecificExerciseViewModel extends ViewModel {

    ExerciseRepository exerciseRepository;

    public SpecificExerciseViewModel()
    {
        exerciseRepository = ExerciseRepository.getInstance();
    }

    LiveData<List<Exercise>> getExercises()
    {
        return exerciseRepository.getExercisesData();
    }

    Exercise getExerciseById(int exerciseId)
    {
        for(Exercise exercise : exerciseRepository.getExercisesData().getValue())
        {
            if(exercise.getId() == exerciseId)
                return exercise;
        }
        return null;
    }

    public void setExercise()
    {
        exerciseRepository.setAllExercisesData();
    }
}
