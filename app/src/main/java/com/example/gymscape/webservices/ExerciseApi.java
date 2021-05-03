package com.example.gymscape.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExerciseApi {
    @GET("8dea0cee-983c-4cde-8963-be67b6247516")
    Call<List<ExerciseResponse>> getExercises();
}
