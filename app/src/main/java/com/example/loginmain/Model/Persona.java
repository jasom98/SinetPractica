package com.example.loginmain.Model;

public class Persona {

    private String uid;
    private String Nombre;
    private String Correos;
    private String Cedula;
    private String Celular;

    public Persona() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreos() {
        return Correos;
    }

    public void setCorreos(String correos) {
        Correos = correos;
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

    @Override
    public String toString() {
        return Nombre;
    }
}
