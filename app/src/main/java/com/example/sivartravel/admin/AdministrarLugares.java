package com.example.sivartravel.admin;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.StrictMode;
import android.text.Editable;
import android.util.Log;
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
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.entidades.Municipios;
import com.example.sivartravel.entidades.Usuarios;
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
    ArrayList<String> AllDp;
    ArrayList<String> AllMun;
    int idDep = 0;
    int idMuni = 0;
    String Municipio = "";
    String Departamentos = "";
    String NombreLugar = "";
    String Localizacion = "";
    String DescripciónL = "";
    String URLImagen = "";

    Departamentos DepartObject = new Departamentos();
    Municipios MunObject = new Municipios();
    Usuarios UserObject = new Usuarios();

    ServicioApi srv = RetrofitClient.getSOService();


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

        CargarDatosSpinners();
        CargarDatosUsuario();

        SpinDepartamentos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                idDep = position + 1;
                Departamentos = SpinDepartamentos.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        SpinMunicipio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                idMuni = position + 1;
                Municipio = SpinMunicipio.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });


        BtnGuardarLugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Comprobar())
                {
                    DepartObject.setIdDepartamentos(idDep);
                    DepartObject.setDepartamentos(Departamentos);
                    MunObject.setIdMunicipio(idMuni);
                    MunObject.setMunicipio(Municipio);
                    MunObject.setIdDepartamentos(DepartObject);

                    try
                    {
                        Lugares lugares = new Lugares(NombreLugar, URLImagen, DescripciónL, Localizacion, MunObject, UserObject);

                        Call<Lugares> AddPlace = srv.insertPlace(lugares);

                        AddPlace.enqueue(new Callback<Lugares>()
                        {
                            @Override
                            public void onResponse(Call<Lugares> call, Response<Lugares> response)
                            {
                                if(response.code() == 200)
                                {
                                    Toast.makeText(view.getContext().getApplicationContext(), "Lugar agregado satisactoriamente" , Toast.LENGTH_SHORT).show();
                                    EdtDescripcionLugar.setText("");
                                    EdtNombreLugar.setText("");
                                    EdtLocationLugar.setText("");
                                    URLimage.setText("");
                                }
                                else
                                {
                                    Toast.makeText(view.getContext().getApplicationContext(), "Hubo un error " +response.code() , Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Lugares> call, Throwable t)
                            {
                                Toast.makeText(view.getContext().getApplicationContext(), "Fatal error " +t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Ingrese los datos e imagenes requeridas" , Toast.LENGTH_LONG).show();
                }

            }
        });

        BtnListaLugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /* Aqui va la actividad que carga la lista de lugares en MODO ADMIN
                Intent OpenList = new Intent(AdministrarLugares.this, );
                startActivity(OpenList);*/
            }
        });
    }

    public void CargarDatosUsuario()
    {
        UserObject.setIdUsuario(1);
    }

    public void CargarDatosSpinners()
    {

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Call<List<com.example.sivartravel.entidades.Departamentos>> Dep = srv.getDepartamentos();

        try
        {
            Dep.enqueue(new Callback<List<com.example.sivartravel.entidades.Departamentos>>()
            {
                @Override
                public void onResponse(Call<List<Departamentos>> call, Response<List<Departamentos>> response)
                {
                    if(!response.isSuccessful()){Toast.makeText(getActivity().getApplicationContext(), "ERROR "+response.code(), Toast.LENGTH_SHORT).show(); return;}

                    List<Departamentos> Dp = response.body();

                    ObtenerDepartamentos(Dp);

                    ArrayAdapter<String> Adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AllDp);
                    SpinDepartamentos.setAdapter(Adapter);
                }
                @Override
                public void onFailure(Call<List<Departamentos>> call, Throwable t) { }
            });
        }
        catch (Exception e) {e.printStackTrace();}

        Call<List<com.example.sivartravel.entidades.Municipios>> Mun = srv.getMunicipios();

        try
        {
            Mun.enqueue(new Callback<List<com.example.sivartravel.entidades.Municipios>>()
            {
                @Override
                public void onResponse(Call<List<Municipios>> call, Response<List<Municipios>> response)
                {
                    if(!response.isSuccessful()){Toast.makeText(getActivity().getApplicationContext(), "ERROR "+response.code(), Toast.LENGTH_SHORT).show(); return;}

                    List<Municipios> Muni = response.body();

                    ObtenerMunicipios(Muni);

                    ArrayAdapter<String> Adapter2 = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AllMun);
                    SpinMunicipio.setAdapter(Adapter2);
                }
                @Override
                public void onFailure(Call<List<Municipios>> call, Throwable t) { }
            });
        }
        catch (Exception e){ e.printStackTrace();};
    }


    //Metodos propios

    public boolean Comprobar()
    {
        if(!EdtNombreLugar.getText().toString().isEmpty() && !EdtLocationLugar.getText().toString().isEmpty() && !EdtDescripcionLugar.getText().toString().isEmpty() && !URLimage.getText().toString().isEmpty())
        {
            NombreLugar = EdtNombreLugar.getText().toString();
            DescripciónL = EdtDescripcionLugar.getText().toString();
            URLImagen = URLimage.getText().toString();
            Localizacion = EdtLocationLugar.getText().toString();
            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<String> ObtenerDepartamentos(List<Departamentos> List1)
    {
        AllDp = new ArrayList<>();

        for(Departamentos dp1 : List1)
        {
            AllDp.add(dp1.getDepartamentos());
        }

        return AllDp;
    }

    public  ArrayList<String> ObtenerMunicipios(List<Municipios> List2)
    {
        AllMun = new ArrayList<>();

        for(Municipios mun1 : List2)
        {
            AllMun.add(mun1.getMunicipio());
        }

        return  AllMun;
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