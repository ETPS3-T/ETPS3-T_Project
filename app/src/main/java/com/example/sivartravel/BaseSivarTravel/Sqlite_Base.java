package com.example.sivartravel.BaseSivarTravel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sivartravel.util.Utilidades;

import static com.example.sivartravel.util.Utilidades.Crear_Tabla_Usuarios;
import static com.example.sivartravel.util.Utilidades.Tabla_Usuario;

public class Sqlite_Base extends SQLiteOpenHelper {

    Long idResultante;
    //Nombre de la base de datos en este constructor es donde colocamos el nombre de nuestra base...
    public Sqlite_Base(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DatosConexion.NOMBREBD, factory, DatosConexion.VERSION);
    }

    //En el onCreate es donde vamos a crear la estructura que va tener nuestra base de datos, Nuestras tablas y posibles relaciones entre ellas..
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*La primera vez que llamemos una base de datos y le ingresemos el nombre
          automaticamente se crea nuestra base de datos pero solos se crea una vez */
        sqLiteDatabase.execSQL(Crear_Tabla_Usuarios);

    }

    //Si queremos modificar alguna estructura de nuestra base de datos utilizaremos el onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //Si volvemos a instalar la aplicacion debemos eliminar la version antigua y luego generarla
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Tabla_Usuario);
        onCreate(sqLiteDatabase);
    }

    //Metodo para insertar registros en la tabla usuarios

    public void insetarReg(String nom, String correo, String clave){
        //Estos valores cuando se envien se deben colocar en ...
        ContentValues valores=new ContentValues();
        //Con put agregamos valores a el objeto valores
        valores.put(Utilidades.Campo_Nombre,nom);
        valores.put(Utilidades.Campo_Correo,correo);
        valores.put(Utilidades.Campo_Clave,clave);


        idResultante= this.getWritableDatabase().insert(Tabla_Usuario, Utilidades.Campo_Id,valores);
        //Toast.makeText(this,"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();

    }

    public Long IdR(){

        return idResultante;
    }

    //Metodo para abrir la base de datos
    public void abrir(){

        this.getWritableDatabase();

    }

    //Metodo para cerrar la base de datos
    public void cerrar(){
        this.close();
    }


}
