package com.example.gymscape.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExerciseApi {
    @GET("681233d0-255b-4500-9451-758c8c7624df")
    Call<List<ExerciseResponse>> getExercises();
}
