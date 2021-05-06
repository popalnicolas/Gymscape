package com.example.gymscape.ui.exercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exerciselist.ExerciseActivity;
import com.example.gymscape.ui.newworkout.NewWorkoutActivity;

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

        viewModel.setExerciseMD(exercise);

        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseDescription = findViewById(R.id.exerciseDescription);
        exerciseName = findViewById(R.id.exerciseName);

        viewModel.getExerciseMD().observe(this, exerciseData -> {
            this.getSupportActionBar().setTitle(exerciseData.getName());
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

            if(exercise.getPicture().isEmpty())
                exerciseImage.setImageResource(R.drawable.exercise_universal_picture);
            else
                Glide.with(this).load(exercise.getPicture()).into(exerciseImage);
            exerciseDescription.setText(exercise.getDescription() + "\n\n\n\n\n\n\n\n");
            exerciseName.setText(exercise.getName().toUpperCase());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu_exercise, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.addToWorkoutMenu)
        {
            Intent intent = new Intent(this, NewWorkoutActivity.class);
            intent.putExtra(UsedEnums.EXERCISE.toString(), exercise);
            startActivity(intent);
            finish();
            return true;
        }
        else if(item.getItemId() == R.id.deleteExerciseMenu)
        {
            if(exercise.isDatabase())
            {
                viewModel.delete(exercise);
                Intent intent = new Intent(this, ExerciseActivity.class);
                intent.putExtra(UsedEnums.CATEGORY.toString(), exercise.getCategory());
                startActivity(intent);
                finish();
                return true;
            }
            else
            {
                Toast.makeText(this, "You cannot delete this exercise.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
            return super.onOptionsItemSelected(item);
    }
}