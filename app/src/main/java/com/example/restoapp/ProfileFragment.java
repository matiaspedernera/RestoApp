package com.example.restoapp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private static final int REQUEST_IMAGE_CHANGE = 1;
    private ImageView profileImageView;
    private TextView textViewEmail;
    private TextView textViewDNI;
    private Button botonCerrarSesion;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profileImageView = view.findViewById(R.id.profile_image_view);
        textViewEmail = view.findViewById(R.id.textViewName2);
        textViewDNI = view.findViewById(R.id.textViewDni);
        botonCerrarSesion = view.findViewById(R.id.button5);

        sharedPreferences = requireActivity().getSharedPreferences("user_data", Context.MODE_PRIVATE);

        botonCerrarSesion.setOnClickListener(v -> {
            // Cierra la sesión actual del usuario
            FirebaseAuth.getInstance().signOut();

            // Limpia los datos de usuario almacenados en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            // Redirige a la pantalla de inicio de sesión o a donde desees
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finish(); // Opcional, para cerrar la actividad actual
        });

        // Muestra el correo electrónico del usuario si está autenticado
        showUserData();
        showUserDNI();

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

            // Puedes hacer más con la URI de la imagen, como guardarla en SharedPreferences
            // o en otro lugar según tus necesidades.
        }
    }

    @SuppressLint("SetTextI18n")
    private void showUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // El usuario está autenticado, por lo que puedes obtener su correo electrónico
            String userEmail = user.getEmail();

            // Establece el correo electrónico en el TextView
            textViewEmail.setText(userEmail);

            // Guarda el correo electrónico en SharedPreferences para su uso posterior
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", userEmail);
            editor.apply();
        } else {
            // El usuario no está autenticado
            // Realiza alguna acción si es necesario
        }
    }

    @SuppressLint("SetTextI18n")

    private void showUserDNI() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Obtiene el DNI del usuario desde Firebase, reemplaza "dniObtenidoDeFirebase" con la lógica real
            String userDNI = getDNIFromFirebase(user);

            // Guarda el DNI en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("dni", userDNI);
            editor.apply();

            // Muestra el DNI en el TextView
            textViewDNI.setText(" " + userDNI);

            // Agrega un log para verificar que se ha leído el DNI
            Log.d("DNI_LOG", "DNI displayed: " + userDNI);
        } else {
            // El usuario no está autenticado, realiza una acción si es necesario
        }
    }

    private String getDNIFromFirebase(FirebaseUser user) {
        // Aquí debes implementar la lógica para obtener el DNI del usuario desde Firebase
        // Esto dependerá de cómo almacenes el DNI en Firebase, puede ser en una base de datos en tiempo real o Firestore, por ejemplo.
        // Reemplaza "dniObtenidoDeFirebase" con la lógica real para obtener el DNI del usuario.
        // Por ejemplo, si estás usando Firestore, podrías usar una consulta para obtener el DNI del usuario.
        String dniObtenidoDeFirebase = ""; // Reemplaza esto con la lógica real

        return dniObtenidoDeFirebase;
    }

}
