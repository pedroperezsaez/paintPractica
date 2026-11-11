package com.paint.paintPrimeraPractica.model;

import java.util.List;

public class Trazado extends Figura{
    private List<PosicionPunto> posicionPuntos;




    Trazado(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, List<PosicionPunto> posicionPuntos) {
        super(tipo, id, x, y, color, tieneRelleno, canvasId);
        this.posicionPuntos=posicionPuntos;
    }
    public List<PosicionPunto> getPosicionPuntos() {
        return posicionPuntos;
    }

    public void setPosicionPuntos(List<PosicionPunto> posicionPuntos) {
        this.posicionPuntos = posicionPuntos;
    }
}
