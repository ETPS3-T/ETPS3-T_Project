package com.example.sivartravel.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lugares {




    @SerializedName("idLugares")
    @Expose
    private Integer idLugares;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("imagen")
    @Expose
    private String imagen;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("localizacion")
    @Expose
    private String localizacion;

    @SerializedName("idMunicipio")
    @Expose
    private Municipios idMunicipio;

    @SerializedName("idUsuario")
    @Expose
    private Usuarios idUsuario;

    public Lugares() {
    }

    public Lugares(Integer idLugares) {
        this.idLugares = idLugares;
    }

    public Integer getIdLugares() {
        return idLugares;
    }

    public void setIdLugares(Integer idLugares) {
        this.idLugares = idLugares;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }
}
