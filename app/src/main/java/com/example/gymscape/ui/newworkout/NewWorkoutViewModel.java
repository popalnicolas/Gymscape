package com.example.gymscape.ui.newworkout;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Exercise;

import java.util.Date;

public class NewWorkoutViewModel extends AndroidViewModel {
    MutableLiveData<Date> date;
    MutableLiveData<Integer> sets;
    MutableLiveData<Integer> weight;
    MutableLiveData<Exercise> exercise;

    public NewWorkoutViewModel(Application application)
    {
        super(application);
        date = new MutableLiveData<>();
        sets = new MutableLiveData<>();
        weight = new MutableLiveData<>();
        exercise = new MutableLiveData<>();
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

    public LiveData<Integer> getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.setValue(weight);
    }

    public LiveData<Exercise> getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise.setValue(exercise);
    }
}
