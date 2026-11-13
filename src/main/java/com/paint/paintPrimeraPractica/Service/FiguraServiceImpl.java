package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Otros.FigurasEnJson;
import com.paint.paintPrimeraPractica.Respository.FiguraRepo;
import com.paint.paintPrimeraPractica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiguraServiceImpl implements FigurasService {
    @Autowired
    FiguraRepo figuraRepo;
    private int contId = 1;

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
        figuraRepo.agregarCirculo(tipo, id, x, y, color, tieneRelleno, canvasId, radios);
    }

    @Override
    public void agregarTriangulo(String tipo, int id, int x, int y, String color, boolean tieneRelleno,
                                 int canvasId, int x1, int y1, int x2, int y2, int x3, int y3) {
        figuraRepo.agregarTriangulo(tipo, id, x, y, color, tieneRelleno,
                canvasId, x1, y1, x2, y2, x3, y3);
    }

    @Override
    public void agregarTrazo(String tipo, int id, int x, int y, String color,
                             boolean tieneRelleno, int canvasId, List<PosicionPunto> posicionPuntos) {
        figuraRepo.agregarTrazo(tipo, id, x, y, color,
                tieneRelleno, canvasId, posicionPuntos);
    }

    @Override
    public List<Figura> buscarTodasFigurasCanvas(int canvasId) {
        return figuraRepo.buscarTodasFigurasCanvas(canvasId);
    }

    public String crearJson(int canvasId) {
        List<Figura> figurasCanvas = figuraRepo.buscarTodasFigurasCanvas(canvasId);
        FigurasEnJson figurasEnJson=new FigurasEnJson();
        String jsonFiguras = "[";

        for (int i = 0; i < figurasCanvas.size(); i++) {

            Figura figura = figurasCanvas.get(i);

            if (figura.getTipo().equals("Circulo")) {

                Circulo circulo = (Circulo) figura;
                jsonFiguras+= figurasEnJson.circuloJson(circulo);


            } else if (figura.getTipo().equals("Triangulo")) {

                Triangulo triangulo = (Triangulo) figura;

                jsonFiguras += figurasEnJson.trianguloJson(triangulo);

            } else if (figura.getTipo().equals("Cuadrado")) {
                Cuadrado cuadrado = (Cuadrado) figura;
                jsonFiguras += figurasEnJson.cuadradoJson(cuadrado);

            } else if (figura.getTipo().equals("Estrella")) {
                Estrella estrella = (Estrella) figura;
                jsonFiguras += figurasEnJson.EstrellaJson(estrella);

            } else if (figura.getTipo().equals("Trazado")) {
                Trazado trazado = (Trazado) figura;
                jsonFiguras += figurasEnJson.trazadoJson(trazado);

            }

            if (i < figurasCanvas.size() - 1) {
                jsonFiguras += ",";
            }
        }

        jsonFiguras += "]";

        return jsonFiguras;
    }

}
