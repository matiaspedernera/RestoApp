package com.example.restoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_IMAGE_CHANGE = 1;
    private ImageView profileImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImageView = view.findViewById(R.id.profile_image_view);

        // Botón para cambiar la imagen de perfil
        Button changeImageButton = view.findViewById(R.id.change_image_button);

        changeImageButton.setOnClickListener(v -> {
            // Crea un Intent para abrir la actividad PhotoChangeActivity
            Intent changeImageIntent = new Intent(requireContext(), PhotoChangeActivity.class);

            // Inicia la actividad PhotoChangeActivity para cambiar la imagen de perfil
            startActivityForResult(changeImageIntent, REQUEST_IMAGE_CHANGE);
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CHANGE && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            // Obtiene la URI de la imagen seleccionada desde PhotoChangeActivity
            Uri selectedImageUri = data.getData();

            // Muestra la imagen en profileImageView
            profileImageView.setImageURI(selectedImageUri);
        }
    }

    public void updateProfileImage(Uri selectedImageUri) {
    }
}
