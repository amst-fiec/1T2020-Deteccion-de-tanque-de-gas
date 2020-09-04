package com.example.oxygen.ObjetosNat;

import java.io.Serializable;
import java.util.Objects;

public class Tanque implements Comparable<Tanque>, Serializable {
    private String idModulo;
    private int idUbicacion;
    private String nombre;
    private String porcentajeBateria;
    private String volumenInicial;
    private String volumenMedido;

    public Tanque() {
    }

    public Tanque(String idModulo, int idUbicacion, String nombre, String porcentajeBateria, String volumenInicial, String volumenMedido) {
        this.idModulo = idModulo;
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.porcentajeBateria = porcentajeBateria;
        this.volumenInicial = volumenInicial;
        this.volumenMedido = volumenMedido;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPorcentajeBateria() {
        return porcentajeBateria;
    }

    public void setPorcentajeBateria(String porcentajeBateria) {
        this.porcentajeBateria = porcentajeBateria;
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

    public boolean equals(Object object){
        if(object != null){
            if(object instanceof  Tanque){
                Tanque t = (Tanque)object;
                if(this.idModulo == t.getIdModulo() && this.volumenMedido == t.getVolumenMedido()){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public int compareTo(Tanque tanque) {
        return this.idModulo.compareTo(tanque.idModulo);
    }
}
