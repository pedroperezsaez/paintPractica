package com.paint.paintPrimeraPractica.model;

public class Circulo extends Figura{
    private int radios;


    public int getRadios() {
        return radios;
    }

    public void setRadios(int radios) {
        this.radios = radios;
    }

    public Circulo(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, int radios) {
        super(tipo, id, x, y, color, tieneRelleno, canvasId);
        this.radios=radios;
    }
}
