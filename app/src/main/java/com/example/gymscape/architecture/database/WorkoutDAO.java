package com.example.gymscape.architecture.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.gymscape.Model.Workout;

import java.util.List;

@Dao
public interface WorkoutDAO {
    @Insert
    void insert(Workout workout);

    @Delete
    void delete(Workout workout);

    @Update
    void update(Workout workout);

    @Query("SELECT * FROM workout_table WHERE date= :date")
    LiveData<List<Workout>> getWorkoutByDate(long date);
}
