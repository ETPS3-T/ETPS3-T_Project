package com.example.sivartravel.user.lugares;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sivartravel.R;
import com.example.sivartravel.entidades.Transporte;
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

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TransporteLugar extends Fragment implements OnMapReadyCallback, InterfaceClickListener {

    private  LugaresEntity lugarSelect;
    private TextView lb,Atras,data1,data2,data4;
    private GoogleMap mimapa;
    private MapView mMapView;
    private ArrayList<Transporte> z;

    public TransporteLugar() {

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.user_transporte_lugar, container, false);

        Bundle argumentos=this.getArguments();
        String transporte =argumentos.getString("Lugares");

        lugarSelect= JsonUtil.getGsonParser().fromJson(transporte, LugaresEntity.class);

        lb= root.findViewById(R.id.textLugar);
        data1 = root.findViewById(R.id.data1);
        data2 = root.findViewById(R.id.data2);
        data4 = root.findViewById(R.id.data4);

        Atras=root.findViewById(R.id.btnAtrasX);

        lb.setText(lugarSelect.getLugar());

        data1.setText(lugarSelect.getDescripcion());
        data2.setText(lugarSelect.getDepartamento());
        data4.setText(lugarSelect.getHorario());

        z = new ArrayList<>();

        Atras.setOnClickListener(new View.OnClickListener() {

            Lugares L = new Lugares();
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, L);
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });

        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        mMapView = (MapView) view.findViewById(R.id.mapView2);
        if(mMapView != null){

            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    public void OnBackPressed() {

        Lugares tl2 = new Lugares();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, tl2);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());

        mimapa = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        String[] part1 = lugarSelect.getX().split(" ");
        String[] part2 = lugarSelect.getY().split("=");
        googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(part1[1]), Double.parseDouble(part2[1]))).title("El Salvador").snippet("NICE"));
        CameraPosition ESA = CameraPosition.builder().target(new LatLng(Double.parseDouble(part1[1]), Double.parseDouble(part2[1]))).zoom(15).bearing(1).tilt(35).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(ESA));
    }
}
