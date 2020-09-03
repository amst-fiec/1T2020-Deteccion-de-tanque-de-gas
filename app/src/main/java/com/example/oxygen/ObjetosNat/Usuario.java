package com.example.oxygen.ObjetosNat;

import java.util.ArrayList;

public class Usuario {
    private String correo;
    private String idUser;
    private String imagen;
    private String nombreUsuario;
    private ArrayList<Estacion> Estaciones = new ArrayList<Estacion>();
    //private DataSnapshot dataSnapshot;


    public Usuario() {
    }

    public Usuario(String correo, String idUser, String imagen, String nombreUsuario) {
        this.correo = correo;
        this.idUser = idUser;
        this.imagen = imagen;
        this.nombreUsuario = nombreUsuario;
        Estaciones = getEstaciones();
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    public ArrayList<Estacion> getEstaciones() {
        return Estaciones;
    }

    public void setEstaciones(ArrayList<Estacion> estaciones) {
        Estaciones = estaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUser.equals(usuario.idUser);
    }

    public void agregarEstacion(Estacion estacion){
        Estaciones.add(estacion);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", idUser='" + idUser + '\'' +
                ", imagen='" + imagen + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                '}';
    }
}
