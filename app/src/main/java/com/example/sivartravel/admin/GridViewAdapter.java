package com.example.sivartravel.admin;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sivartravel.R;

import java.util.List;

public class GridViewAdapter extends BaseAdapter
{
    Context context;
    List<Uri> listaImagenes;
    LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, List<Uri> listaImagenes)
    {
        this.context = context;
        this.listaImagenes = listaImagenes;
    }

    @Override
    public int getCount()
    {
        return listaImagenes.size();
    }

    @Override
    public Object getItem(int i)
    {
        return listaImagenes.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        if(view == null)
        {
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.grid_pics, null);
        }

        ImageView ivImagen = view.findViewById(R.id.fotosCargadas);
        ImageButton ibtnEliminar = view.findViewById(R.id.Delete);

        Glide.with(context).load(listaImagenes.get(i)).into(ivImagen);

        ibtnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                listaImagenes.remove(i);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
