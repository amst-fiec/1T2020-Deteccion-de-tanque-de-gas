package com.example.oxygen.Objetos;

import java.io.Serializable;

public class Tanque {
    private String contenido, descripcion;
    private int idTanque,porcentajeBateria,volumenActual,volumenInicial;

    public Tanque(String contenido, String descripcion, int idTanque, int porcentajeBateria, int volumenActual, int volumenInicial) {
        this.contenido = contenido;
        this.descripcion = descripcion;
        this.idTanque = idTanque;
        this.porcentajeBateria = porcentajeBateria;
        this.volumenActual = volumenActual;
        this.volumenInicial = volumenInicial;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTanque() {
        return idTanque;
    }

    public void setIdTanque(int idTanque) {
        this.idTanque = idTanque;
    }

    public int getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(int porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
    }

    public int getVolumenActual() {
        return volumenActual;
    }

    public void setVolumenActual(int volumenActual) {
        this.volumenActual = volumenActual;
    }

    public int getVolumenInicial() {
        return volumenInicial;
    }

    public void setVolumenInicial(int volumenInicial) {
        this.volumenInicial = volumenInicial;
    }

    @Override
    public String toString() {
        return "Tanque{" +
                "contenido='" + contenido + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idTanque=" + idTanque +
                ", porcentajeBateria=" + porcentajeBateria +
                ", volumenActual=" + volumenActual +
                ", volumenInicial=" + volumenInicial +
                '}';
    }
}


