package com.example.gymscape.ui.exercise;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.architecture.shared.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

public class SpecificExerciseViewModel extends AndroidViewModel {

    ExerciseRepository exerciseRepository;

    public SpecificExerciseViewModel(Application app)
    {
        super(app);
        exerciseRepository = ExerciseRepository.getInstance(app);
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

    /** DATABASE **/

    LiveData<List<Exercise>> getExercisesDAO()
    {
        return exerciseRepository.getAllExercisesDao();
    }

    Exercise getExerciseDAOById(int exerciseId)
    {
        for(Exercise exercise : exerciseRepository.getAllExercisesDao().getValue())
        {
            if(exercise.getId() == exerciseId)
                return exercise;
        }
        return null;
    }
}
