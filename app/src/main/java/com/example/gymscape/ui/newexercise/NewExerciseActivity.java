package com.example.gymscape.ui.newexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gymscape.Model.Exercise;
import com.example.gymscape.R;
import com.example.gymscape.ui.MainActivity;
import com.example.gymscape.ui.UsedEnums;
import com.example.gymscape.ui.exercise.SpecificExerciseViewModel;
import com.example.gymscape.ui.exerciselist.ExerciseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewExerciseActivity extends AppCompatActivity {

    private NewExerciseViewModel viewModel;

    EditText nameField;
    EditText descriptionField;
    Button selectPhoto;
    Button cancelExercise;
    ImageView previewImage;

    int category;

    File mediaFile;
    final int CAMERA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(NewExerciseViewModel.class);

        this.getSupportActionBar().setTitle("Add Exercise");
        this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(this, R.color.darkBlue)));

        Intent intent = getIntent();
        category = intent.getIntExtra(UsedEnums.CATEGORY.toString(), 0);

        nameField = findViewById(R.id.nameEditText);
        descriptionField = findViewById(R.id.descriptionEditText);
        selectPhoto = findViewById(R.id.imageButton);
        cancelExercise = findViewById(R.id.cancelButton);
        previewImage = findViewById(R.id.previewImage);

        cancelExercise.setOnClickListener(v -> {
            Intent backIntent = new Intent(this, ExerciseActivity.class);
            backIntent.putExtra(UsedEnums.CATEGORY.toString(), category);
            startActivity(backIntent);
            finish();
        });

        selectPhoto.setOnClickListener(v -> {
            //@TODO: full resolution picture
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        });
    }

    public void saveExercise(View v)
    {
        if(nameField.getText().toString().length() > 20)
            Toast.makeText(this, "Exercise name is too long.", Toast.LENGTH_SHORT).show();
        else if(nameField.getText().toString().isEmpty() || descriptionField.getText().toString().isEmpty())
            Toast.makeText(this, "Description or/and exercise name are empty.", Toast.LENGTH_SHORT).show();
        else if(!viewModel.getPictureTaken().getValue())
            Toast.makeText(this, "You must take a picture of exercise.", Toast.LENGTH_SHORT).show();
        else {
            storeImage(viewModel.getExercisePhoto().getValue());
            viewModel.insert(new Exercise(nameField.getText().toString(), category, descriptionField.getText().toString(), mediaFile.getAbsolutePath(), true));
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
            viewModel.setPictureTaken(true);
            viewModel.setExercisePhoto((Bitmap) data.getExtras().get("data"));
            previewImage.setImageBitmap(viewModel.getExercisePhoto().getValue());
        }
        else
            viewModel.setPictureTaken(false);
    }

    private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("Image Save", "Error creating media file, check storage permissions: ");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("Image Save", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("Image Save", "Error accessing file: " + e.getMessage());
        }
    }

    private File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");
        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        // Create a media file name (EXERCISE_ddMMyyy_HHmmss.jpg)
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());

        String mImageName="EXERCISE_"+ timeStamp +".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    @Override
    protected void onResume() {
        super.onResume();

        previewImage.setImageBitmap(viewModel.getExercisePhoto().getValue());
    }
}