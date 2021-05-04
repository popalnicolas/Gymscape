package com.example.gymscape.architecture.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExerciseApi {
    @GET("8dea0cee-983c-4cde-8963-be67b6247516")
    Call<List<ExerciseResponse>> getExercises();
}
