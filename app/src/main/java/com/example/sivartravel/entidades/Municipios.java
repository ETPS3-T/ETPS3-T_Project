package com.example.sivartravel.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Municipios {

    @SerializedName("idMunicipio")
    @Expose
    private Integer idMunicipio;
    @SerializedName("municipio")
    @Expose
    private String municipio;
    @SerializedName("idDepartamentos")
    @Expose
    private Departamentos idDepartamentos;

    public Municipios() {
    }

    public Municipios(String municipio) {
        this.municipio = municipio;
    }

    public Municipios(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Departamentos getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(Departamentos idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }
}
