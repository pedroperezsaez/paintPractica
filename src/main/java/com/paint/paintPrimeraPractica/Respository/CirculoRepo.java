package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Circulo;
import com.paint.paintPrimeraPractica.model.Figura;

import java.util.List;

public interface CirculoRepo {
    public Figura buscarCirculo(int id);
    public void agregarCirculo(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, int radios);
    List<Figura>buscarFigurasDelcanvas(int idCanvas);
    public void eliminarFigura(int id);
}
