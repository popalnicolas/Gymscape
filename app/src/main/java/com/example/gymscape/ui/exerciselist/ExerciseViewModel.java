package com.example.gymscape.ui.exerciselist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.architecture.shared.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    ExerciseRepository exerciseRepository;

    public ExerciseViewModel(Application app)
    {
        super(app);
        exerciseRepository = ExerciseRepository.getInstance(app);
    }

    LiveData<List<Exercise>> getExercises()
    {
        return exerciseRepository.getExercisesData();
    }

    List<Exercise> getExerciseByCategory(int category)
    {
        if(category == 0)
            return exerciseRepository.getExercisesData().getValue();

        ArrayList<Exercise> catExercises = new ArrayList<>();
        for(Exercise exercise : exerciseRepository.getExercisesData().getValue())
        {
            if(exercise.getCategory() == category)
                catExercises.add(exercise);
        }
        return new ArrayList<Exercise>(catExercises);
    }

    public void setExercise(int category)
    {
        exerciseRepository.setAllExercisesData(category);
    }

    /** DATABASE **/
    public LiveData<List<Exercise>> getAllExercisesDAO()
    {
        return exerciseRepository.getAllExercisesDao();
    }

    public void insert(final Exercise exercise)
    {
        exerciseRepository.insert(exercise);
    }

    public void delete(final Exercise exercise)
    {
        exerciseRepository.delete(exercise);
    }

    public void deleteAll()
    {
        exerciseRepository.deleteAll();
    }
}
