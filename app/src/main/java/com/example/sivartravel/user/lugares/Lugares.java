package com.example.sivartravel.user.lugares;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.R;
import com.example.sivartravel.user.adaptador.InterfaceClickListener;
import com.example.sivartravel.user.adaptador.RecyclerViewAdapter;
import com.example.sivartravel.user.adaptador.RecyclerViewAdapterMini;
import com.example.sivartravel.user.entitys.LugaresEntity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Lugares extends Fragment implements OnMapReadyCallback, InterfaceClickListener {

    private GoogleMap mimapa;
    private MapView mMapView;
    private Spinner spDepartures;
    private View root;

    private RecyclerView Rec;
    private RecyclerView.LayoutManager adapter;
    private ArrayList<LugaresEntity> x = new ArrayList<>();;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.user_lugares, container, false);
        spDepartures = root.findViewById(R.id.spDepa);
        Rec = (RecyclerView)root.findViewById(R.id.Rec);


        spDepartures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> spn,View v,int position ,long id) {

                String dep = (String) spn.getItemAtPosition(position);
                switch(dep)
                {
                    case "Chalatenango":

                        ada(Chalate());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(14.0227471, -88.9384462)).title("Chalatenango").snippet("NICE"));
                        CameraPosition CHA = CameraPosition.builder().target(new LatLng(14.0227471, -88.9384462)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CHA));

                        break;
                    case "San Salvador":

                        ada(SanSalvador());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.6915591, -89.2502715)).title("San Salvador").snippet("NICE"));
                        CameraPosition SS = CameraPosition.builder().target(new LatLng(13.6915591, -89.2502715)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SS));

                        break;
                    case "Cabañas":
                        ada(Cabanas());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.8796805, -88.8809559)).title("Cabañas").snippet("NICE"));
                        CameraPosition CA = CameraPosition.builder().target(new LatLng(13.8796805, -88.8809559)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CA));

                        break;
                    case "La Union":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.3376564, -87.8662064)).title("La Union").snippet("NICE"));
                        CameraPosition LAU = CameraPosition.builder().target(new LatLng(13.3376564, -87.8662064)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(LAU));

                        break;
                    case "Usulutan":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.3370298, -88.4432588)).title("Usulutan").snippet("NICE"));
                        CameraPosition USUL = CameraPosition.builder().target(new LatLng(13.3370298, -88.4432588)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(USUL));

                        break;
                    case "San Miguel":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.4785983, -88.2041089)).title("San Miguel").snippet("NICE"));
                        CameraPosition SMIG = CameraPosition.builder().target(new LatLng(13.4785983, -88.2041089)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SMIG));

                        break;

                    case "La Paz":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.4665636, -89.1122749)).title("La Paz").snippet("NICE"));
                        CameraPosition LAPA = CameraPosition.builder().target(new LatLng(13.4665636, -89.1122749)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(LAPA));

                        break;

                    case "La Libertad":
                        ada(LaLibertad());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.8523851, -89.4360627)).title("La Libertad").snippet("NICE"));
                        CameraPosition LALI = CameraPosition.builder().target(new LatLng(13.8523851, -89.4360627)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(LALI));

                        break;

                    case "Santa Tecla":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.6723758, -89.3077328)).title("Santa Tecla").snippet("NICE"));
                        CameraPosition TEC = CameraPosition.builder().target(new LatLng(13.6723758, -89.3077328)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(TEC));

                        break;

                    case "San Vicente":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.6406283, -88.8014311)).title("San Vicente").snippet("NICE"));
                        CameraPosition SANVI = CameraPosition.builder().target(new LatLng(13.6406283, -88.8014311)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SANVI));

                        break;
                    case "Morazan":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.7471515, -88.241804)).title("Morazan").snippet("NICE"));
                        CameraPosition MOR = CameraPosition.builder().target(new LatLng(13.7471515, -88.241804)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(MOR));

                        break;

                    case "Cuscatlan":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.854424, -89.2968673)).title("Cuscatlan").snippet("NICE"));
                        CameraPosition CUSC = CameraPosition.builder().target(new LatLng(13.854424, -89.2968673)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CUSC));

                        break;

                    case "Sonsonate":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.7107221, -89.7659636)).title("Sonsonate").snippet("NICE"));
                        CameraPosition SONS = CameraPosition.builder().target(new LatLng(13.7107221, -89.7659636)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SONS));

                        break;
                    case "Santa Ana":
                        ada(SantaAna());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.9838741, -89.5978411)).title("Santa Ana").snippet("NICE"));
                        CameraPosition SNT = CameraPosition.builder().target(new LatLng(13.9838741, -89.5978411)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SNT));

                        break;
                }
            }
            public void onNothingSelected(AdapterView<?> spn) {

            }
        });

        RecyclerViewAdapterMini adapter = new RecyclerViewAdapterMini(x, this);
        Rec.setLayoutManager(new GridLayoutManager(getContext(),2));
        Rec.setAdapter(adapter);

        return root;
    }
    private ArrayList<LugaresEntity> Chalate(){

        x.clear();
        x.add(new LugaresEntity(R.drawable.main, "Rio Sumpul","Chalatenango","CENTRO TURISTICO","","9:00 am - 17:00 pm", "14.0672518","-88.8947239"));
        x.add(new LugaresEntity(R.drawable.main, "Aguas Frias","Chalatenango", "CENTRO TURISTICO","","6:00 am - 20:00 pm", "14.0499338","-88.937296"));
        x.add(new LugaresEntity(R.drawable.main, "Cerro El Pital","Chalatenango", "CENTRO TURISTICO","","24/7", "14.3805098","-89.1466227"));
        x.add(new LugaresEntity(R.drawable.main, "Entre Pinos","Chalatenango", "HOTEL","","24/7", "14.3312982","-89.1804379"));

        return x;
    }
    private ArrayList<LugaresEntity> SanSalvador(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.main, "El Boquerón","San Salvador","PARQUE NACIONAL","","9:00 am - 17:00 pm", "13.7044008","-89.2778442"));
        x.add(new LugaresEntity(R.drawable.main, "Parque Bicentenario","San Salvador", "PARQUE NACIONAL","","6:00 am - 20:00 pm", "13.6939506","-89.2510802"));
        x.add(new LugaresEntity(R.drawable.main, " Lago Ilopango","San Salvador", "CENTRO TURISTICO","","24/7", "13.6629697","-89.0107873"));
        x.add(new LugaresEntity(R.drawable.main, "Joya de Cerén","San Salvador", "SITIO ARQUEOLOGICO","","24/7", "13.827925","-89.3584326"));

        return x;
    }
    private ArrayList<LugaresEntity> SantaAna(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.main, "Volcan Santa Ana","Santa Ana","PARQUE NACIONAL","","9:00 am - 17:00 pm", "13.8472748","-89.597247"));
        x.add(new LugaresEntity(R.drawable.main, "Parque Cerro Verde","Santa Ana", "PARQUE NACIONAL","","6:00 am - 20:00 pm", "13.8472748","-89.597247"));
        x.add(new LugaresEntity(R.drawable.main, " Lago Coatepeque","Santa Ana", "CENTRO TURISTICO","","24/7", "13.867523","-89.5578335"));
        x.add(new LugaresEntity(R.drawable.main, "Volcan Ilamatepec","Santa Ana", "PARQUE NACIONAL","","24/7", "13.8502055","-89.653759"));

        return x;
    }
    private ArrayList<LugaresEntity> LaLibertad(){

        x.clear();
        x.add(new LugaresEntity(R.drawable.main, "La Curva de Don Gere","La Libertad","RESTAURANT/BEACH","","9:00 am - 17:00 pm", "13.5016112","-89.4105319"));
        x.add(new LugaresEntity(R.drawable.main, "El Malecón","La Libertad", "PLAZA MUNICIPAL","","6:00 am - 20:00 pm", "13.4840151","-89.3151255"));
        x.add(new LugaresEntity(R.drawable.main, "Parque Walter T. Deininger","La Libertad", "PARQUE NACIONAL","","24/7", "13.5036888","-89.2787378"));
        x.add(new LugaresEntity(R.drawable.main, "San Andrés","La Libertad", "SITIO ARQUEOLOGICO","","24/7", "13.7834365","-89.3830932"));

        return x;
    }
    private ArrayList<LugaresEntity> Cabanas(){

        x.clear();
        x.add(new LugaresEntity(R.drawable.main, "Bosque De Cinquera","Cabañas","PARQUE ECOLOGICO","","9:00 am - 17:00 pm", "13.8796803","-88.881296"));
        x.add(new LugaresEntity(R.drawable.main, "El Salto San Antonio","Cabañas", "NATURAL CASCADE","","6:00 am - 20:00 pm", "13.9881533","-88.6753741"));
        x.add(new LugaresEntity(R.drawable.main, "Heliconias Del Rio","Cabañas", "HOTEL","","24/7", "13.8718591","-88.9285789"));
        x.add(new LugaresEntity(R.drawable.main, "La Casa de La Hacienda","Cabañas", "PARQUE ACUATICO","","24/7", "13.8214476","-88.8582877"));

        return x;
    }

    public void ada(ArrayList<LugaresEntity> je){

        RecyclerViewAdapterMini adapter = new RecyclerViewAdapterMini(je, this);
        Rec.setLayoutManager(new GridLayoutManager(getContext(),2));
        Rec.setAdapter(adapter);
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

        mimapa.clear();
        mimapa.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(x.get(position).getX()), Double.parseDouble(x.get(position).getY()))).title(x.get(position).getDescripcion()).snippet("NICE"));
        CameraPosition CHA = CameraPosition.builder().target(new LatLng(Double.parseDouble(x.get(position).getX()), Double.parseDouble(x.get(position).getY()))).zoom(15).bearing(0).tilt(35).build();
        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CHA));

    }
}