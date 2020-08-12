package com.example.oxygen;

import java.io.Serializable;

public class Tanque implements Serializable {
    private String descripcion;
    private int idTanque;
    private double volInicial;
    private double volActual;
    private int porcentajeBateria;


    Tanque(String descripcion, int idTanque, double volInicial, double volActual, int porcentajeBateria){
        this.descripcion = descripcion;
        this.idTanque = idTanque;
        this.volInicial = volInicial;
        this.volActual = volActual;
        this.porcentajeBateria = porcentajeBateria;

    }

    public String getDescripcion() {
        return descripcion;
    }



    public int getIdTanque() {
        return idTanque;
    }

    public double getVolInicial() {
        return volInicial;
    }

    public double getVolActual() {
        return volActual;
    }

    public int getPorcentajeBateria() {
        return porcentajeBateria;
    }


}


