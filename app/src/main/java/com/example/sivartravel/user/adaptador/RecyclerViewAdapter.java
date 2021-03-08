/**
 * Travel v1
 * @author Isaias Ortiz
 */

package com.example.sivartravel.user.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.R;
import com.example.sivartravel.user.entitys.LugaresEntity;
import com.example.sivartravel.user.lugares.Lugares;
import com.google.android.material.transition.Hold;

import java.util.List;

/**
 * @author Isaias Ortiz
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<LugaresEntity> listaLugares;
    private InterfaceClickListener selectedItemListener;

    public RecyclerViewAdapter(List<LugaresEntity> listaLugares, InterfaceClickListener selectedItemListener) {
        this.selectedItemListener= selectedItemListener;
        this.listaLugares = listaLugares;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.lugaresview, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        LugaresEntity lugar = listaLugares.get(position);
        //Aqui set a los componentes
        holder.imagen.setImageResource(listaLugares.get(position).getImagen());
        holder.lugar.setText(listaLugares.get(position).getLugar());
        holder.departamento.setText(listaLugares.get(position).getDepartamento());
        System.out.println(lugar.getDepartamento());
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;
        TextView departamento;
        TextView lugar;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            departamento=(TextView) itemView.findViewById(R.id.tvDepartamento);
            lugar = (TextView) itemView.findViewById(R.id.tvLugar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItemListener.OnItemClick(getAdapterPosition());
                }
            });
          }
    }
}
