package com.paint.paintPrimeraPractica.model;

import java.util.List;
import java.time.LocalDateTime;
public class Canvas {
    private int id;
    private String nombre;
    private List<Figura> figuras;
    private int idUser;
    private String figurasJson;
    private String fechaCreacion;
    private String fechaModificacion;
    private int numObjetos;
    public Canvas(int id, String nombre, List<Figura> figuras, int idUser, String figurasJson){
        this.id=id;
        this.nombre=nombre;
        this.figuras=figuras;
        this.idUser=idUser;
        this.figurasJson=figurasJson;
        this.fechaCreacion=LocalDateTime.now().toString();
        this.fechaModificacion=LocalDateTime.now().toString();
        this.numObjetos=0;
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

    public List<Figura> getFiguras() {
        return figuras;
    }

    public void setFiguras(List<Figura> figuras) {
        this.figuras = figuras;
    }
}
