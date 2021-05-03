package com.example.gymscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymscape.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity implements ExerciseAdapter.OnListItemClickListener{

    ExerciseViewModel viewModel;
    RecyclerView recyclerView;
    ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //Intent intent = getIntent();
        //int category = intent.getIntExtra("exercise", 0);

        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.setExercise();
        ArrayList<Exercise> exercises = new ArrayList<>();
        viewModel.getExercises().observe(this, exerciseCollection ->{
            exercises.clear();
            exercises.addAll(exerciseCollection);
        });

        adapter = new ExerciseAdapter(exercises, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        /*Intent toSpecificExercise = new Intent(this, ExerciseActivity.class);
        toSpecificExercise.putExtra("specificExercise", adapter.exercises.get(position).getId());
        startActivity(toSpecificExercise);*/
    }
}