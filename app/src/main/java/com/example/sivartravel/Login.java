package com.example.sivartravel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity{
    private EditText Usuario, Contraseña;
    private Button Aceptar, Registro;
    private String Usu, Psw;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Usuario = findViewById(R.id.txtUsuario);
        Contraseña = findViewById(R.id.txtContraseña);
        Aceptar = findViewById(R.id.btaceptar);
        Registro = findViewById(R.id.btnRegistar);
        DB = new DBHelper(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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
                String user = Usuario.getText().toString();
                String pass = Contraseña.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass){
                        Toast.makeText(Login.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), MenuUser.class);
                        startActivity(intent);
                    }else{
                        //Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        Usu=Usuario.getText().toString();
                        Psw= Contraseña.getText().toString();
                        if (Usu.equals("admin")&&(Psw.equals("123"))){
                            Intent i2 = new Intent(getApplicationContext(),Administrador.class);
                            startActivity(i2);
                        }else{
                            Usuario.setError("Usuario o clave incorrectos.");


                        }
                    }
                }



            }
            /*
            * Usu=Usuario.getText().toString();
                if (Usu.equals("1")){
                    Intent i = new Intent(getApplicationContext(),MenuUser.class);
                    startActivity(i);
                 }
                else {
                    Intent i2 = new Intent(getApplicationContext(),Administrador.class);
                    startActivity(i2);
                }
            * */
        });
    }


}