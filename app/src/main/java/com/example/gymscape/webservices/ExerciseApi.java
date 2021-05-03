package com.example.gymscape.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExerciseApi {
    @GET("601d66ee-1cf0-4328-a1e6-a8a230c0509c")
    Call<List<ExerciseResponse>> getExercises();
}
