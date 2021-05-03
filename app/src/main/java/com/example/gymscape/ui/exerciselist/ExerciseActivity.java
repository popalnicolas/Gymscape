package com.example.gymscape.ui.exerciselist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseAdapter.OnListItemClickListener{

    ExerciseViewModel viewModel;
    RecyclerView recyclerView;
    ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
        int category = intent.getIntExtra("exercise", 0);

        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.setExercise();

        ArrayList<Exercise> exercises = new ArrayList<>();
        adapter = new ExerciseAdapter(exercises, this);
        recyclerView.setAdapter(adapter);

        viewModel.getExercises().observe(this, exerciseCollection ->{
            exercises.clear();
            exercises.addAll(viewModel.getExerciseByCategory(category));
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        /*Intent toSpecificExercise = new Intent(this, ExerciseActivity.class);
        toSpecificExercise.putExtra("specificExercise", adapter.exercises.get(position).getId());
        startActivity(toSpecificExercise);*/
        Toast.makeText(this, "" + clickedItemIndex, Toast.LENGTH_SHORT).show();
    }
}