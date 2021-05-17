package com.example.gymscape.ui.exerciselist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.MainActivity;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exercise.SpecificExerciseActivity;
import com.example.gymscape.ui.main.exercises.ExercisesFragment;
import com.example.gymscape.ui.newexercise.NewExerciseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class ExerciseActivity extends AppCompatActivity implements ExerciseAdapter.OnListItemClickListener{

    ExerciseViewModel viewModel;
    RecyclerView recyclerView;
    ExerciseAdapter adapter;
    FloatingActionButton fab;
    int category;
    ArrayList<Exercise> exercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ArrayList<String> categoryNames = new ArrayList<>(Arrays.asList("All", "Core", "Chest", "Back", "Biceps", "Triceps", "Shoulders", "Legs", "Glutes"));
        Intent intent = getIntent();
        category = intent.getIntExtra(UsedEnums.CATEGORY.toString(), 0);

        this.getSupportActionBar().setTitle( categoryNames.get(category) + " " + getResources().getString(R.string.title_exercises));
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ExerciseViewModel.class);

        fab = findViewById(R.id.addNewExerciseButton);
        if(category == 0)
            fab.setVisibility(View.GONE);
        fab.setOnClickListener(v -> {
            Intent newProfileIntent = new Intent(this, NewExerciseActivity.class);
            newProfileIntent.putExtra(UsedEnums.CATEGORY.toString(), category);
            startActivity(newProfileIntent);
            finish();
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.setExercise();

        adapter = new ExerciseAdapter(exercises, this);
        recyclerView.setAdapter(adapter);

        exercises.clear();

        viewModel.getExercises().observe(this, exerciseCollection ->{
            for(Exercise exercise : exerciseCollection)
            {
                if(category == 0)
                    exercises.add(exercise);
                else if(exercise.getCategory() == category)
                    exercises.add(exercise);
            }
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent toSpecificExercise = new Intent(this, SpecificExerciseActivity.class);
        toSpecificExercise.putExtra(UsedEnums.EXERCISE.toString(), adapter.exercisesList.get(clickedItemIndex));
        startActivity(toSpecificExercise);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllExercisesDAO().observe(this, exercisesDAO ->{
            exercises.clear();
            for(Exercise exercise : exercisesDAO)
            {
                if(category == 0)
                    exercises.add(exercise);
                else if(exercise.getCategory() == category)
                    exercises.add(exercise);
            }
            adapter.notifyDataSetChanged();
        });
    }
}