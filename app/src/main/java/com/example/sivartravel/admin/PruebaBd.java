package com.example.sivartravel.admin;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sivartravel.BaseSivarTravel.DatosConexion;
import com.example.sivartravel.BaseSivarTravel.Sqlite_Base;
import com.example.sivartravel.R;

import static com.example.sivartravel.util.Utilidades.Campo_Clave;
import static com.example.sivartravel.util.Utilidades.Campo_Correo;
import static com.example.sivartravel.util.Utilidades.Campo_Nombre;
import static com.example.sivartravel.util.Utilidades.Tabla_Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PruebaBd#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PruebaBd extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PruebaBd() {
        // Required empty public constructor
    }

    Button btnCargar;

    //Creamos una instacia de nuestra base de datos
    // Sqlite_Base helper=new Sqlite_Base(getContext(), DatosConexion.NOMBREBD,null, DatosConexion.VERSION);


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PruebaBd.
     */
    // TODO: Rename and change types and number of parameters
    public static PruebaBd newInstance(String param1, String param2) {
        PruebaBd fragment = new PruebaBd();
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
        return inflater.inflate(R.layout.fragment_prueba_bd, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btnCargar=view.findViewById(R.id.btnCargarBD);


        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor mm = validacionUsuarios();
                try {
                    if(mm.getCount()>0){
                        Toast.makeText(getContext(),"TENEMOS DATOS AREAS",Toast.LENGTH_LONG).show();
                    }
                    else{
                        insertarUsuario();
                    }

                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }


        });




    }

    public Cursor validacionUsuarios(){

        Sqlite_Base obj=new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);

        Cursor u = null;

        u=obj.getWritableDatabase().rawQuery("SELECT * FROM "+Tabla_Usuario,new String[]{});

        return u;

    }

    private void insertarUsuario() {
        Sqlite_Base obj=new Sqlite_Base(getContext(),DatosConexion.NOMBREBD,null,DatosConexion.VERSION);
        obj.abrir();
        String comandoL="INSERT INTO "+Tabla_Usuario+"("+Campo_Nombre+","+Campo_Correo+" ,"+Campo_Clave+") " +
                "values('Douglas Calderon','douglas@gmail.com','123')";

        obj.getWritableDatabase().execSQL(comandoL);
        obj.cerrar();
    }





}