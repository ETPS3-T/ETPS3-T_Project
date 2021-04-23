package com.example.sivartravel.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.restservice.TransporteApo;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdministrarUbicacion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdministrarUbicacion extends Fragment {

    TextView txtDatosI;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdministrarUbicacion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdministrarUbicacion.
     */
    // TODO: Rename and change types and number of parameters
    public static AdministrarUbicacion newInstance(String param1, String param2) {
        AdministrarUbicacion fragment = new AdministrarUbicacion();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_ubicacion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtDatosI= view.findViewById(R.id.txtDatosI);
        ObtenerTransportes();



    }

    private void ObtenerTransportes() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://35.223.84.113/ApiSivar/").addConverterFactory(GsonConverterFactory.create())
                .build();

        TransporteApo transporteApo= retrofit.create(TransporteApo.class);
        Call<List<Transporte>> call=transporteApo.getTransportes();


        call.enqueue(new Callback<List<Transporte>>() {
            @Override
            public void onResponse(Call<List<Transporte>> call, Response<List<Transporte>> response) {
            if(!response.isSuccessful()){
                txtDatosI.setText("Código de respuestas: "+response);
                return;
            }

            List<Transporte> datos=response.body();
           for (Transporte t: datos){
                String registro="";
                registro="Id Transporte:"+t.getIdTransporte();
                txtDatosI.append(registro);
            }

            }

            @Override
            public void onFailure(Call<List<Transporte>> call, Throwable t) {
               txtDatosI.setText(t.getMessage());
            }
        });


    }
}