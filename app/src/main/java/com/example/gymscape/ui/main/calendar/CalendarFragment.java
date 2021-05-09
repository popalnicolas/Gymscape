package com.example.gymscape.ui.main.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.Model.Workout;
import com.example.gymscape.R;
import com.example.gymscape.SharedFunctions;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.editworkout.EditWorkoutActivity;
import com.example.gymscape.ui.main.workout.WorkoutAdapter;
import com.example.gymscape.ui.main.workout.WorkoutViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarFragment extends Fragment implements WorkoutAdapter.OnListItemClickListener {

    private CalendarViewModel viewModel;

    CalendarView calendarView;
    RecyclerView recyclerView;
    WorkoutAdapter adapter;
    TextView textView;

    int chosenDateInt;

    ArrayList<Workout> allWorkouts = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CalendarViewModel.class);

        Date c = Calendar.getInstance().getTime();
        chosenDateInt = SharedFunctions.getDate(c.getTime());

        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = root.findViewById(R.id.calendarView);
        recyclerView = root.findViewById(R.id.calendarRecycleView);
        textView = root.findViewById(R.id.text_calendar);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new WorkoutAdapter(allWorkouts, this);
        recyclerView.setAdapter(adapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Date chosenDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
                chosenDateInt = SharedFunctions.getDate(chosenDate.getTime());
                Log.i("Date", "" + chosenDateInt);
                notifyDateChange();
            }
        });

        notifyDateChange();

        return root;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent toWorkout = new Intent(getContext(), EditWorkoutActivity.class);
        toWorkout.putExtra(UsedEnums.WORKOUT.toString(), adapter.workoutList.get(clickedItemIndex));
        startActivity(toWorkout);
    }

    private void notifyDateChange()
    {

        viewModel.getAllWorkouts().observe(getActivity(), workouts -> {
            allWorkouts.clear();
            for(Workout w : workouts)
            {
                if(w.getDate() == chosenDateInt) {
                    allWorkouts.add(w);
                }
            }
            adapter.notifyDataSetChanged();
        });

        viewModel.setExerciseText(allWorkouts.isEmpty());

        viewModel.getExerciseText().observe(getViewLifecycleOwner(), exerciseText -> {
            if(exerciseText)
                textView.setText("No workouts for this date.");
            else
                textView.setText("");
        });
    }
}