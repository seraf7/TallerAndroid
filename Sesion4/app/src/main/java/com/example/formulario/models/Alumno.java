package com.example.formulario.models;

import java.io.Serializable;

//Serializable, permite guardar el objeto para transmitirlo entre Activitys
public class Alumno implements Serializable {
    private String nombre;
    private String cuenta;

    public Alumno(String nombre, String cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
}
