package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class FiguraRepoImpl implements FiguraRepo {
    List<Figura> figuras = new ArrayList<>();


    @Override
    public Figura buscarFigura(int id) {
        return figuras.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void eliminarFigura(int id) {
        for (int i = 0; i < figuras.size(); i++) {
            if (figuras.get(i).getId() == id) {
                figuras.remove(i);
                break;
            }
        }

    }

    @Override
    public void agregarCirculo(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, int radios) {
        Circulo circulo = new Circulo(tipo, id, x, y, color, tieneRelleno, canvasId, radios);
        figuras.add(circulo);

    }

    @Override
    public void agregarTriangulo(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, int x1, int y1, int x2, int y2, int x3, int y3) {
        Triangulo triangulo = new Triangulo(tipo, id, x, y, color, tieneRelleno
                , canvasId, x1, y1, x2, y2, x3, y3);
        figuras.add(triangulo);

    }

    @Override
    public void agregarTrazo(String tipo, int id, int x, int y, String color,
                             boolean tieneRelleno, int canvasId, List<PosicionPunto> posicionPuntos) {
        Trazado trazado = new Trazado(tipo, id, x, y, color, tieneRelleno, canvasId, posicionPuntos);
        figuras.add(trazado);

    }

    @Override
    public List<Figura> buscarTodasFigurasCanvas(int canvasId, List<Figura> figurasCanvas) {
        return figuras.stream().filter(i -> i.getCanvasId() == canvasId).toList();
    }
}
