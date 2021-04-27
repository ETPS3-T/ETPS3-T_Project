package com.example.sivartravel.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Departamentos;
import com.example.sivartravel.entidades.Municipios;
import com.example.sivartravel.restservice.RetrofitClient;
import com.example.sivartravel.restservice.ServicioApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdministrarLugares extends Fragment
{
    private Button BtnGuardarLugar, BtnListaLugar;
    private EditText EdtNombreLugar, EdtLocationLugar, EdtDescripcionLugar, URLimage;

    private Spinner SpinDepartamentos, SpinMunicipio;
    String StringDepa;

    ArrayList<Departamentos> AllDp = new ArrayList<>();
    ArrayList<Municipios> AllMun = new ArrayList<>();

    public AdministrarLugares() { }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        BtnGuardarLugar = view.findViewById(R.id.BtnGuardarLugar);
        BtnListaLugar = view.findViewById(R.id.BtnListaLugar);
        EdtNombreLugar = view.findViewById(R.id.EdtNombreLugar);
        EdtLocationLugar = view.findViewById(R.id.EdtLocationLugar);
        EdtDescripcionLugar = view.findViewById(R.id.EdtDescripcionLugar);
        URLimage = view.findViewById(R.id.URLimage);
        SpinDepartamentos = view.findViewById(R.id.SpinDepartamentos);
        SpinMunicipio = view.findViewById(R.id.SpinMunicipio);

        ServicioApi srv = RetrofitClient.getSOService();

        Call<List<Departamentos>> Dep = srv.getDepartamentos();

        try
        {
            Dep.enqueue(new Callback<List<Departamentos>>()
            {
                @Override
                public void onResponse(Call<List<Departamentos>> call, Response<List<Departamentos>> response)
                {
                    if(!response.isSuccessful()){Toast.makeText(getActivity().getApplicationContext(), "ERROR "+response.code(), Toast.LENGTH_SHORT).show(); return;}

                    List<Departamentos> Dp = response.body();

                    for(Departamentos Depart : Dp)
                    {
                        AllDp.add(new Departamentos(Depart.getDepartamentos()));
                        EdtDescripcionLugar.setText(Depart.getDepartamentos());
                    }

                    ArrayAdapter<Departamentos> Adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AllDp);
                    SpinDepartamentos.setAdapter(Adapter);
                }
                @Override
                public void onFailure(Call<List<Departamentos>> call, Throwable t) { }
            });
        }
        catch (Exception e) {e.printStackTrace();}

        Call<List<Municipios>> Mun = srv.getMunicipios();

        Mun.enqueue(new Callback<List<Municipios>>()
        {
            @Override
            public void onResponse(Call<List<Municipios>> call, Response<List<Municipios>> response)
            {
                if(!response.isSuccessful()){Toast.makeText(getActivity().getApplicationContext(), "ERROR "+response.code(), Toast.LENGTH_SHORT).show(); return;}

                List<Municipios> Muni = response.body();

                for(Municipios M1: Muni)
                {
                    AllMun.add(new Municipios(M1.getMunicipio()));
                }

                ArrayAdapter<Municipios> MuniAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item , AllMun);
                SpinMunicipio.setAdapter(MuniAdapter);
            }

            @Override
            public void onFailure(Call<List<Municipios>> call, Throwable t) { }
        });

        SpinDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                StringDepa = parent.getItemAtPosition(position).toString();
                EdtDescripcionLugar.setText(StringDepa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        BtnGuardarLugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v
            )
            {
                Comprobar();
            }
        });

        BtnListaLugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*
                Intent OpenList = new Intent(AdministrarLugares.this, );
                startActivity(OpenList);*/
            }
        });
    }

    //Comprobar que se haya seleccionado algo

    public boolean Comprobar()
    {
        if(!EdtNombreLugar.getText().toString().isEmpty() && !EdtLocationLugar.getText().toString().isEmpty() && !EdtDescripcionLugar.getText().toString().isEmpty() && !URLimage.getText().toString().isEmpty())
        {
            return true;
        }
        else
        {
            Toast.makeText(getActivity().getApplicationContext(), "Ingrese los datos e imagenes requeridas" , Toast.LENGTH_LONG).show();
            return false;
        }
    }

    //Otros metodos del fragment que no se usarán
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static AdministrarLugares newInstance(String param1, String param2)
    {
        AdministrarLugares fragment = new AdministrarLugares();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_lugares, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
}