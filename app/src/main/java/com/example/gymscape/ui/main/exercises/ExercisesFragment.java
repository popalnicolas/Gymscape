package com.example.gymscape.ui.main.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymscape.R;

public class ExercisesFragment extends Fragment {

    private ExercisesViewModel exercisesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        exercisesViewModel =
                new ViewModelProvider(this).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_exercises, container, false);
        /*final TextView textView = root.findViewById(R.id.text_exercises);
        exercisesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        return root;
    }


}