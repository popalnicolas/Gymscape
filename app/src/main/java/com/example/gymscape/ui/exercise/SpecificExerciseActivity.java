package com.example.gymscape.ui.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exerciselist.ExerciseActivity;

public class SpecificExerciseActivity extends AppCompatActivity {

    private Exercise exercise;

    ImageView exerciseImage;
    TextView exerciseDescription;
    TextView exerciseName;
    SpecificExerciseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_exercise);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(SpecificExerciseViewModel.class);

        this.getSupportActionBar().setTitle(getResources().getString(R.string.title_exercises));
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        Intent intent = getIntent();
        exercise = (Exercise) intent.getSerializableExtra(UsedEnums.EXERCISE.toString());
        String position = intent.getStringExtra(UsedEnums.POSITION.toString());

        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseDescription = findViewById(R.id.exerciseDescription);
        exerciseName = findViewById(R.id.exerciseName);

        if(position.equals("webservice")) {
            viewModel.setExercise();
            viewModel.getExercises().observe(this, exercises -> {
                Glide.with(this).load(exercise.getPicture()).into(exerciseImage);
                exerciseDescription.setText(exercise.getDescription());
                exerciseName.setText(exercise.getName().toUpperCase());
            });
        }
        else
        {
            viewModel.getExercisesDAO().observe(this, exercises -> {
                Glide.with(this).load(exercise.getPicture()).into(exerciseImage);
                exerciseDescription.setText(exercise.getDescription());
                exerciseName.setText(exercise.getName().toUpperCase());
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, ExerciseActivity.class);
        setResult(RESULT_OK, intent);

        finish();
    }
}