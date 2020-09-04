package com.example.oxygen.ObjetosNat;

public class Ubicacion {
    private String ciudad;
    private int habitacion;
    private int idUbicacion;
    private int piso;

    public Ubicacion() {
    }

    public Ubicacion(String ciudad, int habitacion, int idUbicacion, int piso) {
        this.ciudad = ciudad;
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

    public boolean equals(Object obj){
        if(obj != null){
            if(obj instanceof Ubicacion ){
                Ubicacion ubicacion = (Ubicacion)obj;
                if(this.idUbicacion == ubicacion.getIdUbicacion()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "ciudad='" + ciudad + '\'' +
                ", habitacion=" + habitacion +
                ", idUbicacion=" + idUbicacion +
                ", piso=" + piso +
                '}';
    }
}
