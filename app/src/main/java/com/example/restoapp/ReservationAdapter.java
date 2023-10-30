package com.example.restoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.restoapp.modelos.Reservation;
import java.util.List;

public class ReservationAdapter extends ArrayAdapter<Reservation> {
    private int layoutResourceId;

    public ReservationAdapter(Context context, int layoutResourceId, List<Reservation> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReservationHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ReservationHolder();
            holder.textViewId = row.findViewById(R.id.textViewId);
            holder.textViewPersonas = row.findViewById(R.id.textViewPersonas);
            holder.textViewFecha = row.findViewById(R.id.textViewFecha);
            holder.textViewCreada = row.findViewById(R.id.textViewCreada);
            holder.textViewTipo = row.findViewById(R.id.textViewTipo);
            holder.textViewMesa = row.findViewById(R.id.textViewMesa);
            holder.textViewObservacion = row.findViewById(R.id.textViewObservacion);
            holder.textViewEstado = row.findViewById(R.id.textViewEstado);


            row.setTag(holder);
        } else {
            holder = (ReservationHolder) row.getTag();
        }

        Reservation reservation = getItem(position);
        holder.textViewId.setText("# " + reservation.getId());
        holder.textViewPersonas.setText("Personas: " + reservation.getNumber_of_people());
        holder.textViewFecha.setText("Fecha: " + reservation.getDateAndTime());
        holder.textViewCreada.setText("Enviada: " + reservation.getCreated());
        holder.textViewTipo.setText("Tipo: " + reservation.getType());
        holder.textViewMesa.setText("Mesa: " + reservation.getTable());
        holder.textViewObservacion.setText("Observacion: " + reservation.getObservations());
        holder.textViewEstado.setText("Estado: " + reservation.getStatus());


        return row;
    }

    static class ReservationHolder {
        TextView textViewId;
        TextView textViewPersonas;
        TextView textViewFecha;
        TextView textViewCreada;
        TextView textViewTipo;
        TextView textViewMesa;
        TextView textViewObservacion;
        TextView textViewEstado;

    }
}
