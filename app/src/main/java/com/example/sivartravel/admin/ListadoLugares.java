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
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.restservice.RetrofitClient;
import com.example.sivartravel.restservice.ServicioApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoLugares extends Fragment {

    List<Lugares> Lista=new ArrayList<Lugares>();
    ListView listView;

    public ListadoLugares() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado_lugares, container, false);
    }

    @Override
    public  void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView=view.findViewById(R.id.listView);

        ObtenerLugares();
    }


    public void ObtenerLugares() {
        try {
            ServicioApi service = RetrofitClient.getSOService();
            Call<List<Lugares>> repos = service.getLugares();

            repos.enqueue(new Callback<List<Lugares>>() {
                @Override
                public void onResponse(Call<List<Lugares>> call, Response<List<Lugares>> response) {
                    Lista = response.body();
                    listView.setAdapter(new UbicacionAdapter(getContext(), R.layout.fragment_listado_lugares,Lista));

                    /*  System.out.println("SI se pudo"+Lista.size());
                    for (Lugares t : Lista){
                        txtDatosI.setText(String.valueOf(t.getIdLugares()));
                        System.out.println(""+t.getIdLugares());
                    } */

                }

                @Override
                public void onFailure(Call<List<Lugares>> call, Throwable t) {
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
