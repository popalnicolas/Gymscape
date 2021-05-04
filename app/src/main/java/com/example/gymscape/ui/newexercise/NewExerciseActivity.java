package com.example.gymscape.ui.newexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.gymscape.R;
import com.example.gymscape.ui.MainActivity;
import com.example.gymscape.ui.exerciselist.ExerciseActivity;

public class NewExerciseActivity extends AppCompatActivity {

    EditText categoryField;
    EditText nameField;
    EditText descriptionField;
    Button selectPhoto;
    Button addExercise;
    Button cancelExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        categoryField = findViewById(R.id.categoryEditText);
        nameField = findViewById(R.id.nameEditText);
        descriptionField = findViewById(R.id.descriptionEditText);
        selectPhoto = findViewById(R.id.imageButton);
        addExercise = findViewById(R.id.addButton);
        cancelExercise = findViewById(R.id.cancelButton);

        cancelExercise.setOnClickListener(v -> {
            Intent intent = new Intent(this, ExerciseActivity.class);
        });
    }
}