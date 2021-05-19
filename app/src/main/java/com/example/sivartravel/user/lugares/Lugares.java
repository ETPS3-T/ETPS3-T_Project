package com.example.sivartravel.user.lugares;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Departamentos;
import com.example.sivartravel.restservice.RetrofitClient;
import com.example.sivartravel.restservice.ServicioApi;
import com.example.sivartravel.user.adaptador.InterfaceClickListener;
import com.example.sivartravel.user.adaptador.RecyclerViewAdapterMini;
import com.example.sivartravel.user.entitys.LugaresEntity;
import com.example.sivartravel.util.JsonUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

//import static android.app.Activity.RESULT_OK;

public class Lugares extends Fragment implements OnMapReadyCallback, InterfaceClickListener {

    private GoogleMap mimapa;
    private MapView mMapView;
    private Spinner spDepartures;
    private View root;
    private RecyclerView Rec;
    private ArrayList<LugaresEntity> x= new ArrayList<>();
    private ArrayList AllDp= new ArrayList<>();
    private List<Lugares> respuesta = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.user_lugares, container, false);

        spDepartures = root.findViewById(R.id.spDepa);
        Rec = (RecyclerView)root.findViewById(R.id.Rec);
        ServicioApi service = RetrofitClient.getSOService();

        Call<List<com.example.sivartravel.entidades.Departamentos>> Dep = service.getDepartamentos();

        try
        {
            Dep.enqueue(new Callback<List<com.example.sivartravel.entidades.Departamentos>>()
            {
                @Override
                public void onResponse(Call<List<Departamentos>> call, Response<List<Departamentos>> response) {

                    if(!response.isSuccessful()){
                        Toast.makeText(getActivity().getApplicationContext(), "ERROR "+response.code(), Toast.LENGTH_SHORT).show(); return;}

                    List<Departamentos> Dp = response.body();

                    ObtenerDepartamentos(Dp);

                    ArrayAdapter<String> Adapter;
                    Adapter = new ArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, AllDp);
                    spDepartures.setAdapter(Adapter);
                }

                @Override
                public void onFailure(Call<List<Departamentos>> call, Throwable t) {

                }

            });
        }catch (Exception e){}
        spDepartures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> spn,View v,int position ,long id) {
                int h=position+1;

                if(x.size()>0)
                    x.clear();


                Call<List<com.example.sivartravel.entidades.Lugares>> repos = service.getSpecificLugar(h);

                try{

                    repos.enqueue(new Callback<List<com.example.sivartravel.entidades.Lugares>>(){
                        @Override
                        public void onResponse(Call<List<com.example.sivartravel.entidades.Lugares>> call, Response<List<com.example.sivartravel.entidades.Lugares>> response) {
                            if(response.isSuccessful()){

                                List<com.example.sivartravel.entidades.Lugares> lugaresList = response.body();


                                for(com.example.sivartravel.entidades.Lugares lugar : lugaresList)
                                {
                                    String[] part = lugar.getLocalizacion().split(",");
                                    x.add(new LugaresEntity( lugar.getImagen(), ""+lugar.getNombre(),""+lugar.getIdMunicipio().getIdDepartamentos().getDepartamentos(), ""+lugar.getDescripcion(),lugar.getLocalizacion(),"9:00 am - 17:00 pm", part[0],part[1]));

                                }

                                RecyclerViewAdapterMini adapter = new RecyclerViewAdapterMini(x, Lugares.this);
                                Rec.setLayoutManager(new GridLayoutManager(getContext(),2));
                                Rec.setAdapter(adapter);


                            }else{
                                System.out.println("ERROR EJECUCION");
                            }

                        }

                        @Override
                        public void onFailure(Call<List<com.example.sivartravel.entidades.Lugares>> call, Throwable t) {

                        }
                    });
                }catch (Exception e){ }finally {


                }


            }
            public void onNothingSelected(AdapterView<?> spn) {

            }
        });

            return root;
    }


    public void ada(ArrayList<LugaresEntity> je){

        RecyclerViewAdapterMini adapter = new RecyclerViewAdapterMini(je, this);
        Rec.setLayoutManager(new GridLayoutManager(getContext(),2));
        Rec.setAdapter(adapter);
    }

    private ArrayList getLugaress(List<com.example.sivartravel.entidades.Lugares> lugaresLista){

        x= new ArrayList<>();

        for(com.example.sivartravel.entidades.Lugares lugar : lugaresLista)
        {
            x.add(new LugaresEntity( lugar.getImagen(), ""+lugar.getNombre(),""+lugar.getIdMunicipio().getIdDepartamentos().getDepartamentos(), ""+lugar.getDescripcion(),""+lugar.getLocalizacion(),"9:00 am - 17:00 pm", "",""));
        }
        return x;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        mMapView = (MapView) root.findViewById(R.id.mapView);
        if(mMapView != null){

            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mimapa = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        googleMap.addMarker(new MarkerOptions().position(new LatLng(13.8205482, -89.135501)).title("El Salvador").snippet("NICE"));
        CameraPosition ESA = CameraPosition.builder().target(new LatLng(13.8205482, -89.135501)).zoom(10).bearing(0).tilt(35).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(ESA));
    }


    @Override
    public void OnItemClick(int position) {
        TransporteLugar tl = new TransporteLugar();

        /*
        mimapa.clear();
        mimapa.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(x.get(position).getX()), Double.parseDouble(x.get(position).getY()))).title(x.get(position).getDescripcion()).snippet("NICE"));
        CameraPosition CHA = CameraPosition.builder().target(new LatLng(Double.parseDouble(x.get(position).getX()), Double.parseDouble(x.get(position).getY()))).zoom(15).bearing(0).tilt(35).build();
        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CHA));
        */
        Bundle args=new Bundle();
        String jsonLugares= JsonUtil.getGsonParser().toJson(x.get(position));

        args.putString("Lugares", jsonLugares );
        tl.setArguments(args);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, tl);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();


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


}