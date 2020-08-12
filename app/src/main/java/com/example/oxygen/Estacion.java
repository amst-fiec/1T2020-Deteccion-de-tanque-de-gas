package com.example.oxygen;

import com.example.oxygen.Tanque;

import java.io.Serializable;

public class Estacion implements Serializable {
    Tanque tanque;
    private String ubicacion;
    private int habitacion;
    private int piso;
    private int id;

    Estacion(Tanque tanque, String ubicacion, int habitacion, int piso, int id){
        this.tanque = tanque;
        this.ubicacion = ubicacion;
        this.habitacion = habitacion;
        this.piso = piso;
        this.id = id;

    }
    public int getHabitacion() {
        return habitacion;
    }

    public int getPiso() {
        return piso;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public int getId() {
        return id;
    }
}
