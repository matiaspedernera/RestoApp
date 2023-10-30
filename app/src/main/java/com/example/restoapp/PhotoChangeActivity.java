package com.example.restoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PhotoChangeActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICK = 1;
    private ImageView imageselect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_change);

        imageselect = findViewById(R.id.imageselect);

        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        ImageView imageView5 = findViewById(R.id.imageView5);
        ImageView imageView6 = findViewById(R.id.imageView6);
        ImageView imageView7 = findViewById(R.id.imageView7);
        ImageView imageView8 = findViewById(R.id.imageView8);
        ImageView imageView9 = findViewById(R.id.imageView9);
        ImageView imageView10 = findViewById(R.id.imageView10);

        // Configura un OnClickListener para imageView3
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Establece la imagen de imageselect según la imagen seleccionada
                imageselect.setImageResource(R.drawable.image1);
            }
        });

        // Repite el mismo patrón para los otros ImageView
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageselect.setImageResource(R.drawable.image2);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageselect.setImageResource(R.drawable.image3);
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageselect.setImageResource(R.drawable.image4);
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageselect.setImageResource(R.drawable.image5);
            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageselect.setImageResource(R.drawable.image6);
            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageselect.setImageResource(R.drawable.image8);
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageselect.setImageResource(R.drawable.image9);
            }
        });

        ImageButton selectImageBtn = findViewById(R.id.selectImageBtn);

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abre la galería para seleccionar una imagen.
                Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickImage, REQUEST_IMAGE_PICK);
            }
        });

        Button confirmarBtn = findViewById(R.id.confirmarBtn);

        confirmarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageselect.getDrawable() != null) {
                    // Obtén la URI de la imagen seleccionada en imageselect
                    Uri selectedImageUri = Uri.parse("android.resource://com.example.restoapp/drawable/"
                            + getResources().getResourceEntryName(imageselect.getId()));

                    // Envía la URI de la imagen a la actividad ProfileActivity
                    Intent intent = new Intent(PhotoChangeActivity.this, ProfileActivity.class);
                    intent.putExtra("imageUri", selectedImageUri.toString());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            // Muestra la imagen seleccionada en el ImageView "imageselect"
            imageselect.setImageURI(selectedImageUri);
        }
    }
}