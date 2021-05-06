package com.example.gymscape.ui.newworkout;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.Model.Workout;
import com.example.gymscape.architecture.shared.ExerciseRepository;

import java.util.Date;

public class NewWorkoutViewModel extends AndroidViewModel {
    MutableLiveData<Date> date;
    MutableLiveData<Integer> sets;
    MutableLiveData<Exercise> exercise;
    ExerciseRepository repository;

    public NewWorkoutViewModel(Application application)
    {
        super(application);
        date = new MutableLiveData<>();
        sets = new MutableLiveData<>();
        sets.setValue(1);
        exercise = new MutableLiveData<>();
        repository = ExerciseRepository.getInstance(application);
    }

    public LiveData<Date> getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date.setValue(date);
    }

    public LiveData<Integer> getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets.setValue(sets);
    }

    public LiveData<Exercise> getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise.setValue(exercise);
    }

    public void insert(final Workout workout)
    {
        repository.insertWorkout(workout);
    }
}
