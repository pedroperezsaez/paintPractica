package com.paint.paintPrimeraPractica.model;

public class Canvas {
    private int id;
    private String nombre;
    private int idUser;
    private String figurasJson;
    private String fechaCreacion;
    private String fechaModificacion;
    private int numObjetos;

    public Canvas(int id, String nombre, int idUser, String figurasJson, String fechaCreacion, String fechaModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.idUser = idUser;
        this.figurasJson = figurasJson;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.numObjetos = 0;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getNumObjetos() {
        return numObjetos;
    }

    public void setNumObjetos(int numObjetos) {
        this.numObjetos = numObjetos;
    }

    public String getFigurasJson() {
        return figurasJson;
    }

    public void setFigurasJson(String figurasJson) {
        this.figurasJson = figurasJson;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
}



