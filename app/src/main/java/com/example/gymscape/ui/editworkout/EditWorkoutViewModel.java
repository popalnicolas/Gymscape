package com.example.gymscape.ui.editworkout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Workout;
import com.example.gymscape.architecture.shared.ExerciseRepository;

import java.util.Date;

public class EditWorkoutViewModel extends AndroidViewModel {

    ExerciseRepository repository;

    MutableLiveData<Integer> setsCount;
    MutableLiveData<Date> date;

    public EditWorkoutViewModel(Application application) {
        super(application);
        repository = ExerciseRepository.getInstance(application);
        setsCount = new MutableLiveData<>();
        date = new MutableLiveData<>();
    }

    public LiveData<Integer> getSetsCount() {
        return setsCount;
    }

    public void setSetsCount(int setsCount) {
        this.setsCount.setValue(setsCount);
    }

    public LiveData<Date> getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date.setValue(date);
    }

    public void delete(Workout workout)
    {
        repository.deleteWorkout(workout);
    }

    public void update(Workout workout)
    {
        repository.updateWorkout(workout);
    }
}
