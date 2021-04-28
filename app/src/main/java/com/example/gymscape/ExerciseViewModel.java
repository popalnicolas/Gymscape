package com.example.gymscape;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.webservices.ExerciseRepository;

import java.util.List;

public class ExerciseViewModel extends ViewModel {

    ExerciseRepository exerciseRepository;

    public ExerciseViewModel()
    {
        exerciseRepository = ExerciseRepository.getInstance();
    }

    LiveData<Exercise> getExercise()
    {
        return exerciseRepository.getExerciseData();
    }

    Exercise getExerciseInClass()
    {
        return exerciseRepository.getExerciseData().getValue();
    }

    LiveData<List<Exercise>> getAllExercises()
    {
        return exerciseRepository.getAllExerciseData();
    }

    public void setExercise(int id)
    {
        exerciseRepository.setExerciseData(id);
    }

    public void setAllExercises(int cat)
    {
        exerciseRepository.setAllExerciseData(cat);
    }
}
