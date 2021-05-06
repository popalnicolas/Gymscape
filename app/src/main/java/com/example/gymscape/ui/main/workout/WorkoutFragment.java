package com.example.gymscape.ui.main.workout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymscape.Model.Workout;
import com.example.gymscape.R;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.editworkout.EditWorkoutActivity;
import com.example.gymscape.ui.exercise.SpecificExerciseActivity;
import com.example.gymscape.ui.newexercise.NewExerciseViewModel;
import com.example.gymscape.ui.newworkout.NewWorkoutActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WorkoutFragment extends Fragment implements WorkoutAdapter.OnListItemClickListener {

    private WorkoutViewModel viewModel;

    RecyclerView recyclerView;
    WorkoutAdapter adapter;
    TextView textView;

    int currentDate;

    ArrayList<Workout> allWorkouts = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_workout, container, false);
        textView = root.findViewById(R.id.text_workout);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(WorkoutViewModel.class);

        Date c = Calendar.getInstance().getTime();
        currentDate = getDate(c.getTime());

        recyclerView = root.findViewById(R.id.workoutRecycleView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new WorkoutAdapter(allWorkouts, this);
        recyclerView.setAdapter(adapter);

        viewModel.getAllWorkouts().observe(getActivity(), workouts -> {
            allWorkouts.clear();
            for(Workout w : workouts)
            {
                if(w.getDate() == currentDate) {
                    allWorkouts.add(w);
                    viewModel.setIsWorkout(true);
                }
            }
            adapter.notifyDataSetChanged();
            if(allWorkouts.isEmpty())
                viewModel.setIsWorkout(false);
        });

        viewModel.isWorkout().observe(getActivity(), isWorkout ->{
            Log.i("Is workout?", isWorkout+"");
            if(!isWorkout)
                textView.setText("No planned workouts for today.");
            else
                textView.setText("");
        });

        return root;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent toWorkout = new Intent(getContext(), EditWorkoutActivity.class);
        toWorkout.putExtra(UsedEnums.WORKOUT.toString(), adapter.workoutList.get(clickedItemIndex));
        startActivity(toWorkout);
    }

    private int getDate(long date)
    {
        Date date1 = new Date(date);
        String myFormat = "ddMMyyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.UK);
        String desiredFormat = dateFormat.format(date1);
        return Integer.parseInt(desiredFormat);
    }
}