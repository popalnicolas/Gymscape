package com.example.gymscape.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymscape.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.gymscape.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {

    TextView loginRegister;
    FirebaseAuth firebaseAuth;
    EditText emailAddress, password;
    Button loginButton;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            Log.i("Logged in", "true");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        preferences = getSharedPreferences("profile", MODE_PRIVATE);

        loginRegister = findViewById(R.id.loginRegister);

        loginRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });

        emailAddress = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            firebaseAuth.signInWithEmailAndPassword(emailAddress.getText().toString().trim(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", emailAddress.getText().toString());
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Something went wrong. Try again or register yourself.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        });
    }
}