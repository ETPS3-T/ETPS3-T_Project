package com.example.sivartravel.user.lugares;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.R;
import com.example.sivartravel.user.adaptador.InterfaceClickListener;
import com.example.sivartravel.user.adaptador.RecyclerViewAdapter;
import com.example.sivartravel.user.adaptador.RecyclerViewAdapterMini;
import com.example.sivartravel.user.entitys.LugaresEntity;
import com.example.sivartravel.util.JsonUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
/*
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static android.app.Activity.RESULT_OK;

public class Lugares extends Fragment implements OnMapReadyCallback, InterfaceClickListener{

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
        //Inicializamos Places
        //Places.initialize(getContext(),"AIzaSyDgIBVb5R2nZCAyoz0H8psXicG41MG6d2A");

        /*Set EditText non Focusable
        UbicacionB.setFocusable(false);
        UbicacionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Place.Field> llena = Arrays.asList(Place.Field.ADDRESS,Place.Field.LAT_LNG,Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,llena).build(getContext());
                startActivityForResult(intent,100);
            }
        });*/
        spDepartures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> spn,View v,int position ,long id) {

                String dep = (String) spn.getItemAtPosition(position);
                switch(dep)
                {
                    case "Chalatenango":

                        ada(Chalatenango());
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

                        ada(LaUnion());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.3376564, -87.8662064)).title("La Union").snippet("NICE"));
                        CameraPosition LAU = CameraPosition.builder().target(new LatLng(13.3376564, -87.8662064)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(LAU));

                        break;
                    case "Usulutan":
                        ada(Usulutan());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.3370298, -88.4432588)).title("Usulutan").snippet("NICE"));
                        CameraPosition USUL = CameraPosition.builder().target(new LatLng(13.3370298, -88.4432588)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(USUL));

                        break;
                    case "San Miguel":
                        ada(SanMiguel());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.4785983, -88.2041089)).title("San Miguel").snippet("NICE"));
                        CameraPosition SMIG = CameraPosition.builder().target(new LatLng(13.4785983, -88.2041089)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SMIG));

                        break;

                    case "La Paz":
                        ada(LaPaz());
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

                    case "Ahuachapan":
                        ada(Ahuachapan());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.6723758, -89.3077328)).title("Santa Tecla").snippet("NICE"));
                        CameraPosition TEC = CameraPosition.builder().target(new LatLng(13.6723758, -89.3077328)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(TEC));

                        break;

                    case "San Vicente":
                        ada(SanVicente());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.6406283, -88.8014311)).title("San Vicente").snippet("NICE"));
                        CameraPosition SANVI = CameraPosition.builder().target(new LatLng(13.6406283, -88.8014311)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SANVI));

                        break;
                    case "Morazan":

                        ada(Morazan());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.7471515, -88.241804)).title("Morazan").snippet("NICE"));
                        CameraPosition MOR = CameraPosition.builder().target(new LatLng(13.7471515, -88.241804)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(MOR));

                        break;

                    case "Cuscatlan":

                        ada(Cuscatlan());
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.854424, -89.2968673)).title("Cuscatlan").snippet("NICE"));
                        CameraPosition CUSC = CameraPosition.builder().target(new LatLng(13.854424, -89.2968673)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CUSC));

                        break;

                    case "Sonsonate":

                        ada(Sonsonate());
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
    private ArrayList<LugaresEntity> SanVicente(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.acuaticoamapulapa2, "Parque Acuatico de Amapulapa","San Vicente","CENTRO TURISTICO","","7:00 am - 16:00 pm", "13.6285503"," -88.7758982"));
        x.add(new LugaresEntity(R.drawable.jiboa1, "Rio Jiboa","San Vicente", "CENTRO TURISTICO","","24/7", "13.690546"," -88.8644153"));
        x.add(new LugaresEntity(R.drawable.apastepeque3, "Sitio arqueologico de Apastepeque","San Vicente", "CENTRO TURISTICO","","8:00 am – 4:00 pm", "13.6636627"," -88.7878711")); return x;
    }

    private ArrayList<LugaresEntity> Morazan(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.perquin2, "Perquin","Morazan","CENTRO TURISTICO","","7:00 am – 16:00 pm", "13.9581839"," -88.1624176"));
        x.add(new LugaresEntity(R.drawable.atecozol4, "Turicentro Atecozol","Morazan", "CENTRO TURISTICO","","8:00 am – 16:00 pm", "13.7458664"," -89.6684428"));
        x.add(new LugaresEntity(R.drawable.rutapaz4, "Ruta de la Paz","Morazan", "RUTA TURISTICA","","24/7", "13.7520189"," -88.1192502"));
        return x;
    }

    private ArrayList<LugaresEntity> Usulutan(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.jiquilisco5, "La Bahia de Jiquilisco","San Vicente","CENTRO TURISTICO","","24/7", "13.263251"," -88.6375888"));
        x.add(new LugaresEntity(R.drawable.espino3, "Playa El Espino","San Vicente", "CENTRO TURISTICO","","7:00 am – 18:00 pm", "13.1704799"," -88.2947132"));
        x.add(new LugaresEntity(R.drawable.casavieja2, "Casa Vieja","San Vicente", "RESTAURANTE","","6:30 am – 21:00 pm", "13.6453169"," -88.786062")); return x;
    }

    private ArrayList<LugaresEntity> Chalatenango(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.elpital4, "Cerro El Pital","Chalatenango", "CENTRO TURISTICO","","24/7", "14.3886102"," -89.1347624"));
        x.add(new LugaresEntity(R.drawable.aguafria2, "Turicentro Aguas Fria","Chalatenango", "CENTRO TURISTICO","","8:00 am - 16:00 pm ", "14.0467278"," -88.9381128"));
        x.add(new LugaresEntity(R.drawable.montanona2, "la montanona","Chalatenango", "CENTRO TURISTICO","","24/7", "14.1292765"," -88.9199047"));
        x.add(new LugaresEntity(R.drawable.centrodrmario3, "centro recreativo dr. mario zamora rivas","Chalatenango", " CENTRO TURISTICO ","","8:00 am - 16:00 pm ", "14.2919778"," -89.1620618")); return x;}

    private ArrayList<LugaresEntity> SanSalvador(){
        x.clear(); x.add(new LugaresEntity(R.drawable.catedral2, "Catedral metropolitana","San Salvador","CENTRO TURISTICO","","6:00 am - 18:00 pm", "13.6985831"," -89.1933203"));
        x.add(new LugaresEntity(R.drawable.muna3, "museo nacional de antropologia david j. guzman","San Salvador", "MUSEO","","9:00 am - 17:00 pm", "13.6871469"," -89.2409359"));
        x.add(new LugaresEntity(R.drawable.tinmarin4, "Tin Marin","San Salvador", "MUSEO","","9:00 am - 17:00 pm", "13.6985873"," -89.2110741"));
        x.add(new LugaresEntity(R.drawable.palacio3, "Palacio Nacional","San Salvador", "CENTRO TURISTICO","","8:00 am - 16:00 pm ", "13.6977482"," -89.1943602"));
        x.add(new LugaresEntity(R.drawable.teatro3, "Teatro Nacional","San Salvador", "CENTRO TURISTICO","","13:00 am - 16:00 pm ", "13.6985668"," -89.1925948")); return x;
    }
    private ArrayList<LugaresEntity> SanMiguel(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.catedralsanmiguel2, "Catedral de San Miguel","San Miguel","CENTRO RELIGIOSO","","8:00 am – 17:00 pm", "13.4831361"," -88.1754647"));
        x.add(new LugaresEntity(R.drawable.olomega4, "Laguna de Olomega","San Miguel", "CENTRO TURISTICO","","24/7", "13.3103247"," -88.0715938"));
        x.add(new LugaresEntity(R.drawable.quelepa3, "Ruinas de Quelepa","San Miguel", "LUGAR ARQUEOLOGICO","","24/7", "13.5208638"," -88.2383896"));
        x.add(new LugaresEntity(R.drawable.museoregional2, "Museo Regional del Oriente","San Miguel", "LUGAR ARQUEOLOGICO","","9:00 am – 16:30 pm", "13.473957"," -88.1712819")); return x;
    }

    private ArrayList<LugaresEntity> LaUnion(){
        x.clear();
        x.add(new LugaresEntity(R.drawable.tunas1, "Playa Las Tunas","La Union","CENTRO TURISTICO","","24/7", "13.1605424"," -87.9710184"));
        x.add(new LugaresEntity(R.drawable.fonseca3, "Golfo de Fonseca","La Union", "CENTRO TURISTICO","","24/7", "13.3376146"," -87.8662063"));
        x.add(new LugaresEntity(R.drawable.main, "Volcan de Conchagua","La Union", "CENTRO TURISTICO","","24/7", "13.2769043"," -87.8436864"));
        x.add(new LugaresEntity(R.drawable.tamarindo1, "Playa El Tamarindo","La Union", "CENTRO TURISTICO","","24/7", "13.1972715"," -87.9146203")); return x;
    }


    private ArrayList<LugaresEntity> SantaAna(){
        x.clear(); x.add(new LugaresEntity(R.drawable.coatepeque3, "lago de coatepeque","Santa Ana","CENTRO TURISTICO","","24/7", "14.1873093","-88.8897874"));
        x.add(new LugaresEntity(R.drawable.tazumal2, "el tazumal"," Santa Ana ", "CENTRO TURISTICO","","9:00 am - 16:00 pm ", "14.0499338","-88.937296"));
        x.add(new LugaresEntity(R.drawable.ilamatepec1, "volcan ilamatepec"," Santa Ana ", "CENTRO TURISTICO","","24/7", "14.3805098","-89.1466227"));
        x.add(new LugaresEntity(R.drawable.museoregional2, "museo regional de occidente"," Santa Ana ", "MUSEO","","9:00 am - 17:00 pm ", "14.3312982","-89.1804379"));
        return x;
    }

    private ArrayList<LugaresEntity> LaLibertad(){
        x.clear(); x.add(new LugaresEntity(R.drawable.joyaceren2, "sitio arqueologico joya de ceren","La Libertad","CENTRO TURISTICO","","24/7", "13.8279258"," -89.3584273"));
        x.add(new LugaresEntity(R.drawable.sanandres3, "sitio arqueologico san andres"," La Libertad ", "CENTRO TURISTICO","","9:00 am - 16:00 pm", "13.7972019"," -89.3926815"));
        x.add(new LugaresEntity(R.drawable.eltunco3, "playa el tunco"," La Libertad ", "CENTRO TURISTICO","","24/7", "13.4922427"," -89.3901437"));
        x.add(new LugaresEntity(R.drawable.boqueron3, "parque nacional el boqueron"," La Libertad ", "CENTRO TURISTICO","","8:00 am - 17:00 pm", "13.7374964"," -89.2819297"));
        x.add(new LugaresEntity(R.drawable.deininger1, "parque nacional walter thilo deininger"," La Libertad ", " CENTRO TURISTICO ","","8:00 am - 16:00 pm ", "13.499261"," -89.2702906")); return x;}

    private ArrayList<LugaresEntity> Cabanas(){
        x.clear(); x.add(new LugaresEntity(R.drawable.lempa2, "Rio Lempa","Cabañas","CENTRO TURISTICO","","24/7", "13.8796805"," -88.8809559"));
        x.add(new LugaresEntity(R.drawable.central5nov2, "Planta Hidroelectrica 5 de Noviembre","Cabañas", "CENTRO TURISTICO","","8:00 am – 15:30 pm", "13.9900255"," -88.7602318"));
        x.add(new LugaresEntity(R.drawable.tunel3, "El tunel de los mineros","Cabañas", "CENTRO TURISTICO","","24/7", "13.8801704"," -88.6299372")); return x;
    }

    private ArrayList<LugaresEntity> Sonsonate(){
        x.clear(); x.add(new LugaresEntity(R.drawable.volcanizalco3, "volcan de izalco","Sonsonate","CENTRO TURISTICO","","11:00 am - 12:30 pm", "13.813678"," -89.641393"));
        x.add(new LugaresEntity(R.drawable.cobanos2, "playa los cobanos"," Sonsonate ", "CENTRO TURISTICO","","24/7", "13.5250205"," -89.8054215"));
        x.add(new LugaresEntity(R.drawable.atecozol2, "turicentro atecozol"," Sonsonate ", "CENTRO TURISTICO","","8:00 am - 16:00 pm ", "13.7458716"," -89.6684428"));
        x.add(new LugaresEntity(R.drawable.loschorros2, "chorros de la calera"," Sonsonate ", " CENTRO TURISTICO ","","7:00 am - 17:00 pm ", "13.8375113"," -89.7359698")); return x;}

    private ArrayList<LugaresEntity> Ahuachapan() {
        x.clear();
        x.add(new LugaresEntity(R.drawable.elimposible1, "Parque Nacional El Imposible", "Ahuachapan", "CENTRO TURISTICO", "", "24/7", "13.8332149", " -89.9368555"));
        x.add(new LugaresEntity(R.drawable.santateresa3, "Termales de Santa Teresa", " Ahuachapan ", "CENTRO TURISTICO", "", "8:00 am - 22:00 pm", "13.9022893", " -89.8211289"));
        x.add(new LugaresEntity(R.drawable.malacatiupan2, "El salto de Malacatiupan", " Ahuachapan ", "CENTRO TURISTICO", "", "24/7", "13.9966006", " -89.8015921"));
        x.add(new LugaresEntity(R.drawable.lagunaverde1, "Cerro Laguna Verde", " Ahuachapan ", "CENTRO TURISTICO", "", "24/7", "13.9000204", " -89.7920881"));
        return x;
    }
    private ArrayList<LugaresEntity> Cuscatlan(){
        x.clear(); x.add(new LugaresEntity(R.drawable.cascadas2, "Cascadas los tercios","Cuscatlan","CENTRO TURISTICO","","24/7", "13.9372965"," -89.0152753"));
        x.add(new LugaresEntity(R.drawable.centroarte2, "Centro arte para la paz"," Cuscatlan", "CENTRO TURISTICO","","7:00 am - 15:00 pm", "13.9384198"," -89.0309367"));
        x.add(new LugaresEntity(R.drawable.casaalejandro3, "casa de alejandro cotto"," Cuscatlan", "CENTRO TURISTICO","","8:00 am - 15:00 pm ", "13.9420601"," -89.0228998"));
        x.add(new LugaresEntity(R.drawable.museomoneda3, "museo de la moneda"," Cuscatlan", "MUSEO","","8:00 am - 17:00 pm ", "13.9384806"," -89.0292936"));
        return x;
    }

    private ArrayList<LugaresEntity> LaPaz() {
        x.clear();

        x.add(new LugaresEntity(R.drawable.costasol3, "Costa del Sol", "La Paz", "CENTRO TURISTICO", "", "7:00 am - 20:00 pm", "13.3436107", " -89.0151437"));
        x.add(new LugaresEntity(R.drawable.santaclara2, "Parque Regional Santa Clara", "La Paz", "CENTRO TURISTICO", "", "8:00 am - 20:00 pm", "13.4499996", " -89.1087548"));
        x.add(new LugaresEntity(R.drawable.manantialesjiboa2, "Manantiales de Jiboa", "La Paz", "CENTRO TURISTICO", "", "8:00 am - 17:00 pm", "13.4965677", " -89.0090676"));
        x.add(new LugaresEntity(R.drawable.museocasarte2, "Museo municipal y casa de arte San Pedro Masahuat", "La Paz", "MUSEO", "", "8:00 am – 16:30 pm", "13.5433236", " -89.0404215"));

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

        TransporteLugar tl = new TransporteLugar();
        mimapa.clear();
        mimapa.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(x.get(position).getX()), Double.parseDouble(x.get(position).getY()))).title(x.get(position).getDescripcion()).snippet("NICE"));
        CameraPosition CHA = CameraPosition.builder().target(new LatLng(Double.parseDouble(x.get(position).getX()), Double.parseDouble(x.get(position).getY()))).zoom(15).bearing(0).tilt(35).build();
        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CHA));

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

/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            Place place = Autocomplete.getPlaceFromIntent(data);
            UbicacionB.setText(place.getAddress());
            //Set Locality
            UbicacionB.setText(String.format("Nombre Ubicacion: %s",place.getName()));
            UbicacionB.setText(String.valueOf(place.getLatLng()));

        }else if(resultCode== AutocompleteActivity.RESULT_ERROR){
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getContext(),status.getStatusMessage(),Toast.LENGTH_LONG).show();
        }
    }
    */

}