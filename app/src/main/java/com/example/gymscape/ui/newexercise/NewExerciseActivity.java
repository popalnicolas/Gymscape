package com.example.gymscape.ui.newexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.MainActivity;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exerciselist.ExerciseActivity;

import java.io.IOException;

public class NewExerciseActivity extends AppCompatActivity {

    private NewExerciseViewModel viewModel;

    EditText nameField;
    EditText descriptionField;
    Button selectPhoto;
    Button cancelExercise;
    ImageView previewImage;

    Bitmap bitmap;

    final int CAMERA_REQUEST = 1;

    int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        viewModel = new ViewModelProvider(this).get(NewExerciseViewModel.class);

        Intent intent = getIntent();
        category = intent.getIntExtra(UsedEnums.CATEGORY.toString(), 0);

        nameField = findViewById(R.id.nameEditText);
        descriptionField = findViewById(R.id.descriptionEditText);
        selectPhoto = findViewById(R.id.imageButton);
        cancelExercise = findViewById(R.id.cancelButton);
        previewImage = findViewById(R.id.previewImage);

        cancelExercise.setOnClickListener(v -> {
            Intent backIntent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), category);
            startActivity(backIntent);
            finish();
        });

        selectPhoto.setOnClickListener(v -> {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });
    }

    public void saveExercise(View v)
    {
        if(nameField.getText().toString().length() > 20)
            Toast.makeText(this, "Exercise name is too long.", Toast.LENGTH_SHORT).show();
        else if(nameField.getText().toString().isEmpty() || descriptionField.getText().toString().isEmpty())
            Toast.makeText(this, "Description or exercise name are empty.", Toast.LENGTH_SHORT).show();
        else {
            viewModel.insert(new Exercise(nameField.getText().toString(), category, descriptionField.getText().toString(), bitmap.toString()));
            Intent intent = new Intent(this, ExerciseActivity.class);
            intent.putExtra(UsedEnums.CATEGORY.toString(), category);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            previewImage.setImageBitmap(photo);
        }
    }
}