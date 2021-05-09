package com.example.gymscape.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gymscape.R;
import com.example.gymscape.ui.exerciselist.ExerciseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().hide();
        
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_exercises, R.id.navigation_workout, R.id.navigation_calendar, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void openExercise(View view)
    {
        if(view.getId() == R.id.muscleCore)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 1);
            startActivity(intent);
        }
        else if(view.getId() == R.id.muscleChest)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 2);
            startActivity(intent);
        }
        else if(view.getId() == R.id.muscleBack)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 3);
            startActivity(intent);
        }
        else if(view.getId() == R.id.muscleBiceps)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 4);
            startActivity(intent);
        }
        else if(view.getId() == R.id.muscleTriceps)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 5);
            startActivity(intent);
        }
        else if(view.getId() == R.id.muscleShoulders)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 6);
            startActivity(intent);
        }
        else if(view.getId() == R.id.muscleLegs)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 7);
            startActivity(intent);
        }
        else if(view.getId() == R.id.muscleGlutes)
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 8);
            startActivity(intent);
        }
        else
        {
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), 0);
            startActivity(intent);
        }
    }
}