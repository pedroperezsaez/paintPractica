package com.paint.paintPrimeraPractica.model;

public class Figura {
    private String tipo;
    private int id;
    private int x;
    private int y;
    private String color;
    private boolean tieneRelleno;
    int canvasId;



    Figura(String tipo, int id, int x, int y, String color, boolean tieneRelleno,int canvasId){
        this.tipo=tipo;
        this.id=id;
        this.x=x;
        this.y=y;
        this.color=color;
        this.tieneRelleno=tieneRelleno;
        this.canvasId=canvasId;
    }
    public int getCanvasId() {
        return canvasId;
    }

    public void setCanvasId(int canvasId) {
        this.canvasId = canvasId;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isTieneRelleno() {
        return tieneRelleno;
    }

    public void setTieneRelleno(boolean tieneRelleno) {
        this.tieneRelleno = tieneRelleno;
    }
}

