package com.example.restoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.restoapp.controladores.ReservationBD;
import com.example.restoapp.controladores.SelectListener;
import com.example.restoapp.modelos.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationsActivity extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<Integer> idReserve;
    ReservationBD reservationBD;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);
        init();

        Button botonAgregarReserva = findViewById(R.id.botonAgregarReserva);
        botonAgregarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarDialogAgregarReserva();
            }
        });

        Reservation nuevaReserva = new Reservation(
                0,
                3,
                "1-11-2023",
                "Reunion",
                15,
                "Sin observacion",
                "Pendiente"
        );

        reservationBD.agregar(nuevaReserva);
    }


    private void mostrarDialogAgregarReserva() {
        Dialog dialog = new Dialog(this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_reservation);

        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

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
        dialog.show();
    }

    private void init(){
        context = this.getApplicationContext();
        reservationBD = new ReservationBD(context);
        listView = findViewById(R.id.reservation_list);
        fillListView();
    }

    private void fillListView() {
        idReserve = new ArrayList<Integer>();

        List<Reservation> reservationList = reservationBD.lista();

        ReservationAdapter adapter = new ReservationAdapter(this, R.layout.reservation_item, reservationList);

        listView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Reservation reservation = reservationList.get(i); // Obtén la reserva en la posición i
                Bundle bolsa = new Bundle();
                bolsa.putInt("id", reservation.getId());
                bolsa.putInt("number_of_people", reservation.getNumber_of_people());
                bolsa.putString("dateAndTime", reservation.getDateAndTime());
                bolsa.putLong("created", reservation.getCreated().getTime());
                bolsa.putInt("table", reservation.getTable());
                bolsa.putString("observations", reservation.getObservations());
                bolsa.putString("status", reservation.getStatus());

            }
        });

    }

    @Override
    public void onItemClick(int id) {

    }
}