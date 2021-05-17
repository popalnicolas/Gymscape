package com.example.gymscape.ui.main.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.gymscape.architecture.shared.ExerciseRepository;

public class ProfileViewModel extends AndroidViewModel {

    ExerciseRepository repository;

    public ProfileViewModel(Application application)
    {
        super(application);
        repository = ExerciseRepository.getInstance(application);
    }

    public void deleteAll()
    {
        repository.deleteAllExercises();
        repository.deleteAllWorkouts();
    }
}