package com.example.sivartravel.admin;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.navigation.Navigation;
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
        //TextView tvidUsuario=rowView.findViewById(R.id.tvidUsuario);
        TextView tvidLugares=rowView.findViewById(R.id.tvidLugares);




        tvIdtransporte.setText(String.format("Id Transporte:%s",transporte.get(position).getIdTransporte()));
        tvNombre.setText(String.format("Nombre:%s",transporte.get(position).getNombre()));
        tvFecha.setText(String.format("Fecha:%s",transporte.get(position).getFecha()));
        tvHoraSalida.setText(String.format("Hora Salida:%s",transporte.get(position).getHoraSalida()));
        tvHoraRegreso.setText(String.format("Hora Regreso:%s",transporte.get(position).getHoraRegreso()));
        tvCosto.setText(String.format("Costo:%s",transporte.get(position).getCosto()));
        tvTelefono.setText(String.format("Telefono:%s",transporte.get(position).getTelefono()));
        //tvidUsuario.setText(String.format("id Usuario:%s",transporte.get(position).getIdUsuario()));
        tvidLugares.setText(String.format("Lugar:%s",transporte.get(position).getIdLugares().getNombre()));

        //Implementar metodo para actualizar datos

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("IdTransporte",String.valueOf(transporte.get(position).getIdTransporte()));
                bundle.putString("Nombre",String.valueOf(transporte.get(position).getNombre()));
                bundle.putString("Fecha",String.valueOf(transporte.get(position).getFecha()));
                bundle.putString("HoraS",String.valueOf(transporte.get(position).getHoraSalida()));
                bundle.putString("HoraR",String.valueOf(transporte.get(position).getHoraRegreso()));
                bundle.putString("Costo",String.valueOf(transporte.get(position).getCosto()));
                bundle.putString("Telefono",String.valueOf(transporte.get(position).getTelefono()));
                bundle.putString("IdUsu",String.valueOf(transporte.get(position).getIdUsuario()));
                bundle.putString("IdLugar",String.valueOf(transporte.get(position).getIdLugares().getIdLugares()));


                Navigation.findNavController(v).navigate(R.id.editarTransportes,bundle);
            }
        });

        return rowView;



    }
}
