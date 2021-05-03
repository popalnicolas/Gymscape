package com.example.gymscape.ui.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.IntentExtraEnum;

public class SpecificExerciseActivity extends AppCompatActivity {

    ImageView exerciseImage;
    TextView exerciseDescription;
    TextView exerciseName;
    SpecificExerciseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_exercise);

        viewModel = new ViewModelProvider(this).get(SpecificExerciseViewModel.class);

        this.getSupportActionBar().setTitle(getResources().getString(R.string.title_exercises));
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        Intent intent = getIntent();
        int exerciseId = intent.getIntExtra(IntentExtraEnum.EXERCISE.toString(), 0);

        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseDescription = findViewById(R.id.exerciseDescription);
        exerciseName = findViewById(R.id.exerciseName);

        viewModel.setExercise();

        viewModel.getExercises().observe(this, exercises -> {
            Exercise exercise = viewModel.getExerciseById(exerciseId);
            Glide.with(this).load(exercise.getPicture()).into(exerciseImage);
            exerciseDescription.setText(exercise.getDescription());
            exerciseName.setText(exercise.getName().toUpperCase());
        });
    }
}