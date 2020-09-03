package com.example.oxygen.ObjetosNat;

public class Estacion {
    private String nombre;
    private String volumenInicial;
    private String volumenMedido;
    private String porcentajeBateria;
    private String idModulo;
    private int idUbicacion;
    private String idUsuario;

    public Estacion(String nombre, String volumenInicial, String volumenMedido, String porcentajeBateria, String idModulo, int idUbicacion, String idUsuario) {
        this.nombre = nombre;
        this.volumenInicial = volumenInicial;
        this.volumenMedido = volumenMedido;
        this.porcentajeBateria = porcentajeBateria;
        this.idModulo = idModulo;
        this.idUbicacion = idUbicacion;
        this.idUsuario = idUsuario;
    }

    public Estacion(){

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVolumenInicial() {
        return volumenInicial;
    }

    public void setVolumenInicial(String volumenInicial) {
        this.volumenInicial = volumenInicial;
    }

    public String getVolumenMedido() {
        return volumenMedido;
    }

    public void setVolumenMedido(String volumenMedido) {
        this.volumenMedido = volumenMedido;
    }

    public String getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(String porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
    }

    public String getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(String idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
