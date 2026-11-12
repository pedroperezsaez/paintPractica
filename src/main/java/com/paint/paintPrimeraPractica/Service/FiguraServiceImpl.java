package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Respository.FiguraRepo;
import com.paint.paintPrimeraPractica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiguraServiceImpl implements FigurasService {
    @Autowired
    FiguraRepo figuraRepo;
    private int contId=1;
    @Override
    public Figura buscarFigura(int id) {
        return figuraRepo.buscarFigura(id);
    }

    @Override
    public void eliminarFigura(int id) {
        figuraRepo.eliminarFigura(id);
    }

    @Override
    public void agregarCirculo(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, int radios) {
        figuraRepo.agregarCirculo( tipo,id, x, y,  color, tieneRelleno, canvasId, radios);
    }

    @Override
    public void agregarTriangulo(String tipo, int id, int x, int y, String color, boolean tieneRelleno,
                                 int canvasId, int x1, int y1, int x2, int y2, int x3, int y3) {
    figuraRepo.agregarTriangulo( tipo, id, x, y, color, tieneRelleno,
         canvasId,  x1,  y1, x2, y2,  x3, y3);
    }

    @Override
    public void agregarTrazo(String tipo, int id, int x, int y, String color,
                             boolean tieneRelleno, int canvasId, List<PosicionPunto> posicionPuntos) {
        figuraRepo.agregarTrazo( tipo, id, x, y, color,
         tieneRelleno, canvasId,  posicionPuntos);
    }

    @Override
    public List<Figura> buscarTodasFigurasCanvas(int canvasId) {
        return figuraRepo.buscarTodasFigurasCanvas(canvasId);
    }
    public String crearJson(int canvasId){
        List<Figura> figurasCanvas=figuraRepo.buscarTodasFigurasCanvas(canvasId);
        String jsonFiguras="[";
        for (int i = 0; i < figurasCanvas.size(); i++) {

            Figura figura = figurasCanvas.get(i);

            if (figura.getTipo().equals("Circulo")) {

                Circulo circulo = (Circulo) figura;

                jsonFiguras += "{\n" +
                        "\"tipo\":\"" + circulo.getTipo() + "\",\n" +
                        "\"id\":" + circulo.getId() + ",\n" +
                        "\"x\":" + circulo.getX() + ",\n" +
                        "\"y\":" + circulo.getY() + ",\n" +
                        "\"color\":\"" + circulo.getColor() + "\",\n" +
                        "\"tieneRelleno\":" + circulo.isTieneRelleno() + ",\n" +
                        "\"canvasId\":" + circulo.getCanvasId() + ",\n" +
                        "\"radios\":" + circulo.getRadios() + "\n" +
                        "}";
            } else if (figura.getTipo().equals("Triangulo")) {
                Triangulo triangulo = (Triangulo) figura;

                jsonFiguras += "{\n" +
                        "\"tipo\":\"" + triangulo.getTipo() + "\",\n" +
                        "\"id\":" + triangulo.getId() + ",\n" +
                        "\"x\":" + triangulo.getX() + ",\n" +
                        "\"y\":" + triangulo.getY() + ",\n" +
                        "\"color\":\"" + triangulo.getColor() + "\",\n" +
                        "\"tieneRelleno\":" + triangulo.isTieneRelleno() + ",\n" +
                        "\"canvasId\":" + triangulo.getCanvasId() + ",\n" +
                        "\"x1\":" + triangulo.getX1() + ",\n" +
                        "\"y1\":" + triangulo.getY1() + ",\n" +
                        "\"x2\":" + triangulo.getX2() + ",\n" +
                        "\"y2\":" + triangulo.getY2() + ",\n" +
                        "\"x3\":" + triangulo.getX3() + ",\n" +
                        "\"y3\":" + triangulo.getY3() + "\n" +
                        "}";
            } else if (figura.getTipo().equals("Cuadrado")) {
                Cuadrado cuadrado = (Cuadrado) figura;

                jsonFiguras += "{\n" +
                        "\"tipo\":\"" + cuadrado.getTipo() + "\",\n" +
                        "\"id\":" + cuadrado.getId() + ",\n" +
                        "\"x\":" + cuadrado.getX() + ",\n" +
                        "\"y\":" + cuadrado.getY() + ",\n" +
                        "\"color\":\"" + cuadrado.getColor() + "\",\n" +
                        "\"tieneRelleno\":" + cuadrado.isTieneRelleno() + ",\n" +
                        "\"canvasId\":" + cuadrado.getCanvasId() + ",\n" +
                        "\"grandaria\":" + cuadrado.getGrandaria() + "\n" +
                        "}";
            }else if (figura.getTipo().equals("Estrella")) {
                Estrella estrella =(Estrella) figura;
                jsonFiguras += "{\n" +
                        "\"tipo\":\"" + estrella.getTipo() + "\",\n" +
                        "\"id\":" + estrella.getId() + ",\n" +
                        "\"x\":" + estrella.getX() + ",\n" +
                        "\"y\":" + estrella.getY() + ",\n" +
                        "\"color\":\"" + estrella.getColor() + "\",\n" +
                        "\"tieneRelleno\":" + estrella.isTieneRelleno() + ",\n" +
                        "\"canvasId\":" + estrella.getCanvasId() + "\n" +
                        "}";
            } else if (figura.getTipo().equals("Trazado")) {
                Trazado trazado = (Trazado) figura;

                jsonFiguras += "{\n" +
                        "\"tipo\":\"" + trazado.getTipo() + "\",\n" +
                        "\"id\":" + trazado.getId() + ",\n" +
                        "\"x\":" + trazado.getX() + ",\n" +
                        "\"y\":" + trazado.getY() + ",\n" +
                        "\"color\":\"" + trazado.getColor() + "\",\n" +
                        "\"tieneRelleno\":" + trazado.isTieneRelleno() + ",\n" +
                        "\"canvasId\":" + trazado.getCanvasId() + ",\n" +
                        "\"posicionPuntos\":[";
                for (int j = 0; j < trazado.getPosicionPuntos().size(); j++) {
                    PosicionPunto posicionPunto=trazado.getPosicionPuntos().get(j);
                    jsonFiguras += "{\n" +
                            "\"x\":" + posicionPunto.getX() + ",\n" +
                            "\"y\":" + posicionPunto.getY() + "\n" +
                            "}";

                    if (j < trazado.getPosicionPuntos().size() - 1) {
                        jsonFiguras += ",";
                    }

                }
            }
        }
        return jsonFiguras;
    }
}
