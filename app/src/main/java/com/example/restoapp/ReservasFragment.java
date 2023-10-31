package com.example.restoapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.restoapp.R;
import com.example.restoapp.ReservationAdapter;
import com.example.restoapp.controladores.ReservationBD;
import com.example.restoapp.controladores.SelectListener;
import com.example.restoapp.modelos.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservasFragment extends Fragment {
    private ListView listView;
    private ArrayList<Integer> idReserve;
    private ReservationBD reservationBD;
    private Context context;

    public ReservasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = requireContext();
        reservationBD = new ReservationBD(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);
        listView = view.findViewById(R.id.reservation_list);

        fillListView(view);

        Button botonAgregarReserva = view.findViewById(R.id.botonAgregarReserva);
        botonAgregarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogAgregarReserva();
            }
        });

        return view;
    }

    private void fillListView(View view) {
        idReserve = new ArrayList<Integer>();

        List<Reservation> reservationList = reservationBD.lista();

        ReservationAdapter adapter = new ReservationAdapter(requireActivity(), R.layout.reservation_item, reservationList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Reservation reservation = reservationList.get(i);
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

    private void mostrarDialogAgregarReserva() {
        Dialog dialog = new Dialog(requireContext());

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
                // Agregar aquí la lógica para confirmar la reserva
                dialog.dismiss();
            }
        });

        TextView btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
