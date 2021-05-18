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
import com.example.gymscape.SharedFunctions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.gymscape.ui.MainActivity;

public class LoginActivity extends AppCompatActivity {

    TextView loginRegister, passwordReset;
    FirebaseAuth firebaseAuth;
    EditText emailAddress, password;
    Button loginButton;

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

        loginRegister = findViewById(R.id.loginRegister);
        passwordReset = findViewById(R.id.passwordReset);

        loginRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            finish();
        });

        passwordReset.setOnClickListener(v -> {
            startActivity(new Intent(this, ResetPasswordActivity.class));
            finish();
        });

        emailAddress = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            if(emailAddress.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                Toast.makeText(this, "Please fill all information.", Toast.LENGTH_SHORT).show();
            else if(!SharedFunctions.isEmailValid(emailAddress.getText().toString()))
                Toast.makeText(this, "Wrong email format.", Toast.LENGTH_SHORT).show();
            else {
                firebaseAuth.signInWithEmailAndPassword(emailAddress.getText().toString().trim(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(firebaseAuth.getCurrentUser().isEmailVerified()) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            }
                            else
                                Toast.makeText(LoginActivity.this, "You need to verify your email.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "This email is not registered yet.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}