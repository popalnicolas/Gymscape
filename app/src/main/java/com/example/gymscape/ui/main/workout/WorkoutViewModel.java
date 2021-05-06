package com.example.gymscape.ui.main.workout;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymscape.Model.Workout;
import com.example.gymscape.architecture.shared.ExerciseRepository;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {

    private ExerciseRepository repository;
    MutableLiveData<Boolean> isWorkout;

    public WorkoutViewModel(Application app) {
        super(app);
        repository = ExerciseRepository.getInstance(app);
        isWorkout = new MutableLiveData<>();
        isWorkout.setValue(false);
    }

    public void setIsWorkout(boolean isWorkout) {
        this.isWorkout.setValue(isWorkout);
    }

    public LiveData<Boolean> isWorkout() {
        return isWorkout;
    }

    public LiveData<List<Workout>> getAllWorkouts()
    {
        return repository.getAllWorkouts();
    }
}