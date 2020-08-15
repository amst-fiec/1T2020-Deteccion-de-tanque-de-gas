package com.example.oxygen.ObjetosNat;

public class Tanque {
    private String contenido;
    private String volumenIinicial;

    public Tanque(String contenido, String volumenIinicial) {
        this.contenido = contenido;
        this.volumenIinicial = volumenIinicial;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getVolumenIinicial() {
        return volumenIinicial;
    }

    public void setVolumenIinicial(String volumenIinicial) {
        this.volumenIinicial = volumenIinicial;
    }
}
