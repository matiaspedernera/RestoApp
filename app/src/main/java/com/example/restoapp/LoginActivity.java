package com.example.restoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.nombreEditText);
        etPassword = findViewById(R.id.contrasenaEditText);
        btnLogin = findViewById(R.id.IngresarBtn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() && password.equals("1234")) {
                    Toast.makeText(LoginActivity.this, "Se requiere un nombre de usuario", Toast.LENGTH_SHORT).show();
                } else if (username.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Se requiere un nombre de usuario", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Se requiere una contraseña", Toast.LENGTH_SHORT).show();
                } else if (username.equals("restoapp") && password.equals("1234")) {
                    // Las credenciales son válidas, inicia sesión
                    Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                    // Iniciar la actividad DishesActivity
                    Intent intent = new Intent(LoginActivity.this, DishesActivity.class);
                    startActivity(intent);
                } else {
                    // Credenciales incorrectas, muestra un mensaje de error.
                    Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
