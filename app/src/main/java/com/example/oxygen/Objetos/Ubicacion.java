package com.example.oxygen.Objetos;

public class Ubicacion {
    private String ciudad,nombreHospital;
    private int habitacion, idUbicacion,piso;

    public Ubicacion(String ciudad, String nombreHospital, int habitacion, int idUbicacion, int piso) {
        this.ciudad = ciudad;
        this.nombreHospital = nombreHospital;
        this.habitacion = habitacion;
        this.idUbicacion = idUbicacion;
        this.piso = piso;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "ciudad='" + ciudad + '\'' +
                ", nombreHospital='" + nombreHospital + '\'' +
                ", habitacion=" + habitacion +
                ", idUbicacion=" + idUbicacion +
                ", piso=" + piso +
                '}';
    }
}
