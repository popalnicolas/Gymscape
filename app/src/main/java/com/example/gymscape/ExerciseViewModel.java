package com.example.gymscape;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.webservices.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

public class ExerciseViewModel extends ViewModel {

    ExerciseRepository exerciseRepository;

    public ExerciseViewModel()
    {
        exerciseRepository = ExerciseRepository.getInstance();
    }

    LiveData<List<Exercise>> getExercises()
    {
        return exerciseRepository.getExercisesData();
    }

    LiveData<List<Exercise>> getExerciseByCategory(int category)
    {
        if(category == 0)
            return exerciseRepository.getExercisesData();

        ArrayList<Exercise> catExercises = new ArrayList<>();
        for(Exercise exercise : exerciseRepository.getExercisesData().getValue())
        {
            if(exercise.getCategory() == category)
                catExercises.add(exercise);
        }
        return new MutableLiveData<List<Exercise>>(catExercises);
    }

    public void setExercise()
    {
        exerciseRepository.setAllExercisesData();
    }
}
