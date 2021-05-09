package com.example.gymscape.ui.main.calendar;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymscape.Model.Workout;
import com.example.gymscape.architecture.shared.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

public class CalendarViewModel extends AndroidViewModel {

    private ExerciseRepository repository;
    MutableLiveData<Boolean> noExercises;

    public CalendarViewModel(Application app) {
        super(app);
        repository = ExerciseRepository.getInstance(app);
        noExercises = new MutableLiveData<>();
        noExercises.setValue(false);
    }

    public LiveData<Boolean> getExerciseText()
    {
        return  noExercises;
    }

    public void setExerciseText(boolean exerciseText)
    {
        noExercises.setValue(exerciseText);
    }


    public LiveData<List<Workout>> getAllWorkouts()
    {
        return repository.getAllWorkouts();
    }

    public List<Workout> getAllWorkoutsList()
    {
        return repository.getAllWorkouts().getValue();
    }
}