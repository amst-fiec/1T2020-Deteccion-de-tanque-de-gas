package com.example.oxygen;

import java.util.ArrayList;

public class Usuario {
    private String nombre, imagen, nombreUsuario;
    private String correo;
    private ArrayList<Estacion> estaciones;

    public Usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;

    }

}
