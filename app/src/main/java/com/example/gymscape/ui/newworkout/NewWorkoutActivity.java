package com.example.gymscape.ui.newworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exercise.SpecificExerciseViewModel;
import com.example.gymscape.ui.main.calendar.CalendarViewModel;
import com.example.gymscape.ui.newexercise.NewExerciseViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewWorkoutActivity extends AppCompatActivity {

    private NewWorkoutViewModel viewModel;

    EditText datePicker;
    Calendar calendar;
    Exercise exercise;
    ImageView exerciseImageCategory;
    TextView exerciseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NewWorkoutViewModel.class);

        Intent intent = getIntent();
        exercise = (Exercise) intent.getSerializableExtra(UsedEnums.EXERCISE.toString());

        exerciseImageCategory = findViewById(R.id.exerciseImageCategory);
        exerciseName = findViewById(R.id.exerciseName);

        viewModel.setExercise(exercise);
        viewModel.getExercise().observe(this, exerciseData -> {
            this.getSupportActionBar().setTitle("Add " + exerciseData.getName() + " to workout");
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

            exerciseName.setText(exerciseData.getName());
            exerciseImageCategory.setImageResource(getResourceImage(exerciseData.getCategory()));
        });

        calendar = Calendar.getInstance();
        datePicker = findViewById(R.id.editTextDate);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONDAY, dayOfMonth);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                viewModel.setDate(calendar.getTime());
            }
        };

        datePicker.setOnClickListener(v -> {
            new DatePickerDialog(this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        viewModel.getDate().observe(this, chosenDate -> {
            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.UK);
            datePicker.setText(dateFormat.format(chosenDate));
        });
    }

    private int getResourceImage(int category)
    {
        switch(category)
        {
            case 1:
                return R.drawable.icon_core;
            case 2:
                return R.drawable.icon_chest;
            case 3:
                return R.drawable.icon_back;
            case 4:
                return R.drawable.icon_biceps;
            case 5:
                return R.drawable.icon_triceps;
            case 6:
                return R.drawable.icon_shoulder;
            case 7:
                return R.drawable.icon_legs;
            case 8:
                return R.drawable.icon_glutes;
        }
        return 0;
    }
}