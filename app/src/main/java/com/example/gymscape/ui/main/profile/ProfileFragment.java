package com.example.gymscape.ui.main.profile;

import android.content.Context;
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

    SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        preferences = getActivity().getSharedPreferences("profile", Context.MODE_PRIVATE);

        textUsername = root.findViewById(R.id.textUsername);
        textUsername.setText(preferences.getString("email", "").toLowerCase());

        textLogout = root.findViewById(R.id.logoutLayout);
        textLogout.setOnClickListener(v -> {
            firebaseAuth.signOut();
            startActivity(new Intent(getContext(), LoginActivity.class));
        });

        deleteAll = root.findViewById(R.id.deleteAllLayout);
        deleteAll.setOnClickListener(v -> {
            viewModel.deleteAll();
            Toast.makeText(getActivity(), "All data deleted.", Toast.LENGTH_SHORT).show();
        });

        return root;
    }
}