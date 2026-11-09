package com.paint.paintPrimeraPractica.model;

import java.util.List;

public class Canvas {
    private int id;
    private String nombre;
    private List<Figura> figuras;
    private int idUser;

    public Canvas(int id, String nombre, List<Figura> figuras, int idUser){
        this.id=id;
        this.nombre=nombre;
        this.figuras=figuras;
        this.idUser=idUser;
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

    public List<Figura> getFiguras() {
        return figuras;
    }

    public void setFiguras(List<Figura> figuras) {
        this.figuras = figuras;
    }
}
