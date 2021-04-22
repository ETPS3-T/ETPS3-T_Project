package com.example.sivartravel.entidades;

public class Municipios {

    private Integer idMunicipio;

    private String municipio;

    private Departamentos idDepartamentos;

    public Municipios() {
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
