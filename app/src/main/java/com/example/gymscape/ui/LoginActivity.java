package com.example.gymscape.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

import com.example.gymscape.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_login);
    }
}