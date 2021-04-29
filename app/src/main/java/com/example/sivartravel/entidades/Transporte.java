package com.example.sivartravel.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.Date;

public class Transporte {
    @SerializedName("idTransporte")
    @Expose
    private Integer idTransporte;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("fecha")
    @Expose
    private Date fecha;

    @SerializedName("horaSalida")
    @Expose
    private String horaSalida;

    @SerializedName("horaRegreso")
    @Expose
    private String horaRegreso;

    @SerializedName("costo")
    @Expose
    private String costo;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;
    @SerializedName("idLugares")
    @Expose
    private Lugares idLugares;

    public Transporte() {
    }


    public Transporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }

    public Integer getIdTransporte() {
        return idTransporte;
    }

    public void setIdTransporte(Integer idTransporte) {
        this.idTransporte = idTransporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraRegreso() {
        return horaRegreso;
    }

    public void setHoraRegreso(String horaRegreso) {
        this.horaRegreso = horaRegreso;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Lugares getIdLugares() {
        return idLugares;
    }

    public void setIdLugares(Lugares idLugares) {
        this.idLugares = idLugares;
    }



}
