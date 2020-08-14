package com.example.oxygen.Objetos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.oxygen.Estacion;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

 public  class Usuario implements Serializable {
    private String nombreUsuario, idUsuario,imagen;
    private String correo;
    //private ArrayList<Estacion> estacionesUsuario;
    private DataSnapshot snapEstaciones;

     public Usuario() {
     }

     public Usuario(String nombreUsuario, String idUsuario, String imagen, String correo, DataSnapshot snapEstaciones) {
         this.nombreUsuario = nombreUsuario;
         this.idUsuario = idUsuario;
         this.imagen = imagen;
         this.correo = correo;
         this.snapEstaciones = snapEstaciones;
     }

     public String getNombreUsuario() {
         return nombreUsuario;
     }

     public void setNombreUsuario(String nombreUsuario) {
         this.nombreUsuario = nombreUsuario;
     }

     public String getIdUsuario() {
         return idUsuario;
     }

     public void setIdUsuario(String idUsuario) {
         this.idUsuario = idUsuario;
     }

     public String getImagen() {
         return imagen;
     }

     public void setImagen(String imagen) {
         this.imagen = imagen;
     }

     public String getCorreo() {
         return correo;
     }

     public void setCorreo(String correo) {
         this.correo = correo;
     }

     public DataSnapshot getSnapEstaciones() {
         return snapEstaciones;
     }

     public void setSnapEstaciones(DataSnapshot snapEstaciones) {
         this.snapEstaciones = snapEstaciones;
     }

     public void leerBaseDatos(DatabaseReference db_reference){
        //ArrayList<Estacion> estaciones = new ArrayList<>();
       db_reference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    System.out.println(String.valueOf(snapshot.getValue()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("Hay un error al leer la base de datos");;
            }
        });

    }

    public void obtenerDatosUser(DataSnapshot snap){
        String nameU = String.valueOf(snap.child("nombreUsuario").getValue());
        String correo = String.valueOf(snap.child("correo").getValue());

        System.out.println(nameU);
        System.out.println(correo);
        DataSnapshot snapChildEstaciones = snap.child("Estaciones");
        DatabaseReference refenciaGeneral = snapChildEstaciones.getRef();
        System.out.println(refenciaGeneral);
        DatabaseReference referenceEstaciones = refenciaGeneral.getParent();
        System.out.println(referenceEstaciones);

    }


    public Tanque crearTanque(DataSnapshot datoTanque){
        Tanque tanque;
        String contenido = String.valueOf(datoTanque.child("contenido").getValue());
        String descripcion = String.valueOf(datoTanque.child("descripcion").getValue());
        int idTanque = Integer.valueOf(String.valueOf(datoTanque.child("idTanque").getValue()));
        int porcentajeBateria = Integer.valueOf(String.valueOf(datoTanque.child("porcentajeBateria").getValue()));
        int volumenActual = Integer.valueOf(String.valueOf(datoTanque.child("volumenActual").getValue()));
        int volumenInicial = Integer.valueOf(String.valueOf(datoTanque.child("volumenInicial").getValue()));
        tanque = new Tanque(contenido,descripcion,idTanque,porcentajeBateria,volumenActual,volumenInicial);
        //System.out.println(contenido);

        return tanque;
    }

    public Ubicacion crearUbicacion(DataSnapshot datoUbicacion){
        Ubicacion ubicacion;
        String ciudad = String.valueOf(datoUbicacion.child("ciudad").getValue());
        int habitacion = Integer.valueOf(String.valueOf(datoUbicacion.child("habitacion").getValue()));
        int idUbicacion = Integer.valueOf(String.valueOf(datoUbicacion.child("idUbicacion").getValue()));
        String nombreHospital = String.valueOf(datoUbicacion.child("nombreHospital").getValue());
        int piso = Integer.valueOf(String.valueOf(datoUbicacion.child("piso").getValue()));
        ubicacion = new Ubicacion(ciudad,nombreHospital,habitacion,idUbicacion,piso);

        return ubicacion;

    }

     @Override
     public String toString() {
         return "Usuario{" +
                 "nombreUsuario='" + nombreUsuario + '\'' +
                 ", idUsuario='" + idUsuario + '\'' +
                 ", imagen='" + imagen + '\'' +
                 ", correo='" + correo + '\'' +
                 ", snapEstaciones=" + snapEstaciones +
                 '}';
     }
 }
