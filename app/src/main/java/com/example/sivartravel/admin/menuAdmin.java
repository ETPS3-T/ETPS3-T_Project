package com.example.sivartravel.admin;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sivartravel.BaseSivarTravel.DatosConexion;
import com.example.sivartravel.BaseSivarTravel.Sqlite_Base;
import com.example.sivartravel.R;

import static com.example.sivartravel.util.Utilidades.Campo_Clave;
import static com.example.sivartravel.util.Utilidades.Campo_Correo;
import static com.example.sivartravel.util.Utilidades.Campo_Nombre;
import static com.example.sivartravel.util.Utilidades.Tabla_Usuario;

public class menuAdmin extends AppCompatActivity {

    Button btnCargar;

    //Creamos una instacia de nuestra base de datos
    Sqlite_Base helper=new Sqlite_Base(this, DatosConexion.NOMBREBD,null, DatosConexion.VERSION);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu_admin);
        btnCargar=findViewById(R.id.btnCargarBD);
        Cursor mm = validacionUsuarios();

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                if(mm.getCount()>0){
                    Toast.makeText(getApplicationContext(),"TENEMOS DATOS AREAS",Toast.LENGTH_LONG).show();
                }
                else{
                    insertarUsuario();
                }

            }
                catch (SQLException e){
                e.printStackTrace();
            }
            }


        });



    }

    public Cursor validacionUsuarios(){

        helper.abrir();
        Cursor u = null;

        u=helper.getWritableDatabase().rawQuery("SELECT * FROM "+Tabla_Usuario,new String[]{});

        return u;

    }

    private void insertarUsuario() {
        helper.abrir();
        String comandoL="INSERT INTO "+Tabla_Usuario+"("+Campo_Nombre+","+Campo_Correo+" ,"+Campo_Clave+" ) " +
                "values('Douglas Calderon','douglas@gmail.com','123')";

        helper.getWritableDatabase().execSQL(comandoL);
    }

}