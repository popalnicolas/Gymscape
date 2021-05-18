package com.example.gymscape.ui.newexercise;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.architecture.shared.ExerciseRepository;

public class NewExerciseViewModel extends AndroidViewModel {

    private final ExerciseRepository repository;

    MutableLiveData<Bitmap> exercisePhoto;
    MutableLiveData<Boolean> pictureTaken;

    public NewExerciseViewModel(@NonNull Application application) {
        super(application);
        repository = ExerciseRepository.getInstance(application);
        exercisePhoto = new MutableLiveData<>();
        pictureTaken = new MutableLiveData<>(false);
    }

    public void insert(final Exercise exercise)
    {
        repository.insert(exercise);
    }

    public LiveData<Bitmap> getExercisePhoto() {
        return exercisePhoto;
    }

    public void setExercisePhoto(Bitmap exercisePhoto) {
        this.exercisePhoto.setValue(exercisePhoto);
    }

    public LiveData<Boolean> getPictureTaken() {
        return pictureTaken;
    }

    public void setPictureTaken(boolean pictureTaken) {
        this.pictureTaken.setValue(pictureTaken);
    }
}
