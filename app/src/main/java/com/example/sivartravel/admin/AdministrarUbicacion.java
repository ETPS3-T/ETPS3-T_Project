package com.example.sivartravel.admin;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Departamentos;
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.entidades.Municipios;
import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.entidades.Usuarios;
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
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AdministrarUbicacion extends Fragment {

    TextView txtDatosI,txtFecha,txtHora,txtHora1;
    Button btnListaUbicacion,btnGuardarUbicacion;
    EditText EdtTransporte,EdtDestino,EdtFecha,EdtHoraSalida,EdtHoraRegreso,EdtCosto,EdtTelefono;
    private int dia, mes, anio, hora, minutos;

    Municipios MunObject = new Municipios();
    Departamentos DepartObject = new Departamentos();
    Usuarios UserObject = new Usuarios();
    Lugares l=new Lugares();



    public AdministrarUbicacion() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_administrar_ubicacion, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtDatosI= view.findViewById(R.id.txtDatosI);
        btnListaUbicacion=view.findViewById(R.id.btnListaUbicacion);
        btnGuardarUbicacion=view.findViewById(R.id.btnGuardarUbicacion);
        txtFecha=view.findViewById(R.id.txtFecha);
        txtHora=view.findViewById(R.id.txtHora);
        txtHora1=view.findViewById(R.id.txtHora1);

        EdtTransporte=view.findViewById(R.id.EdtTransporte);
        EdtDestino=view.findViewById(R.id.EdtDestino); //Borrar!
        EdtFecha=view.findViewById(R.id.EdtFecha);
        EdtHoraSalida=view.findViewById(R.id.EdtHoraSalida);
        EdtHoraRegreso=view.findViewById(R.id.EdtHoraRegreso);
        EdtCosto=view.findViewById(R.id.EdtCosto);
        EdtTelefono=view.findViewById(R.id.EdtTelefono);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        txtFecha.setOnClickListener(this::AbrirC);
        txtHora.setOnClickListener(this::AbrirHora);
        txtHora1.setOnClickListener(this::AbrirHora1);


        DepartObject.setIdDepartamentos(4);
        DepartObject.setDepartamentos("Ahuachapán");

        MunObject.setIdMunicipio(6);
        MunObject.setIdDepartamentos(DepartObject);
        MunObject.setMunicipio("San Francisco Menendez");


        UserObject.setIdUsuario(1);
        UserObject.setNombre("Douglas Isaias");
        UserObject.setApellido("Valle Ortíz");
        UserObject.setCorreo("2516122016@mail.utec.edu.sv");
        UserObject.setClave("admin");
        UserObject.setTipo("1");
        UserObject.setEstado(1);

        l.setIdLugares(1);
        l.setNombre("El Imposible");
        l.setImagen("https://i.pinimg.com/736x/37/c3/ad/37c3adaa5545c43918ae069428398365.jpg");
        l.setDescripcion("El Parque Nacional El Imposible es un parque nacional en El Salvador. Fue creado el 1 de enero de 1989 y cubre un área de 5,000 hectáreas. Esta área natural protegida es considerada la reliquia natural más importante del país por ser un ecosistema amenazado a nivel mundial (bosque tropical seco y tropical seco premontano), pero también por ser uno de los últimos refugios para una comunidad increíblemente diversa de vida silvestre, fuente de recursos hídricos para la zona y poseedor de belleza es");
       // l.setLocalizacion("x= 13.8332149, y= -89.9368555");
        l.setIdMunicipio(MunObject);
        l.setIdUsuario(UserObject);

        btnGuardarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validar()) return ;
                else
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


    public void AbrirHora(View view) {
        final Calendar c=Calendar.getInstance();
        hora=c.get(Calendar.HOUR_OF_DAY);
        minutos=c.get(Calendar.MINUTE);

        TimePickerDialog tmd=new TimePickerDialog(getContext(), R.style.DialogTheme ,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                if (hourOfDay==0){
                    if (minute==0) {
                        EdtHoraSalida.setText("00:00");
                    }
                    else {
                        EdtHoraSalida.setText("00:"+ minute);
                    }

                }

                else if(minute==0) {
                    if (hourOfDay==0) {
                        EdtHoraSalida.setText("00:00");
                    }
                    else {
                        EdtHoraSalida.setText(hourOfDay + ":00");
                    }
                }

                else {
                    EdtHoraSalida.setText(hourOfDay+ ":"+ minute);
                }


            }
        },hora,minutos,true);

        tmd.show();


    }

    public void AbrirHora1(View view) {
        final Calendar c=Calendar.getInstance();
        hora=c.get(Calendar.HOUR_OF_DAY);
        minutos=c.get(Calendar.MINUTE);

        TimePickerDialog tmd1=new TimePickerDialog(getContext(), R.style.DialogTheme ,new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                if (hourOfDay==0){
                    if (minute==0) {
                        EdtHoraRegreso.setText("00:00");
                    }
                    else {
                        EdtHoraRegreso.setText("00:"+ minute);
                    }

                }

                else if(minute==0) {
                    if (hourOfDay==0) {
                        EdtHoraRegreso.setText("00:00");
                    }
                    else {
                        EdtHoraRegreso.setText(hourOfDay + ":00");
                    }
                }

                else {
                    EdtHoraRegreso.setText(hourOfDay+ ":"+ minute);
                }
            }
        },hora,minutos,true);

        tmd1.show();


    }


    public void AbrirC(View view) {
        final Calendar c=Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        anio=c.get(Calendar.YEAR);

        DatePickerDialog dpd=new DatePickerDialog(getContext(), R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                EdtFecha.setText(year + "-0" + (month+1) + "-" + dayOfMonth);
            }
        }, 2021,mes,dia);

        dpd.show();

    }


    private boolean validar() {
        String transpor = EdtTransporte.getText().toString().trim();
        String destinos = EdtDestino.getText().toString().trim();
        String fecha = EdtFecha.getText().toString().trim();
        String horaSalida = EdtHoraSalida.getText().toString().trim();
        String horaRegreso = EdtHoraRegreso.getText().toString().trim();
        String costo = EdtCosto.getText().toString().trim();
        String telefono=EdtTelefono.getText().toString().trim();


        if (transpor.equals("")) {
            EdtTransporte.setError("Ingrese un transporte");
            return false;
        }
        if (destinos.equals("")) {
            EdtDestino.setError("Digite destino");
            return false;
        }
        if (fecha.equals("")) {
            EdtFecha.setError("Seleccione una fecha");
            return false;
        }

        if (horaSalida.equals("")) {
            EdtHoraSalida.setError("Seleccione una hora");
            return false;
        }
        if (horaRegreso.equals("")) {
            EdtHoraRegreso.setError("Seleccione una hora");
            return false;
        }
        if (costo.equals("")) {
            EdtCosto.setError("Ingrese un costo");
            return false;
        }

        if (telefono.equals("")) {
            EdtTelefono.setError("Ingrese un Telefono");
            return false;
        }


        return true;
    }


    public void AgregarTransporte() {


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
       /* t.setIdTransporte(null);

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
        t.setIdLugares(l);
        t.setIdUsuario(1); */


       try {

           Date fech = formatter.parse(EdtFecha.getText().toString());
           Transporte t = new Transporte(l,EdtTransporte.getText().toString(),EdtFecha.getText().toString(), EdtHoraSalida.getText().toString(),
                   EdtHoraRegreso.getText().toString(), EdtCosto.getText().toString(), EdtTelefono.getText().toString(), 1);
           TransporteApo service = RetrofitClient.getSOTransporte();
           Call<Transporte> repos = service.addTransporte(t);
           System.out.println(t.toString());
           try {
               Toast.makeText(getContext(), "Registro Agregado satisfactoriamente",
                       Toast.LENGTH_LONG).show();
               repos.execute();

           }catch (Exception e){

           }

           /**repos.enqueue(new Callback<Transporte>() {
               @Override
               public void onResponse(Call<Transporte> call, Response<Transporte> response) {

                   try{
                       if (response.code() == 200) {
                           Toast.makeText(getContext(), "Registro Agregado satisfactoriamente",
                                   Toast.LENGTH_LONG).show();

                       } else {
                           Toast.makeText(getContext(), "Error : " + response.code(),
                                   Toast.LENGTH_LONG).show();
                       }
                   }
                   catch (Exception e){
                       e.printStackTrace();
                       System.out.println(e.getStackTrace());
                       System.out.println(e.getCause());
                   }

               }

               @Override
               public void onFailure(Call<Transporte> call, Throwable ti) {
                   System.out.println("No se pudo: " + ti.getMessage());
                   System.out.println(ti.getStackTrace());
                   System.out.println(ti.getCause());
                   for (StackTraceElement e : ti.getStackTrace()) {
                       System.out.println(e.toString());
                   }
               }
           });*/

       }
       catch (Exception e){
           e.printStackTrace();
       }

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