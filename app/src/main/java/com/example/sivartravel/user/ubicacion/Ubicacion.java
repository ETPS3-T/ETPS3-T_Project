package com.example.sivartravel.user.ubicacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sivartravel.R;

public class Ubicacion extends Fragment {
    private Spinner spinnerTransporte;
    private Spinner spinnerDestino;
    //add lines
    String [] transportes = {"Seleccione","Bus", "Microbus","Automovil"};
    String [] destinos = {"Seleccione","Nahuizalco","Suchitoto","Apaneca"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.user_ubicacion, container, false);
        spinnerTransporte=root.findViewById(R.id.spinnerTransporte);
        spinnerDestino=root.findViewById(R.id.spinnerDestino);

        spinnerTransporte.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, transportes));
        spinnerDestino.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, destinos));

        return root;
    }
}