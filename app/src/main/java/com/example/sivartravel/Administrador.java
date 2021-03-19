package com.example.sivartravel;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;


public class Administrador extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    String correo="holii";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
        Toolbar toolbar = findViewById(R.id.toolbara);
        setSupportActionBar(toolbar);

        //correo=getIntent().getStringExtra("correo");

        DrawerLayout drawer = findViewById(R.id.drawer_layoutAdmin);
        NavigationView navigationView = findViewById(R.id.nav_viewAdmin);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.R.id.nav_consultarmedicamentos_a,R.id.nav_medicamentos_a
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.admin_Inicio,R.id.pruebaBd, R.id.administrarLugares, R.id.administrarUbicacion)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_hosta);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        UpdateNavHeader(correo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_hosta);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
    public void UpdateNavHeader(String corr){

        NavigationView navigationView = findViewById(R.id.nav_viewAdmin);
        View Encabezado=navigationView.getHeaderView(0);
        TextView txtCorreo=Encabezado.findViewById(R.id.txtCorreo);
        txtCorreo.setText(corr);

    }


}