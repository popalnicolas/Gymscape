package com.example.gymscape.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    TextView alreadyRegistered;
    EditText emailAddress, password, password2;
    Button registerButton;
    FirebaseAuth firebaseAuth;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        preferences = getSharedPreferences("profile", MODE_PRIVATE);

        alreadyRegistered = findViewById(R.id.alreadyRegistered);
        alreadyRegistered.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        emailAddress = findViewById(R.id.registerEmailAddress);
        password = findViewById(R.id.registerPassword1);
        password2 = findViewById(R.id.registerPassword2);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            if(password.getText().toString().isEmpty() || password2.getText().toString().isEmpty() || emailAddress.getText().toString().isEmpty())
                Toast.makeText(this, "Please fill all information.", Toast.LENGTH_SHORT).show();
            else if(!password.getText().toString().equals(password2.getText().toString()))
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            else if(password.getText().toString().length() < 6)
                Toast.makeText(this, "Password must have at least 6 characters", Toast.LENGTH_SHORT).show();
            else if(!SharedFunctions.isEmailValid(emailAddress.getText().toString()))
                Toast.makeText(this, "Wrong email format.", Toast.LENGTH_SHORT).show();
            else
            {
                firebaseAuth.createUserWithEmailAndPassword(emailAddress.getText().toString().trim(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            firebaseAuth.getCurrentUser().sendEmailVerification();
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("email", emailAddress.getText().toString());
                            editor.apply();
                            firebaseAuth.signOut();
                            Toast.makeText(RegisterActivity.this, "Registration was successful. Account created.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "Email is already registered.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}