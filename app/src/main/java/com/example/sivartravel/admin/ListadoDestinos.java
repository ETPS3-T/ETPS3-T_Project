package com.example.sivartravel.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.restservice.RetrofitClient;
import com.example.sivartravel.restservice.TransporteApo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListadoDestinos extends Fragment {


    List<Transporte> Lista=new ArrayList<Transporte>();
    ListView listView;

    public ListadoDestinos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listado_destinos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView=view.findViewById(R.id.listView);

        ObtenerTransportes();

    }

    public void ObtenerTransportes() {

        try {
            TransporteApo service = RetrofitClient.getSOTransporte();
            Call<List<Transporte>> repos = service.getTransportes();

            repos.enqueue(new Callback<List<Transporte>>() {
                @Override
                public void onResponse(Call<List<Transporte>> call, Response<List<Transporte>> response) {
                    Lista = response.body();
                    listView.setAdapter(new TransporteAdapter(getContext(),R.layout.fragment_listado_destinos,Lista));


                  /*  System.out.println("SI se pudo"+Lista.size());
                    for (Transporte t : Lista){
                        txtDatosI.setText(String.valueOf(t.getIdTransporte()));
                        System.out.println(""+t.getIdTransporte());
                    } */
                }

                @Override
                public void onFailure(Call<List<Transporte>> call, Throwable t) {
                    System.out.println("No se pudo: "+t.getMessage());
                    System.out.println(t.getStackTrace());
                    System.out.println(t.getCause());
                    for (StackTraceElement e:t.getStackTrace()){
                        System.out.println(e.toString());
                    }
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}