package com.example.sivartravel.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Transporte;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransporteAdapter extends ArrayAdapter<Transporte> {

    private Context context;
    private List<Transporte> transporte;



    public TransporteAdapter(@NonNull Context context, int resource, @NonNull List<Transporte> objects) {
        super(context, resource, objects);
        this.context=context;
        this.transporte=objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView=layoutInflater.inflate(R.layout.contenedor_llistatrans,parent,false);

        TextView tvIdtransporte=rowView.findViewById(R.id.tvIdtransporte);
        TextView tvNombre=rowView.findViewById(R.id.tvNombre);
        TextView tvFecha=rowView.findViewById(R.id.tvFecha);
        TextView tvHoraSalida=rowView.findViewById(R.id.tvHoraSalida);
        TextView tvHoraRegreso=rowView.findViewById(R.id.tvHoraRegreso);
        TextView tvCosto=rowView.findViewById(R.id.tvCosto);
        TextView tvTelefono=rowView.findViewById(R.id.tvTelefono);
        TextView tvidUsuario=rowView.findViewById(R.id.tvidUsuario);
        TextView tvidLugares=rowView.findViewById(R.id.tvidLugares);




        tvIdtransporte.setText(String.format("IdTransporte:%s",transporte.get(position).getIdTransporte()));
        tvNombre.setText(String.format("Nombre:%s",transporte.get(position).getNombre()));
        tvFecha.setText(String.format("Fecha:%s",transporte.get(position).getFecha()));
        tvHoraSalida.setText(String.format("Hora Salida:%s",transporte.get(position).getHoraSalida()));
        tvHoraRegreso.setText(String.format("Hora Regreso:%s",transporte.get(position).getHoraRegreso()));
        tvCosto.setText(String.format("Costo:%s",transporte.get(position).getCosto()));
        tvTelefono.setText(String.format("Telefono:%s",transporte.get(position).getTelefono()));
        tvidUsuario.setText(String.format("id Usuario:%s",transporte.get(position).getIdUsuario()));
        tvidLugares.setText(String.format("id Lugar:%s",transporte.get(position).getIdLugares().getIdLugares()));

        //Implementar metodo para actualizar datos

        return rowView;



    }
}
