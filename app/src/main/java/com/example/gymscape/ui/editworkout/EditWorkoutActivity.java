package com.example.gymscape.ui.editworkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymscape.Model.Workout;
import com.example.gymscape.R;
import com.example.gymscape.SharedFunctions;
import com.example.gymscape.ui.MainActivity;
import com.example.gymscape.ui.UsedEnums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditWorkoutActivity extends AppCompatActivity {

    private EditWorkoutViewModel viewModel;

    Workout workout;

    ImageView exerciseImageCategory;
    TextView exerciseName;
    EditText editTextDate;
    TextView setsCountTextView;
    EditText weightTextNumber;
    Button updateWorkoutButton;
    Button deleteWorkoutButton;
    Button cancelWorkoutButton;
    Button increaseSets;
    Button decreaseSets;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(EditWorkoutViewModel.class);

        Intent intent = getIntent();
        workout = (Workout) intent.getSerializableExtra(UsedEnums.WORKOUT.toString());

        exerciseImageCategory = findViewById(R.id.exerciseImageCategory);
        exerciseName = findViewById(R.id.exerciseName);
        editTextDate = findViewById(R.id.editTextDate);
        setsCountTextView = findViewById(R.id.setsCountTextView);
        weightTextNumber = findViewById(R.id.weightTextNumber);
        updateWorkoutButton = findViewById(R.id.updateWorkoutButton);
        deleteWorkoutButton = findViewById(R.id.deleteWorkoutButton);
        cancelWorkoutButton = findViewById(R.id.cancelWorkoutButton);
        increaseSets = findViewById(R.id.increaseSetsButton);
        decreaseSets = findViewById(R.id.decreaseSetsButton);

        calendar = Calendar.getInstance();

        this.getSupportActionBar().setTitle("Workout - " + workout.getExerciseName());
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        exerciseImageCategory.setImageResource(SharedFunctions.getIcon(workout.getCategory()));
        exerciseName.setText(workout.getExerciseName());
        weightTextNumber.setText("" + workout.getWeight());
        viewModel.setSetsCount(workout.getSets());

        String newDate;
        if(workout.getDate() < 10000000)
            newDate = "0" + workout.getDate();
        else
            newDate = "" + workout.getDate();

        try {
            Date savedDate = new SimpleDateFormat("ddMMyyyy").parse(newDate);
            viewModel.setDate(savedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewModel.getDate().observe(this, chosenDate ->{

            int year = Integer.parseInt((String) DateFormat.format("yyyy", chosenDate));
            int month = Integer.parseInt((String) DateFormat.format("MM", chosenDate));
            int dayOfMonth = Integer.parseInt((String) DateFormat.format("dd", chosenDate));

            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    viewModel.setDate(calendar.getTime());
                }
            };
            editTextDate.setOnClickListener(v -> {
                new DatePickerDialog(this, date, year, month-1, dayOfMonth).show();
            });

            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.UK);
            editTextDate.setText(dateFormat.format(chosenDate));

            workout.setDate(SharedFunctions.getDate(chosenDate.getTime()));
        });

        decreaseSets.setOnClickListener(v -> {
            if(viewModel.getSetsCount().getValue() <= 0)
                Toast.makeText(this, "Minimum value of sets is 0", Toast.LENGTH_SHORT).show();
            else
            {
                int setsValue = Integer.parseInt(setsCountTextView.getText().toString());
                viewModel.setSetsCount(--setsValue);
            }
        });

        increaseSets.setOnClickListener(v -> {
            if(viewModel.getSetsCount().getValue() >= 10)
                Toast.makeText(this, "Maximum value of sets is 10", Toast.LENGTH_SHORT).show();
            else
            {
                int setsValue = Integer.parseInt(setsCountTextView.getText().toString());
                viewModel.setSetsCount(++setsValue);
            }
        });

        viewModel.getSetsCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                setsCountTextView.setText("" + integer);

                workout.setSets(integer);
            }
        });

        updateWorkoutButton.setOnClickListener(v -> {
            if(workout.getDate() <= 0)
                Toast.makeText(this, "You must set date to continue.", Toast.LENGTH_SHORT).show();
            else {
                if (weightTextNumber.getText().toString().equals(""))
                    workout.setWeight(0);
                else
                    workout.setWeight(Integer.parseInt(weightTextNumber.getText().toString()));

                viewModel.update(workout);

                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        deleteWorkoutButton.setOnClickListener(v -> {
            viewModel.delete(workout);
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
            finish();
        });

        cancelWorkoutButton.setOnClickListener(v -> {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
            finish();
        });
    }
}