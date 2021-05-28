/**
 * Travel v1
 * @author Isaias Ortiz
 */

package com.example.sivartravel.user.adaptador;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.R;
import com.example.sivartravel.user.entitys.LugaresEntity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
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
        //holder.imagen.setImageURI(listaLugares.get(position).getImagen());
        //holder.imagen.setImageResource(listaLugares.get(position).getImagen());
        try {

            //Uri uri = Uri.parse(listaLugares.get(position).getImagen());

            //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.imagen.setImageBitmap(getImageBitmap(listaLugares.get(position).getImagenUri()));
            //holder.imagen.setImageURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.lugar.setText(listaLugares.get(position).getLugar());
        holder.departamento.setText(listaLugares.get(position).getDepartamento());
        System.out.println(lugar.getDepartamento());
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
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
