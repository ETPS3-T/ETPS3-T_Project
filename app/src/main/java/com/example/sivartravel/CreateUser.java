package com.example.sivartravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateUser extends AppCompatActivity {
    private EditText Usuario, Correo, Contraseña;
    private Button Aceptar, Regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Usuario = findViewById(R.id.txtUsuario);
        Correo = findViewById(R.id.txtCorreo);
        Contraseña = findViewById(R.id.txtContraseña);
        Aceptar = findViewById(R.id.btnAceptar);
        Regresar = findViewById(R.id.btnRegresar);

        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }
}