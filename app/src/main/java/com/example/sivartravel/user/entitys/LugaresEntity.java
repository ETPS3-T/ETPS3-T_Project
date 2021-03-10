package com.example.sivartravel.user.entitys;

/**
 * Travel v1
 * @author Isaias Ortiz
 */
public class LugaresEntity {

    private Integer imagen;
    private String lugar;
    private String departamento;
    private String descripcion;
    private String informacion;
    private String horario;
    private String x;
    private String y;

    public LugaresEntity(Integer imagen, String lugar, String departamento) {
        this.imagen = imagen;
        this.lugar = lugar;
        this.departamento = departamento;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public LugaresEntity(Integer imagen, String lugar, String departamento, String descripcion, String informacion, String horario, String x, String y) {
        this.imagen = imagen;
        this.lugar = lugar;
        this.departamento = departamento;
        this.descripcion = descripcion;
        this.informacion = informacion;
        this.horario = horario;
        this.x=x;
        this.y=y;
    }

    public LugaresEntity() {
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
