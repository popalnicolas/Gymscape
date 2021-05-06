package com.example.gymscape.ui.exercise;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.architecture.shared.ExerciseRepository;

import java.util.ArrayList;
import java.util.List;

public class SpecificExerciseViewModel extends AndroidViewModel {

    MutableLiveData<Exercise> exerciseMD;
    ExerciseRepository repository;

    public SpecificExerciseViewModel(Application app)
    {
        super(app);
        exerciseMD = new MutableLiveData<>();
        repository = ExerciseRepository.getInstance(app);
    }

    public LiveData<Exercise> getExerciseMD() {
        return exerciseMD;
    }

    public void setExerciseMD(Exercise exercise) {
        this.exerciseMD.setValue(exercise);
    }

    public void delete(final Exercise exercise)
    {
        repository.delete(exercise);
    }
}
