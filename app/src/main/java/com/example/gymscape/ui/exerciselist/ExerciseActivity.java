package com.example.gymscape.ui.exerciselist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exercise.SpecificExerciseActivity;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseAdapter.OnListItemClickListener{

    ExerciseViewModel viewModel;
    RecyclerView recyclerView;
    ExerciseAdapter adapter;
    ArrayList<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        this.getSupportActionBar().setTitle(getResources().getString(R.string.title_workout));
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        Intent intent = getIntent();
        int category = intent.getIntExtra(UsedEnums.CATEGORY.toString(), 0);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ExerciseViewModel.class);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.setExercise();

        exercises = new ArrayList<>();
        adapter = new ExerciseAdapter(exercises, this);
        recyclerView.setAdapter(adapter);

        viewModel.getAllExercisesDAO().observe(this, exercisesDAO ->{
            exercises.addAll(viewModel.getExerciseByCategoryDAO(category));
            adapter.notifyDataSetChanged();
        });

        viewModel.getExercises().observe(this, exerciseCollection ->{
            exercises.addAll(viewModel.getExerciseByCategory(category));
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex, String position) {
        Intent toSpecificExercise = new Intent(this, SpecificExerciseActivity.class);
        toSpecificExercise.putExtra(UsedEnums.EXERCISE.toString(), clickedItemIndex);
        toSpecificExercise.putExtra(UsedEnums.POSITION.toString(), position);
        startActivity(toSpecificExercise);
    }

    @Override
    protected void onResume() {
        super.onResume();

        
        exercises.clear();
    }
}