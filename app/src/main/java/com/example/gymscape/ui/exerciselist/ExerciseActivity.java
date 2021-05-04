package com.example.gymscape.ui.exerciselist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.LoginActivity;
import com.example.gymscape.ui.MainActivity;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exercise.SpecificExerciseActivity;
import com.example.gymscape.ui.main.exercises.ExercisesFragment;
import com.example.gymscape.ui.newexercise.NewExerciseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseAdapter.OnListItemClickListener{

    ExerciseViewModel viewModel;
    RecyclerView recyclerView;
    ExerciseAdapter adapter;
    ArrayList<Exercise> exercises = new ArrayList<>();
    FloatingActionButton fab;
    int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        this.getSupportActionBar().setTitle(getResources().getString(R.string.title_workout));
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        Intent intent = getIntent();
        category = intent.getIntExtra(UsedEnums.CATEGORY.toString(), 0);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ExerciseViewModel.class);

        fab = findViewById(R.id.addNewExerciseButton);
        fab.setOnClickListener(v -> {
            Intent newProfileIntent = new Intent(this, NewExerciseActivity.class);
            startActivity(newProfileIntent);
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.setExercise(category);

        adapter = new ExerciseAdapter(exercises, this);
        recyclerView.setAdapter(adapter);

        viewModel.getExercises().observe(this, exerciseCollection ->{
            exercises.addAll(exerciseCollection);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex, String position) {
        Intent toSpecificExercise = new Intent(this, SpecificExerciseActivity.class);
        toSpecificExercise.putExtra(UsedEnums.EXERCISE.toString(), adapter.exercisesList.get(clickedItemIndex));
        toSpecificExercise.putExtra(UsedEnums.POSITION.toString(), position);
        startActivity(toSpecificExercise);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        exercises.clear();
        viewModel.getAllExercisesDAO().observe(this, exercisesDAO ->{
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