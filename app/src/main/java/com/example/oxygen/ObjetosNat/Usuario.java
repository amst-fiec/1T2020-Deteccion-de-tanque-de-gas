package com.example.oxygen.ObjetosNat;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    private String correo;
    private String idUser;
    private String imagen;
    private String nombreUsuario;
    //private ArrayList<Estacion> Estaciones = new ArrayList<Estacion>();
    //private DataSnapshot dataSnapshot;
    //private ArrayList<String> estaciones = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(String correo, String idUser, String imagen, String nombreUsuario) {
        this.correo = correo;
        this.idUser = idUser;
        this.imagen = imagen;
        this.nombreUsuario = nombreUsuario;
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



    @Override
    public boolean equals(Object o) {
       if(o!= null){
           if(o instanceof Usuario){
               Usuario u = (Usuario)o;
               if(this.idUser == u.getIdUser()){
                   return true;
               }
           }
       }
       return false;
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
