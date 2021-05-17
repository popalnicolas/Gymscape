package com.example.gymscape.architecture.shared;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.Model.Workout;
import com.example.gymscape.architecture.database.ExerciseDAO;
import com.example.gymscape.architecture.database.ExerciseDatabase;
import com.example.gymscape.architecture.database.WorkoutDAO;
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

    private final WorkoutDAO workoutDAO;
    private final MutableLiveData<List<Workout>> workoutByDate;
    private final LiveData<List<Workout>> allWorkouts;

    private ExerciseRepository(Application app)
    {
        /** WEBSERVICES **/
        exercisesData = new MutableLiveData<>();

        /** DATABASE **/
        ExerciseDatabase database = ExerciseDatabase.getInstance(app);
        exerciseDAO = database.exerciseDAO();
        allExercisesDao = exerciseDAO.getAllExercises();
        executorService = Executors.newFixedThreadPool(2);

        workoutDAO = database.workoutDAO();
        workoutByDate = new MutableLiveData<>();
        allWorkouts = workoutDAO.getAllWorkouts();
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

    /** DATABASE **/
    public LiveData<List<Exercise>> getAllExercisesDao()
    {
        return allExercisesDao;
    }

    public void deleteAllExercises()
    {
        executorService.execute(exerciseDAO::deleteAll);
    }

    public void deleteAllWorkouts()
    {
        executorService.execute(workoutDAO::deleteAll);
    }

    public void insert (Exercise exercise)
    {
        executorService.execute(() -> exerciseDAO.insert(exercise));
    }
    public void delete (Exercise exercise)
    {
        executorService.execute(() -> exerciseDAO.delete(exercise));
    }

    public void insertWorkout (Workout workout)
    {
        executorService.execute(() -> workoutDAO.insert(workout));
    }

    public LiveData<List<Workout>> getWorkoutByDate(int date)
    {
        executorService.execute(() -> workoutDAO.getWorkoutByDate(date));
        workoutByDate.setValue(workoutDAO.getWorkoutByDate(date).getValue());
        return workoutByDate;
    }

    public LiveData<List<Workout>> getAllWorkouts()
    {
        return allWorkouts;
    }

    public void deleteWorkout(Workout workout)
    {
        executorService.execute(() -> workoutDAO.delete(workout));
    }

    public void updateWorkout(Workout workout)
    {
        executorService.execute(() -> workoutDAO.update(workout));
    }
}
