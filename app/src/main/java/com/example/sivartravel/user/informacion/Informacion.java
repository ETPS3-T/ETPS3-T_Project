package com.example.sivartravel.user.informacion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sivartravel.R;

public class Informacion extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_informacion, container, false);
        String desarroladores="Calderón Guadrón, Douglas Alexander  25-2865-2016\n" +
                "Cerón Arias, Johanna Isabel 25-1656-2016 \n" +
                "Córdova Guevara, Dennys Francisco 25-3391-2016\n" +
                "Elías Portillo, Ricardo Israel 25-2060-2015\n" +
                "López Hernández, José Ernesto 25-1551-2016\n" +
                "Pascasio Calderón, José Daniel 25-1670-2016 \n" +
                " Siliezar López, Lissette Carolina 25-2396-2016 \n" +
                "Valle Ortiz, Douglas Isaías 25-1612-2016 \n" +
                "Vásquez Chávez, Kevin Alexander 25-2281-2014\n";
        ((TextView)root.findViewById(R.id.idDesarroladores)).setText(desarroladores);

        return root;
    }
}