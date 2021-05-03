package com.example.gymscape.ui.main.exercises;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExercisesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExercisesViewModel() {
       /* mText = new MutableLiveData<>();
        mText.setValue("This is exercises fragment");*/
    }

    public LiveData<String> getText() {
        return mText;
    }
}