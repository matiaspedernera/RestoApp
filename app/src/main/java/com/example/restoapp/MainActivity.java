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

    private EditText nombre;
    private EditText apellido;
    private EditText dni;
    private EditText correo;
    private EditText contrasena;
    private EditText contrasenaConfirmacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //nombre = findViewById(R.id.nombreEditText);
        //apellido = findViewById(R.id.apellidoEditText);
        //dni = findViewById(R.id.dniEditText);
        correo = findViewById(R.id.correoEditText);
        contrasena = findViewById(R.id.contrasenaEditText);
        contrasenaConfirmacion = findViewById(R.id.contrasenaConfirmaEditText);
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);  Metodo que debemos crear en el caso de que el usuario ya este logueado
    }
    //Metodo para registrar usuario con firebase (funciona pero se deberia rehacer)
    public void registrarUsuario(View view) {

        String correoString = correo.getText().toString();
        String contrasenaString = contrasena.getText().toString();

        if (contrasenaString.equals(contrasenaConfirmacion.getText().toString())){

            mAuth.createUserWithEmailAndPassword(correoString, contrasenaString)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(getApplicationContext(), "Usuario Creado con éxito", Toast.LENGTH_LONG).show();

                                FirebaseUser user = mAuth.getCurrentUser();

                                // updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Error al crear usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), "Problemas al crear usuario",Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }

    public void irALogin(View view){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
