package com.paint.paintPrimeraPractica.model;

public class Cuadrado  extends Figura{
    private int grandaria;


    public int getGrandaria() {
        return grandaria;
    }

    public void setGrandaria(int grandaria) {
        this.grandaria = grandaria;
    }

    Cuadrado(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, int grandaria) {
        super(tipo, id, x, y, color, tieneRelleno, canvasId);
        this.grandaria=grandaria;
    }
}
