package com.example.gymscape.webservices;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ExerciseRepository {
    private static ExerciseRepository instance;
    private final MutableLiveData<List<Exercise>> exercisesData;

    private ExerciseRepository()
    {
        exercisesData = new MutableLiveData<>();
    }

    public static synchronized  ExerciseRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ExerciseRepository();
        }
        return instance;
    }

    public LiveData<List<Exercise>> getExercisesData()
    {
        return exercisesData;
    }

    public void setAllExercisesData()
    {
        ExerciseApi exerciseApi = ServiceGenerator.getExerciseApi();
        Call<List<ExerciseResponse>> call = exerciseApi.getExercises();

        call.enqueue(new Callback<List<ExerciseResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<ExerciseResponse>> call, Response<List<ExerciseResponse>> response) {
                ArrayList<ExerciseResponse> exerciseResponses = new ArrayList<>(response.body());
                ArrayList<Exercise> exercises = new ArrayList<>();
                for(ExerciseResponse exerciseResponse : exerciseResponses)
                {
                    exercises.add(exerciseResponse.getExercise());
                }
                exercisesData.setValue(exercises);
            }

            @Override
            public void onFailure(Call<List<ExerciseResponse>> call, Throwable t) {

            }
        });
    }
}
