package com.example.oxygen;

import com.example.oxygen.Objetos.Tanque;
import com.example.oxygen.Objetos.Ubicacion;

import java.io.Serializable;

public class Estacion implements Serializable{
    private Tanque tanque;
    private Ubicacion ubicacion;
    private int idEstacion;

    public Estacion(Tanque tanque, Ubicacion ubicacion, int idEstacion) {
        this.tanque = tanque;
        this.ubicacion = ubicacion;
        this.idEstacion = idEstacion;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public boolean equals(Object object){
        if(object != null){
            if(object instanceof Estacion){
                Estacion e = (Estacion)object;

                if(this.idEstacion == e.idEstacion){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Estacion{" +
                "tanque=" + tanque +
                ", ubicacion=" + ubicacion +
                ", idEstacion=" + idEstacion +
                '}';
    }
}
