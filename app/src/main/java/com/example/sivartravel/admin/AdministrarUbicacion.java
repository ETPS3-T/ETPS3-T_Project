package com.example.sivartravel.admin;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.restservice.RetrofitClient;
import com.example.sivartravel.restservice.ServicioApi;
import com.example.sivartravel.restservice.TransporteApo;
import com.example.sivartravel.util.JsonUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdministrarUbicacion extends Fragment {

    TextView txtDatosI;
    Button btnListaUbicacion,btnGuardarUbicacion,btnFecha;
    EditText EdtTransporte,EdtDestino,EdtFecha,EdtHoraSalida,EdtHoraRegreso,EdtCosto,EdtTelefono;
    private int dia, mes, anio, hora, minutos;



    public AdministrarUbicacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_administrar_ubicacion, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtDatosI= view.findViewById(R.id.txtDatosI);
        btnListaUbicacion=view.findViewById(R.id.btnListaUbicacion);
        btnGuardarUbicacion=view.findViewById(R.id.btnGuardarUbicacion);
        btnFecha=view.findViewById(R.id.btnFecha);

        EdtTransporte=view.findViewById(R.id.EdtTransporte);
        EdtDestino=view.findViewById(R.id.EdtDestino); //Borrar!
        EdtFecha=view.findViewById(R.id.EdtFecha);
        EdtHoraSalida=view.findViewById(R.id.EdtHoraSalida);
        EdtHoraRegreso=view.findViewById(R.id.EdtHoraRegreso);
        EdtCosto=view.findViewById(R.id.EdtCosto);
        EdtTelefono=view.findViewById(R.id.EdtTelefono);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        btnFecha.setOnClickListener(this::AbrirC);

        btnGuardarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AgregarTransporte();
            }


        });



        btnListaUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).navigate(R.id.listadoDestinos);

            }
        });


       // ObtenerTransportes();


    }

    public void AbrirC(View view) {
        final Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR);

        DatePickerDialog dpd=new DatePickerDialog(getContext(), R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                EdtFecha.setText(dayOfMonth + "/0" + (month+1) + "/" + year);
            }
        }, 2021,mes,dia);

        dpd.show();

    }

    public void AgregarTransporte() {
        Transporte t=new Transporte();
        Lugares l=new Lugares();
        l.setIdLugares(1);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        t.setNombre(EdtTransporte.getText().toString());
        try {
            t.setFecha(formatter.parse(EdtFecha.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        t.setHoraSalida(EdtHoraSalida.getText().toString());
        t.setHoraRegreso(EdtHoraRegreso.getText().toString());
        t.setCosto(EdtCosto.getText().toString());
        t.setTelefono(EdtTelefono.getText().toString());
        t.setIdUsuario(1);

        t.setIdLugares(l);

        TransporteApo service = RetrofitClient.getSOTransporte();
            Call<Transporte> repos = service.addTransporte(t);

            repos.enqueue(new Callback<Transporte>() {
                @Override
                public void onResponse(Call<Transporte> call, Response<Transporte> response) {
                    if (response.code()==200) {
                        Toast.makeText(getContext(),"Registro Agregado satisfactoriamente",
                                Toast.LENGTH_LONG ).show();

                    } else
                    {
                        Toast.makeText(getContext(),"Error : " + response.code(),
                                Toast.LENGTH_LONG ).show();
                    }
                }

                @Override
                public void onFailure(Call<Transporte> call, Throwable ti) {
                    System.out.println("No se pudo: "+ti.getMessage());
                    System.out.println(ti.getStackTrace());
                    System.out.println(ti.getCause());
                    for (StackTraceElement e:ti.getStackTrace()){
                        System.out.println(e.toString());
                    }
                }
            });

    }

    private void ObtenerTransportes() {

        try {
            TransporteApo service = RetrofitClient.getSOTransporte();
            Call<List<Transporte>> repos = service.getTransportes();

            repos.enqueue(new Callback<List<Transporte>>() {
                @Override
                public void onResponse(Call<List<Transporte>> call, Response<List<Transporte>> response) {
                    List<Transporte> Lista = response.body();
                    System.out.println("SI se pudo"+Lista.size());
                    for (Transporte t : Lista){
                     //   txtDatosI.setText(String.valueOf(t.getIdTransporte()));
                        System.out.println(""+t.getIdTransporte());
                    }
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