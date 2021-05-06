package com.example.gymscape.architecture.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.Model.Workout;

@Database(entities = {Exercise.class, Workout.class}, version = 8)
public abstract class ExerciseDatabase extends RoomDatabase {

    private static ExerciseDatabase instance;

    public abstract ExerciseDAO exerciseDAO();
    public abstract WorkoutDAO workoutDAO();

    public static synchronized ExerciseDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, ExerciseDatabase.class, "exercise_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
