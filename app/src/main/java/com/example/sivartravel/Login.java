package com.example.sivartravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText Usuario, Contraseña;
    private Button Aceptar, Registro;
    private String Usu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Usuario = findViewById(R.id.txtUsuario);
        Contraseña = findViewById(R.id.txtContraseña);
        Aceptar = findViewById(R.id.btaceptar);
        Registro = findViewById(R.id.btnRegistar);

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),CreateUser.class);
                startActivity(i);
            }
        });
        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usu=Usuario.getText().toString();

                if (Usu.equals("1")){


                    Intent i = new Intent(getApplicationContext(),MenuUser.class);
                    startActivity(i);

                 }
                else {

                    Intent i2 = new Intent(getApplicationContext(),Administrador.class);
                    startActivity(i2);


                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}