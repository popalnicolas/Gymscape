package com.example.gymscape.architecture.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gymscape.Model.Exercise;

@Database(entities = {Exercise.class}, version = 5)
public abstract class ExerciseDatabase extends RoomDatabase {

    private static ExerciseDatabase instance;

    public abstract ExerciseDAO exerciseDAO();

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
