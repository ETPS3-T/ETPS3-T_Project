package com.example.sivartravel.util;

public class Utilidades {
    //Declaramos las constantes que seran campos de nuestras tablas USUARIOS
    public static final String Tabla_Usuario="usuarios";
    public static final String Campo_Id="Id";
    public static final String Campo_Nombre="Nombre";
    public static final String Campo_Correo="Correo";
    public static final String Campo_Clave="Clave";


    //Declaramos una variable String donde tendremos un comando SQL para Usuario
    public static final String Crear_Tabla_Usuarios="create table "+Tabla_Usuario+"("+Campo_Id+" integer primary key autoincrement, "+Campo_Nombre+" text, " +Campo_Correo+" text, "+Campo_Clave+" text );";



}
