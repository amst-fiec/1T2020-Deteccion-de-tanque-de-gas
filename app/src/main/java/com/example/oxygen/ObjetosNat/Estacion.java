package com.example.oxygen.ObjetosNat;

public class Estacion {

    private int idTanque;
    private int idUbicacion;
    private String nombreEstacion;
    private String pesoMedido;
    private int pesoVacio;
    private String porcentajeBateria;


    public Estacion(){

    }
    public Estacion(int idTanque, int idUbicacion, String pesoMedido, int pesoVacio, String porcentajeBateria) {
        this.idTanque = idTanque;
        this.idUbicacion = idUbicacion;
        this.pesoMedido = pesoMedido;
        this.pesoVacio = pesoVacio;
        this.porcentajeBateria = porcentajeBateria;
    }

    public Estacion(int idTanque, int idUbicacion, String nombreEstacion, String pesoMedido, int pesoVacio, String porcentajeBateria) {
        this.idTanque = idTanque;
        this.idUbicacion = idUbicacion;
        this.nombreEstacion = nombreEstacion;
        this.pesoMedido = pesoMedido;
        this.pesoVacio = pesoVacio;
        this.porcentajeBateria = porcentajeBateria;
    }

    public String getNombreEstacion() {
        return nombreEstacion;
    }

    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    public int getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(int idTanque) {
        this.idTanque = idTanque;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getPesoMedido() {
        return pesoMedido;
    }

    public void setPesoMedido(String pesoMedido) {
        this.pesoMedido = pesoMedido;
    }

    public int getPesoVacio() {
        return pesoVacio;
    }

    public void setPesoVacio(int pesoVacio) {
        this.pesoVacio = pesoVacio;
    }

    public String getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(String porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
    }

    @Override
    public String toString() {
        return "Estacion{" +
                "idTanque=" + idTanque +
                ", idUbicacion=" + idUbicacion +
                ", pesoMedido='" + pesoMedido + '\'' +
                ", pesoVacio=" + pesoVacio +
                ", porcentajeBateria='" + porcentajeBateria + '\'' +
                '}';
    }
}
