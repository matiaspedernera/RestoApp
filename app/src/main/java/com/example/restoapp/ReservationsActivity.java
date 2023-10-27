package com.example.restoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ReservationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        Button botonAgregarReserva = findViewById(R.id.botonAgregarReserva);
        botonAgregarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar el Dialog al hacer clic en el botón
                mostrarDialogAgregarReserva();
            }
        });
    }

    // Método para mostrar el Dialog
    private void mostrarDialogAgregarReserva() {
        // Crear un objeto de Dialog
        Dialog dialog = new Dialog(this);

        // Configurar la vista personalizada para el Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_reservation);

        // Configurar el tamaño del Dialog (ajusta según tus necesidades)
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

        // Enlazar elementos de la vista del Dialog si es necesario
        Button botonConfirmarReserva = dialog.findViewById(R.id.botonConfirmarReserva);
        botonConfirmarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        TextView btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cerrar el Dialog
                dialog.dismiss();
            }
        });
        // Mostrar el Dialog
        dialog.show();
    }
}