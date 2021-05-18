package com.example.gymscape.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymscape.R;
import com.example.gymscape.SharedFunctions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    EditText emailAddress;
    TextView backToLogin;
    Button confirmReset;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        this.getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();

        emailAddress = findViewById(R.id.passwordResetEmail);
        backToLogin = findViewById(R.id.loginTextView);
        confirmReset = findViewById(R.id.resetButton);

        backToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        confirmReset.setOnClickListener(v -> {
            if(emailAddress.getText().toString().isEmpty())
                Toast.makeText(this, "Please fill your email address", Toast.LENGTH_SHORT).show();
            else if(!SharedFunctions.isEmailValid(emailAddress.getText().toString()))
                Toast.makeText(this, "Wrong email format.", Toast.LENGTH_SHORT).show();
            else
            {
                firebaseAuth.sendPasswordResetEmail(emailAddress.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ResetPasswordActivity.this, "Password was reset. Check your email.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                            finish();
                        }
                        else
                            Toast.makeText(ResetPasswordActivity.this, "This email is not registered yet.", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}