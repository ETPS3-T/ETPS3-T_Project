package com.example.sivartravel.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Lugares;

import java.util.List;

public class UbicacionAdapter extends ArrayAdapter<Lugares> {

    private Context context;
    private List<Lugares> lugares;


    public UbicacionAdapter(@NonNull Context context, int resource, @NonNull List<Lugares> objects) {
        super(context, resource, objects);
        this.context=context;
        this.lugares=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView=layoutInflater.inflate(R.layout.contenedor_listalugar,parent, false);

        TextView tvIdlugares=rowView.findViewById(R.id.tvIdlugares);
        TextView tvNombre=rowView.findViewById(R.id.tvNombre);
        TextView tvImagen=rowView.findViewById(R.id.tvImagen);
        TextView tvDescripcion=rowView.findViewById(R.id.tvDescripcion);
        TextView tvLocalizacion=rowView.findViewById(R.id.tvLocalizacion);
        TextView tvidMunicipio=rowView.findViewById(R.id.tvidMunicipio);
        TextView tvidUsuario=rowView.findViewById(R.id.tvidUsuario);


        tvIdlugares.setText(String.format("IdLugares:%s",lugares.get(position).getIdLugares()));
        tvNombre.setText(String.format("Nombre:%s",lugares.get(position).getNombre()));
        tvImagen.setText(String.format("Imagen:%s",lugares.get(position).getImagen()));
        tvDescripcion.setText(String.format("Descripcion:%s",lugares.get(position).getDescripcion()));
        tvLocalizacion.setText(String.format("Localizacion:%s",lugares.get(position).getLocalizacion()));
        tvidMunicipio.setText(String.format("id Municipio:%s",lugares.get(position).getIdMunicipio()));
        tvidUsuario.setText(String.format("id Usuario:%s",lugares.get(position).getIdUsuario()));


        //Implementar metodo para actualizar datos

        return rowView;
    }

}
