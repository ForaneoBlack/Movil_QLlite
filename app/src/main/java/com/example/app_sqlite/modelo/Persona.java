package com.example.app_sqlite.modelo;

import android.content.Context;

public class Persona {

    private int id;
    private  String nombre;
    private String telefono;
    private String correo;
    private  int edad;

    public Persona() {
        this.telefono="0979937851";

    }

public void guardar (Context context){

        DBHelper dbHelper = new DBHelper(context);
        String nsql= "INSERT INTO personas (id,nombre, telefono, correo, edad)"+
    "VALUES ("+getId()+", '" + getNombre() + " ', '" + getTelefono() + "',' "+ getCorreo()+ "',"+ getEdad()+")";

        dbHelper.no_query(nsql);
        dbHelper.close();
}

    public Persona(int id, String nombre, String telefono, String correo, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
