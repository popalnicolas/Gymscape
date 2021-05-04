package com.example.gymscape.architecture.shared;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.architecture.database.ExerciseDAO;
import com.example.gymscape.architecture.database.ExerciseDatabase;
import com.example.gymscape.architecture.webservices.ExerciseApi;
import com.example.gymscape.architecture.webservices.ExerciseResponse;
import com.example.gymscape.architecture.webservices.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ExerciseRepository {
    /** SHARED **/
    private static ExerciseRepository instance;

    /** WEBSERVICES **/
    private final MutableLiveData<List<Exercise>> exercisesData;

    /** DATABASE **/
    private final ExerciseDAO exerciseDAO;
    private final LiveData<List<Exercise>> allExercisesDao;
    private final ExecutorService executorService;

    private ExerciseRepository(Application app)
    {
        /** WEBSERVICES **/
        exercisesData = new MutableLiveData<>();

        /** DATABASE **/
        ExerciseDatabase database = ExerciseDatabase.getInstance(app);
        exerciseDAO = database.exerciseDAO();
        allExercisesDao = exerciseDAO.getAllExercises();
        executorService = Executors.newFixedThreadPool(2);
    }

    /** SHARED **/
    public static synchronized  ExerciseRepository getInstance(Application app)
    {
        if(instance == null)
        {
            instance = new ExerciseRepository(app);
        }
        return instance;
    }

    /** WEBSERVICES **/
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
                for (ExerciseResponse exerciseResponse : exerciseResponses) {
                    exercises.add(exerciseResponse.getExercise());
                }
                exercisesData.setValue(exercises);
            }

            @Override
            public void onFailure(Call<List<ExerciseResponse>> call, Throwable t) {

            }
        });
    }
    public void setAllExercisesData(int category)
    {
        ExerciseApi exerciseApi = ServiceGenerator.getExerciseApi();
        Call<List<ExerciseResponse>> call = exerciseApi.getExercises();

        call.enqueue(new Callback<List<ExerciseResponse>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<ExerciseResponse>> call, Response<List<ExerciseResponse>> response) {
                ArrayList<ExerciseResponse> exerciseResponses = new ArrayList<>(response.body());
                ArrayList<Exercise> exercises = new ArrayList<>();
                if(category != 0) {
                    for (ExerciseResponse exerciseResponse : exerciseResponses) {
                        if(exerciseResponse.getExercise().getCategory() == category)
                            exercises.add(exerciseResponse.getExercise());
                    }
                }
                else
                {
                    for (ExerciseResponse exerciseResponse : exerciseResponses) {
                        exercises.add(exerciseResponse.getExercise());
                    }
                }
                exercisesData.setValue(exercises);
            }

            @Override
            public void onFailure(Call<List<ExerciseResponse>> call, Throwable t) {

            }
        });
    }

    /** DATABASE **/
    public LiveData<List<Exercise>> getAllExercisesDao()
    {
        return allExercisesDao;
    }

    public void deleteAll()
    {
        executorService.execute(exerciseDAO::deleteAll);
    }

    public void insert (Exercise exercise)
    {
        executorService.execute(() -> exerciseDAO.insert(exercise));
    }
    public void delete (Exercise exercise)
    {
        executorService.execute(() -> exerciseDAO.delete(exercise));
    }
}
