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
    private final MutableLiveData<Exercise> exerciseData;
    private final MutableLiveData<List<Exercise>> allExerciseData;

    private ExerciseRepository()
    {
        exerciseData = new MutableLiveData<>();
        allExerciseData = new MutableLiveData<>();
    }

    public static synchronized  ExerciseRepository getInstance()
    {
        if(instance == null)
        {
            instance = new ExerciseRepository();
        }
        return instance;
    }

    public LiveData<Exercise> getExerciseData()
    {
        return exerciseData;
    }

    public LiveData<List<Exercise>> getAllExerciseData()
    {
        return allExerciseData;
    }

    public void setExerciseData(int id)
    {
        ExerciseApi exerciseApi = ServiceGenerator.getExerciseApi();
        Call<ExerciseResponse> call = exerciseApi.getExercise(id);
        call.enqueue(new Callback<ExerciseResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ExerciseResponse> call, Response<ExerciseResponse> response) {
                exerciseData.setValue(response.body().getExercise());
            }

            @Override
            public void onFailure(Call<ExerciseResponse> call, Throwable t) {
            }
        });
    }

    public void setAllExerciseData(int cat)
    {
        ExerciseApi exerciseApi = ServiceGenerator.getExerciseApi();
        Call<List<ExerciseResponse>> call = exerciseApi.getAllExercises(cat);
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
                allExerciseData.setValue(exercises);
            }

            @Override
            public void onFailure(Call<List<ExerciseResponse>> call, Throwable t) {

            }
        });
    }
}
