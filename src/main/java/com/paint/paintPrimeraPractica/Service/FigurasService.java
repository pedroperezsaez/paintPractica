package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.model.Figura;
import com.paint.paintPrimeraPractica.model.PosicionPunto;

import java.util.List;

public interface FigurasService {


    Figura buscarFigura(int id);
    void eliminarFigura(int id);
    void agregarCirculo(String tipo, int id, int x, int y, String color,
                        boolean tieneRelleno, int canvasId, int radios);
    void agregarTriangulo(String tipo, int id, int x, int y, String color,
                          boolean tieneRelleno,int canvasId,int x1,int y1, int x2, int y2, int x3, int y3);
    void agregarTrazo(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId,
                      List<PosicionPunto> posicionPuntos);
    List<Figura> buscarTodasFigurasCanvas(int canvasId, List<Figura> figurasCanvas);
}
