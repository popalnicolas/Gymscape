package com.example.gymscape.architecture.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.gymscape.Model.Exercise;

import java.util.List;

@Dao
public interface ExerciseDAO {
    @Insert
    void insert(Exercise exercise);

    @Delete
    void delete(Exercise exercise);

    @Query("SELECT * FROM exercise_table ORDER BY category DESC")
    LiveData<List<Exercise>> getAllExercises();

    @Query("DELETE FROM exercise_table")
    void deleteAll();
}
