package com.example.loginmain.Model;

import java.io.Serializable;

public class Persona implements Serializable {

    private String ID;
    private String Nombre;
    private String Correo;
    private String Cedula;
    private String Celular;

    public Persona() {
    }

    public Persona(String ID, String nombre, String correo, String cedula, String celular) {
        this.ID = ID;
        this.Nombre = nombre;
        this.Correo = correo;
        this.Cedula = cedula;
        this.Celular = celular;
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
}
