package com.example.sivartravel.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Transporte {

    @SerializedName("idDepartamentos")
    @Expose
    private Integer idTransporte;

    @SerializedName("idDepartamentos")
    @Expose
    private String nombre;

    @SerializedName("idDepartamentos")
    @Expose
    private Date fecha;

    @SerializedName("idDepartamentos")
    @Expose
    private Date horaSalida;

    @SerializedName("idDepartamentos")
    @Expose
    private Date horaRegreso;

    @SerializedName("idDepartamentos")
    @Expose
    private String costo;
    @SerializedName("idDepartamentos")
    @Expose
    private String telefono;
    @SerializedName("idDepartamentos")
    @Expose
    private Integer idUsuario;
    @SerializedName("idDepartamentos")
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

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraRegreso() {
        return horaRegreso;
    }

    public void setHoraRegreso(Date horaRegreso) {
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
