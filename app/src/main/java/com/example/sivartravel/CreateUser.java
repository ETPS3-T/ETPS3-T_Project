package com.example.sivartravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateUser extends AppCompatActivity {
    private EditText Usuario, Contraseña1,Contraseña2;
    private Button Aceptar, Regresar;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Usuario = findViewById(R.id.txtUsuario);
        Contraseña1 = findViewById(R.id.txtContra1);
        Contraseña2 = findViewById(R.id.txtContra2);
        Aceptar = findViewById(R.id.btnAceptar);
        Regresar = findViewById(R.id.btnRegresar);
        DB = new DBHelper(this);


        Aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Usuario.getText().toString();
                String pass = Contraseña1.getText().toString();
                String repass = Contraseña2.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(CreateUser.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(CreateUser.this, "Registro valido", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MenuUser.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(CreateUser.this, "Registro fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(CreateUser.this, "Usuario existente intente otro", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(CreateUser.this, "Contraseña no son iguales", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
    }
}