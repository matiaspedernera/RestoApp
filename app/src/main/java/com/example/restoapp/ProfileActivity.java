package com.example.restoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CHANGE = 1;
    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileImageView = findViewById(R.id.profile_image_view);

        // Botón para cambiar la imagen de perfil
        Button changeImageButton = findViewById(R.id.change_image_button);

        changeImageButton.setOnClickListener(v -> {
            // Crea un Intent para abrir la actividad PhotoChangeActivity
            Intent changeImageIntent = new Intent(ProfileActivity.this, PhotoChangeActivity.class);

            // Inicia la actividad PhotoChangeActivity para cambiar la imagen de perfil
            startActivityForResult(changeImageIntent, REQUEST_IMAGE_CHANGE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CHANGE && resultCode == RESULT_OK && data != null) {
            // Obtiene la URI de la imagen seleccionada desde PhotoChangeActivity
            Uri selectedImageUri = data.getData();

            // Muestra la imagen en profileImageView
            profileImageView.setImageURI(selectedImageUri);
        }
    }
}
