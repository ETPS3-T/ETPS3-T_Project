package com.example.sivartravel.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Departamentos {

    @SerializedName("idDepartamentos")
    @Expose
    private Integer idDepartamentos;
    @SerializedName("departamentos")
    @Expose
    private String departamentos;

    public Departamentos() {
    }

    public Departamentos(Integer idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public Integer getIdDepartamentos() {
        return idDepartamentos;
    }

    public void setIdDepartamentos(Integer idDepartamentos) {
        this.idDepartamentos = idDepartamentos;
    }

    public String getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(String departamentos) {
        this.departamentos = departamentos;
    }
}
