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

        Intent intent = getIntent();
        exercise = (Exercise) intent.getSerializableExtra(UsedEnums.EXERCISE.toString());

        this.getSupportActionBar().setTitle(exercise.getName());
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseDescription = findViewById(R.id.exerciseDescription);
        exerciseName = findViewById(R.id.exerciseName);

        if(exercise.getPicture().isEmpty())
            exerciseImage.setImageResource(R.drawable.exercise_universal_picture);
        else
            Glide.with(this).load(exercise.getPicture()).into(exerciseImage);
        exerciseDescription.setText(exercise.getDescription() + "\n\n\n\n\n\n\n\n");
        exerciseName.setText(exercise.getName().toUpperCase());
    }
}