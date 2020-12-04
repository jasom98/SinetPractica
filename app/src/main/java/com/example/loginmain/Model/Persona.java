package com.example.loginmain.Model;

import java.io.Serializable;

public class Persona implements Serializable {

    private String ID;
    private String Nombre;
    private String Correo;
    private String Cedula;
    private String Celular;
    private String Lugar;
    private String Actividad;

    public Persona() {
    }

    public Persona(String ID, String nombre, String correo, String cedula, String celular, String lugar, String actividad) {
        this.ID = ID;
        Nombre = nombre;
        Correo = correo;
        Cedula = cedula;
        Celular = celular;
        Lugar = lugar;
        Actividad = actividad;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }
}
