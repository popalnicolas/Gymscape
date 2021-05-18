package com.example.gymscape.ui.main.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gymscape.R;
import com.example.gymscape.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    private ProfileViewModel viewModel;

    ConstraintLayout textLogout, deleteAll;
    TextView textUsername;

    FirebaseAuth firebaseAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        textUsername = root.findViewById(R.id.textUsername);
        textUsername.setText(firebaseAuth.getCurrentUser().getEmail().toLowerCase());

        textLogout = root.findViewById(R.id.logoutLayout);
        textLogout.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        });

        deleteAll = root.findViewById(R.id.deleteAllLayout);
        deleteAll.setOnClickListener(v -> {

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            viewModel.deleteAll();
                            Toast.makeText(getActivity(), "All data deleted.", Toast.LENGTH_SHORT).show();
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            // do nothing
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("All saved workouts and exercises created by you will be deleted. Do you want to continue?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        });

        return root;
    }
}