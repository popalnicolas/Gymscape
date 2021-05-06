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

    public CalendarViewModel(Application app) {
        super(app);
        repository = ExerciseRepository.getInstance(app);
    }



    public List<Workout> getAllWorkoutsList()
    {
        return repository.getAllWorkouts().getValue();
    }
}