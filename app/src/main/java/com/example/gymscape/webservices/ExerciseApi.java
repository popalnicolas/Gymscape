package com.example.gymscape.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExerciseApi {
    @GET("gymscape/index.php")
    Call<ExerciseResponse> getExercise(@Query("exercise") int id);
    @GET("gymscape/index.php")
    Call<List<ExerciseResponse>> getAllExercises(@Query("category") int id);
    //@POST("gymscape/")
}
