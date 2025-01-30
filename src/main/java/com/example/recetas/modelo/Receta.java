package com.example.recetas.modelo;

public class Receta {
    private String nombre;
    private String ingredientes;
    private String pasos;
    private String tiempo;
    private String dificultad;

    public Receta(String nombre, String ingredientes, String pasos, String tiempo, String dificultad) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.tiempo = tiempo;
        this.dificultad = dificultad;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String pasos) {
        this.pasos = pasos;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public boolean validarDatos() {
        return !nombre.isEmpty() &&
                !ingredientes.isEmpty() &&
                !pasos.isEmpty() &&
                !tiempo.isEmpty() &&
                !dificultad.isEmpty();
    }
}
