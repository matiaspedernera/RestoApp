package com.example.restoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class activity_redes extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST_CODE = 1;
    private EditText editTextComentario;
    private ImageView imageViewSelectedFile;
    private List<Uri> selectedFiles = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redes);

        editTextComentario = findViewById(R.id.editTextComentario);
        imageViewSelectedFile = findViewById(R.id.imageView);

        editTextComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextComentario.setText(""); // Borra el contenido del EditText al hacer clic
            }
        });

        Button btnEnviarComentario = findViewById(R.id.btnEnviarComentario);
        btnEnviarComentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtiene el comentario del EditText
                String comentario = editTextComentario.getText().toString();

                if (!comentario.isEmpty()) {
                    // Muestra un mensaje en forma de Toast
                    Toast.makeText(activity_redes.this, "Mensaje Enviado", Toast.LENGTH_SHORT).show();
                    // Borra el contenido del EditText
                    editTextComentario.setText("");
                    // Borra la imagen seleccionada
                    imageViewSelectedFile.setImageResource(0);
                    selectedFiles.clear();
                }
            }
        });

        ImageButton btnSelectFile = findViewById(R.id.btnSelectFile);
        btnSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilePicker();
            }
        });

        ImageButton btnSelectFoto = findViewById(R.id.btnSelectFoto);
        btnSelectFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Aquí puedes implementar la lógica para abrir la selección de fotos
            }
        });

        ImageButton btnOpenFacebook = findViewById(R.id.btnOpenFacebook);
        btnOpenFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFacebookProfile("nombre_de_usuario_facebook");
            }
        });

        ImageButton btnOpenWhatsApp = findViewById(R.id.btnOpenWhatsApp);
        btnOpenWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWhatsAppChat("número_de_teléfono");
            }
        });

        ImageButton btnOpenInstagram = findViewById(R.id.btnOpenInstagram);
        btnOpenInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openInstagramProfile("nombre_de_usuario_instagram");
            }
        });

        ImageButton imageButton14 = findViewById(R.id.imageButton14);
        imageButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea una instancia del fragmento InicioFragment
                InicioFragment inicioFragment = new InicioFragment();

                // Obtiene el FragmentManager
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Comienza una transacción de fragmentos
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Reemplaza el fragmento actual por InicioFragment
                transaction.replace(R.id.fragment_container, inicioFragment);

                // Realiza la transacción
                transaction.commit();
            }
        });
    }

    private void openWhatsAppChat(String phoneNumber) {
        // Implementación de abrir chat de WhatsApp
    }

    private void openFacebookProfile(String facebookUsername) {
        // Implementación de abrir perfil de Facebook
    }

    private void openInstagramProfile(String instagramUsername) {
        // Implementación de abrir perfil de Instagram
    }

    private void openFilePicker() {
        // Implementación de abrir selección de archivos
    }
}
