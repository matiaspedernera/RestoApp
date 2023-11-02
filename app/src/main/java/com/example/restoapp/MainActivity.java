package com.example.restoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText correo;
    private EditText contrasena;
    private EditText contrasenaConfirmacion;
    private EditText nombreEditText; // Agrega la referencia al EditText nombreEditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.correoEditText);
        contrasena = findViewById(R.id.contrasenaEditText);
        contrasenaConfirmacion = findViewById(R.id.contrasenaConfirmaEditText);
        nombreEditText = findViewById(R.id.nombreEditText); // Asigna la referencia a nombreEditText
    }

    public void registrarUsuario(View view) {
        final String correoString = correo.getText().toString();
        final String contrasenaString = contrasena.getText().toString();
        String contrasenaConfirmacionString = contrasenaConfirmacion.getText().toString();

        if (contrasenaString.equals(contrasenaConfirmacionString)) {
            mAuth.createUserWithEmailAndPassword(correoString, contrasenaString)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Usuario Creado con éxito", Toast.LENGTH_LONG).show();
                                Log.d("RegistroExitoso", "Usuario registrado: " + user.getEmail());

                                // Puedes redirigir al usuario a la siguiente actividad aquí
                                String nombreIngresado = nombreEditText.getText().toString(); // Obtiene el nombre ingresado
                                Intent intent = new Intent(MainActivity.this, DishesActivity.class);
                                intent.putExtra("nombreUsuario", nombreIngresado); // Pasa el nombre a la siguiente actividad
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al crear usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("RegistroError", "Error al registrar usuario: " + task.getException().getMessage());
                            }
                        }
                    });
        } else {
            int Toast_SHORT = 0;
            Toast.makeText(this, "Las contraseñas no coinciden", Toast_SHORT).show();
        }
    }

    public void irALogin(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
