package com.example.sivartravel.user.ubicacion;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Departamentos;
import com.example.sivartravel.entidades.Lugares;
import com.example.sivartravel.entidades.Municipios;
import com.example.sivartravel.entidades.Transporte;
import com.example.sivartravel.restservice.RetrofitClient;
import com.example.sivartravel.restservice.ServicioApi;
import com.example.sivartravel.restservice.TransporteApo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ubicacion extends Fragment {
    private TextView tvFechaK;
    private TextView tvHoraSK;
    private TextView tvHoraSR;
    private TextView tvCostoK;
    private TextView tvTelefonoK;
    private Spinner spinnerDestino;

    ArrayList<String> AllLug;
    int idLug = 0;
    String Lugares = "";
    Lugares LugObject = new Lugares();

    ServicioApi servicioApi = RetrofitClient.getSOService();

    //add lines
    String [] transportes = {"Seleccione","Bus", "Microbus","Automovil"};
    String [] destinos = {"Seleccione","Nahuizalco","Suchitoto","Apaneca"};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.user_ubicacion, container, false);
        tvFechaK=root.findViewById(R.id.tvFechaK);
        tvHoraSK=root.findViewById(R.id.tvHoraSK);
        tvHoraSR=root.findViewById(R.id.tvHoraSR);
        tvCostoK=root.findViewById(R.id.tvCostoK);
        tvTelefonoK=root.findViewById(R.id.tvTelefonoK);
        spinnerDestino=root.findViewById(R.id.spinnerDestino);

        CargarDatosSpinners();

        spinnerDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                idLug = position + 1;
                Lugares = spinnerDestino.getSelectedItem().toString();
                //int v1=Integer.parseInt(idLug);
                buscarPorId(idLug);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        return root;
    }


    private void buscarPorId(int codigo) {

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        ServicioApi srv = RetrofitClient.getSOService();

        //construimos una instancia con retrofit y le pasamos la URL base
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://35.239.238.56/ApiSivar/").addConverterFactory(GsonConverterFactory.create()).build();

        //usamos la intrfaz productoAPI
        //ServicioApi productoAPI=retrofit.create(ServicioApi.class);
        //ServicioApi service = RetrofitClient.getSOService();

        //realizamos la llamada HTTP implementando el creado en PorductoAPI
        //Call<List<Transporte>> call=productoAPI.getTransporte(codigo);
        Call<List<com.example.sivartravel.entidades.Lugares>> repos = srv.getLugares();

        //recibimos la respuesta asincrona
        repos.enqueue(new Callback<List<com.example.sivartravel.entidades.Lugares>>() {
            @Override
            public void onResponse(Call<List<com.example.sivartravel.entidades.Lugares>> call, Response<List<com.example.sivartravel.entidades.Lugares>> response) {
                try {
                    //if (response.code() == 200){
                        //creamos un obj de la clase transporte y accedemos al cuerpo de la respuesta
                        Transporte transporte = (Transporte) response.body();
                        tvFechaK.setText(transporte.getFecha());
                        tvHoraSK.setText(transporte.getHoraSalida());
                        tvHoraSR.setText(transporte.getHoraRegreso());
                        tvCostoK.setText(transporte.getCosto());
                        tvTelefonoK.setText(transporte.getTelefono());
                    //}
                } catch (Exception exception){
                    Toast.makeText(getLayoutInflater().getContext().getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<com.example.sivartravel.entidades.Lugares>> call, Throwable t) {
                Toast.makeText(getLayoutInflater().getContext().getApplicationContext(), "Error de conexion", Toast.LENGTH_SHORT).show();
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
                    System.out.println("SI se pudo" + Lista.size());
                    for (Transporte t : Lista) {
                        //   txtDatosI.setText(String.valueOf(t.getIdTransporte()));
                        System.out.println("" + t.getIdTransporte());
                    }
                }

                @Override
                public void onFailure(Call<List<Transporte>> call, Throwable t) {
                    System.out.println("No se pudo: " + t.getMessage());
                    System.out.println(t.getStackTrace());
                    System.out.println(t.getCause());
                    for (StackTraceElement e : t.getStackTrace()) {
                        System.out.println(e.toString());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //llenar spinner
    private void CargarDatosSpinners() {
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Call<List<Lugares>> Lug = servicioApi.getLugares();
        try
        {
            Lug.enqueue(new Callback<List<Lugares>>()
            {
                @Override
                public void onResponse(Call<List<Lugares>> call, Response<List<Lugares>> response)
                {
                    if(!response.isSuccessful()){
                        Toast.makeText(getActivity().getApplicationContext(), "ERROR "+response.code(), Toast.LENGTH_SHORT).show(); return;}

                    List<Lugares> Dp = response.body();

                    ObtenerLugares(Dp);

                    ArrayAdapter<String> Adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AllLug);
                    spinnerDestino.setAdapter(Adapter);
                }
                @Override
                public void onFailure(Call<List<Lugares>> call, Throwable t) { }
            });
        }
        catch (Exception e) {e.printStackTrace();}
    }

    //llenar spinner con lugares
    public ArrayList<String> ObtenerLugares(List<Lugares> List1)
    {
        AllLug = new ArrayList<>();
        for(Lugares dp1 : List1)
        { AllLug.add(dp1.getNombre()); }
        return AllLug;
    }
}