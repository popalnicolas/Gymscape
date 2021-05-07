package com.example.gymscape.ui.newworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gymscape.Model.Exercise;
import com.example.gymscape.Model.Workout;
import com.example.gymscape.R;
import com.example.gymscape.SharedFunctions;
import com.example.gymscape.ui.MainActivity;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exercise.SpecificExerciseActivity;
import com.example.gymscape.ui.exercise.SpecificExerciseViewModel;
import com.example.gymscape.ui.exerciselist.ExerciseActivity;
import com.example.gymscape.ui.main.calendar.CalendarViewModel;
import com.example.gymscape.ui.newexercise.NewExerciseViewModel;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewWorkoutActivity extends AppCompatActivity {

    private NewWorkoutViewModel viewModel;

    EditText datePicker;
    EditText weightTextNumber;
    Calendar calendar;
    Exercise exercise;
    ImageView exerciseImageCategory;
    TextView exerciseName;
    Button increaseSets;
    Button decreaseSets;
    Button saveWorkoutButton;
    Button cancelWorkoutButton;
    TextView setsCountTextView;

    Workout newWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_workout);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NewWorkoutViewModel.class);

        this.getSupportActionBar().setTitle("Add exercise to workout");
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        Intent intent = getIntent();
        exercise = (Exercise) intent.getSerializableExtra(UsedEnums.EXERCISE.toString());

        exerciseImageCategory = findViewById(R.id.exerciseImageCategory);
        exerciseName = findViewById(R.id.exerciseName);
        weightTextNumber = findViewById(R.id.weightTextNumber);
        increaseSets = findViewById(R.id.increaseSetsButton);
        decreaseSets = findViewById(R.id.decreaseSetsButton);
        setsCountTextView = findViewById(R.id.setsCountTextView);
        saveWorkoutButton = findViewById(R.id.saveWorkoutButton);
        cancelWorkoutButton = findViewById(R.id.cancelWorkoutButton);

        newWorkout = new Workout();

        viewModel.setExercise(exercise);
        viewModel.getExercise().observe(this, exerciseData -> {
            /* Text can be too long to display it in actionbar
            this.getSupportActionBar().setTitle("Add " + exerciseData.getName() + " to workout");
            this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));
            */

            exerciseName.setText(exerciseData.getName());
            exerciseImageCategory.setImageResource(SharedFunctions.getIcon(exerciseData.getCategory()));

            newWorkout.setExerciseName(exerciseData.getName());
            newWorkout.setCategory(exerciseData.getCategory());
        });

        calendar = Calendar.getInstance();
        datePicker = findViewById(R.id.editTextDate);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
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

            newWorkout.setDate(SharedFunctions.getDate(chosenDate.getTime()));
        });

        decreaseSets.setOnClickListener(v -> {
            if(viewModel.getSets().getValue() <= 0)
                Toast.makeText(this, "Minimum value of sets is 0", Toast.LENGTH_SHORT).show();
            else
            {
                int setsValue = Integer.parseInt(setsCountTextView.getText().toString());
                viewModel.setSets(--setsValue);
            }
        });

        increaseSets.setOnClickListener(v -> {
            if(viewModel.getSets().getValue() >= 10)
                Toast.makeText(this, "Maximum value of sets is 10", Toast.LENGTH_SHORT).show();
            else
            {
                int setsValue = Integer.parseInt(setsCountTextView.getText().toString());
                viewModel.setSets(++setsValue);
            }
        });

        viewModel.getSets().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setsCountTextView.setText("" + integer);

                newWorkout.setSets(integer);
            }
        });

        saveWorkoutButton.setOnClickListener(v -> {
            if(newWorkout.getDate() <= 0)
                Toast.makeText(this, "You must set date to continue.", Toast.LENGTH_SHORT).show();
            else {
                if (weightTextNumber.getText().toString().equals(""))
                    newWorkout.setWeight(0);
                else
                    newWorkout.setWeight(Integer.parseInt(weightTextNumber.getText().toString()));

                viewModel.insert(newWorkout);
                Log.i("Date", newWorkout.getDate() + "");

                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        cancelWorkoutButton.setOnClickListener(v -> {
            Intent exerciseIntent = new Intent(this, SpecificExerciseActivity.class);
            exerciseIntent.putExtra(UsedEnums.EXERCISE.toString(), exercise);
            startActivity(exerciseIntent);
            finish();
        });
    }
}