package com.example.gymscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    ExerciseViewModel viewModel;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        Intent intent = getIntent();
        int exerciseId = intent.getIntExtra("exercise", 0);

        viewModel.setExercise(exerciseId);
        textView = findViewById(R.id.textView);

        viewModel.getExercise().observe(this, exercise -> {
            textView.setText(viewModel.getExerciseInClass().getDescription());
            Log.i("Exercise", viewModel.getExerciseInClass().getDescription());
        });
    }
}