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

import com.example.sivartravel.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Lugares extends Fragment implements OnMapReadyCallback {

    private GoogleMap mimapa;
    private MapView mMapView;
    private Spinner spDepartures;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.user_lugares, container, false);
        spDepartures = root.findViewById(R.id.spDepa);

        spDepartures.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> spn,View v,int position ,long id) {
                String dep = (String) spn.getItemAtPosition(position);
                switch(dep)
                {
                    case "Chalatenango":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(14.0227471, -88.9384462)).title("Chalatenango").snippet("NICE"));
                        CameraPosition CHA = CameraPosition.builder().target(new LatLng(14.0227471, -88.9384462)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(CHA));

                        break;
                    case "San Salvador":
                        mimapa.clear();
                        mimapa.addMarker(new MarkerOptions().position(new LatLng(13.6915591, -89.2502715)).title("San Salvador").snippet("NICE"));
                        CameraPosition SS = CameraPosition.builder().target(new LatLng(13.6915591, -89.2502715)).zoom(10).bearing(0).tilt(35).build();
                        mimapa.moveCamera(CameraUpdateFactory.newCameraPosition(SS));

                        break;
                    case "Cabañas":
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

        return root;
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

}