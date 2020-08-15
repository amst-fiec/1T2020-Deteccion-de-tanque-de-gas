package com.example.oxygen.ObjetosNat;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Usuario {
    private String correo;
    private String imagen;
    private String nombreUsuario;
    private ArrayList<Estacion> estaciones;
    private DataSnapshot dataSnapshot;
    public Usuario(String correo, String imagen, String nombreUsuario) {
        this.correo = correo;
        this.imagen = imagen;
        this.nombreUsuario = nombreUsuario;
        estaciones = new ArrayList<>();
    }

    public Usuario(String correo, String imagen, String nombreUsuario,DataSnapshot dataSnapshot) {
        this.correo = correo;
        this.imagen = imagen;
        this.nombreUsuario = nombreUsuario;
        this.dataSnapshot = dataSnapshot;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
