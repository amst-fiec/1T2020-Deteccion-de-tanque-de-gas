package com.example.oxygen.ObjetosNat;

public class Ubicacion {
    private String ciudad;
    private int habitacion;
    private String nombreHospital;
    private int piso;

    public Ubicacion(String ciudad, int habitacion, String nombreHospital, int piso) {
        this.ciudad = ciudad;
        this.habitacion = habitacion;
        this.nombreHospital = nombreHospital;
        this.piso = piso;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(int habitacion) {
        this.habitacion = habitacion;
    }

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
