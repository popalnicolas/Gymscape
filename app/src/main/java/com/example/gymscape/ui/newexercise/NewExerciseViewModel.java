package com.example.gymscape.ui.newexercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.architecture.shared.ExerciseRepository;

public class NewExerciseViewModel extends AndroidViewModel {

    private final ExerciseRepository repository;

    public NewExerciseViewModel(@NonNull Application application) {
        super(application);
        repository = ExerciseRepository.getInstance(application);
    }

    public void insert(final Exercise exercise)
    {
        repository.insert(exercise);
    }
}
