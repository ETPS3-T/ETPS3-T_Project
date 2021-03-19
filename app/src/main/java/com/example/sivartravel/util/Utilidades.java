package com.example.sivartravel.util;

public class Utilidades {
    //Declaramos las constantes que seran campos de nuestras tablas USUARIOS
    public static final String Tabla_Usuario="Usuarios";
    public static final String Campo_Id="IdIdUsuario";
    public static final String Campo_Nombre="Nombre";
    public static final String Campo_Correo="Correo";
    public static final String Campo_Clave="Clave";


    //Declaramos una variable String donde tendremos un comando SQL para Usuario
    public static final String Crear_Tabla_Usuarios="create table "+Tabla_Usuario+"("+Campo_Id+" integer primary key autoincrement, "+Campo_Nombre+" text, " +Campo_Correo+" text, "+Campo_Clave+" text );";

    //Declaramos las constantes que seran campos de nuestras tabla Departamento
    public static final String Tabla_Departamento="Departamentos";
    public static final String Campo_IdDepartamento="IdDepartamento";
    public static final String Campo_NombreDepartamento="Departamento";

    //Declaramos una variable String donde tendremos un comando SQL para Departamento
    public static final String Crear_Tabla_Departamento="create table "+Tabla_Departamento+"("+Campo_IdDepartamento+" integer primary key autoincrement, "+Campo_NombreDepartamento+" text); ";


    //Declaramos las constantes que seran campos de nuestras tabla Municipios
    public static final String Tabla_Municipio="Municipios";
    public static final String Campo_IdMunicipio="IdMunicipio";
    public static final String Campo_NombreMunicipio="Municipio";
    public static final String Campo_fkDepartamento="IdDepartamento";

    //Declaramos una variable String donde tendremos un comando SQL para Municipio
    public static final String Crear_Tabla_Municipio="create table "+Tabla_Municipio+"("+Campo_IdMunicipio+" integer primary key autoincrement, "+Campo_NombreMunicipio+" text, " +
            " "+Campo_fkDepartamento+" text ,foreign key("+Campo_fkDepartamento+") references "+Tabla_Departamento+"("+Campo_IdDepartamento+")); ";

    //Declaramos las constantes que seran campos de nuestras tabla Lugares
    public static final String Tabla_Lugares="Lugares";
    public static final String Campo_IdLugar="IdLugar";
    public static final String Campo_fkMunicipio="IdMunicipio";
    public static final String Campo_NombreLugar="Nombre";
    public static final String Campo_Descripcion="Descripcion";
    public static final String Campo_Localizacion="Localizacion";
    public static final String Campo_fkUsuario="IdUsuario";

    //Declaramos una variable String donde tendremos un comando SQL para Lugares
    public static final String Crear_Tabla_Lugares="create table "+Tabla_Lugares+"("+Campo_IdLugar+" integer primary key autoincrement, "+Campo_fkMunicipio+" integer , "+Campo_NombreLugar+" text, "+Campo_Descripcion+" text, "+Campo_Localizacion+" text, " +
            " "+Campo_fkUsuario+" text, foreign key("+Campo_fkUsuario+") references "+Tabla_Usuario+"("+Campo_Id+") "+Campo_fkDepartamento+" text ,foreign key("+Campo_fkMunicipio+") references "+Tabla_Municipio+"("+Campo_IdMunicipio+")); ";


    //Declaramos las constantes que seran campos de nuestras tabla Lugares
    public static final String Tabla_Transporte="Transporte";
    public static final String Campo_IdTransporte="IdTransporte";
    public static final String Campo_fkIdLugar="IdLugar";
    public static final String Campo_NombreTransp="Nombre";
    public static final String Campo_Fecha="Fecha";
    public static final String Campo_HoraS="HoraSalida";
    public static final String Campo_HoraR="HoraRegreso";
    public static final String Campo_Costo="Costo";
    public static final String Campo_Telefono="Telefono";

    //Declaramos una variable String donde tendremos un comando SQL para Lugares
    public static final String Crear_Tabla_Transporte="create table "+Tabla_Transporte+"("+Campo_IdTransporte+" integer primary key autoincrement, "+Campo_fkIdLugar+" integer , "+Campo_NombreTransp+" text, "+Campo_Fecha+" text, "+Campo_HoraS+" text, " +
            " "+Campo_HoraR+" text, "+Campo_Costo+" text, "+Campo_Telefono+" text ,foreign key("+Campo_fkIdLugar+") references "+Tabla_Lugares+"("+Campo_IdLugar+")); ";









}
