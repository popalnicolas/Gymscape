package com.example.gymscape.architecture.webservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ExerciseApi exerciseApi;

    public static ExerciseApi getExerciseApi()
    {
        if(exerciseApi == null)
        {
            exerciseApi = new Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ExerciseApi.class);
        }
        return exerciseApi;
    }
}
