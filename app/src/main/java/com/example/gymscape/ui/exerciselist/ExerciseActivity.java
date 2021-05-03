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
import com.example.gymscape.ui.IntentExtraEnum;
import com.example.gymscape.ui.exercise.SpecificExerciseActivity;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity implements ExerciseAdapter.OnListItemClickListener{

    ExerciseViewModel viewModel;
    RecyclerView recyclerView;
    ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        this.getSupportActionBar().setTitle(getResources().getString(R.string.title_workout));
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        Intent intent = getIntent();
        int category = intent.getIntExtra(IntentExtraEnum.CATEGORY.toString(), 0);

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
        Intent toSpecificExercise = new Intent(this, SpecificExerciseActivity.class);
        toSpecificExercise.putExtra(IntentExtraEnum.EXERCISE.toString(), clickedItemIndex);
        startActivity(toSpecificExercise);
    }
}