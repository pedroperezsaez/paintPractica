package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Otros.FigurasEnJson;
import com.paint.paintPrimeraPractica.Respository.FiguraRepo;
import com.paint.paintPrimeraPractica.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void guardarFiguras(String json, int canvasId) {
        List<Figura>figurasNuevas=new ArrayList<>();
        int desde = 0;

        while (true) {
            int inicio = json.indexOf("{", desde);
            if (inicio == -1) {
                break;
            }

            int nivel = 0;
            int fin = -1;

            for (int i = inicio; i < json.length(); i++) {
                char letra = json.charAt(i);
                if (letra == '{') {
                    nivel++;
                } else if (letra == '}') {
                    nivel--;
                    if (nivel == 0) {
                        fin = i;
                        break;
                    }
                }
            }

            if (fin == -1) {
                break;
            }

            String objeto = json.substring(inicio + 1, fin);

            String tipo = valor(objeto, "tipo");
            int x = numero(objeto, "x");
            int y = numero(objeto, "y");
            String color = valor(objeto, "color");
            boolean tieneRelleno = Boolean.parseBoolean(valor(objeto, "tieneRelleno"));
            int id = contId++;

            Figura figura;

            if (tipo.equals("Cuadrado")) {
                figura = new Cuadrado(tipo, id, x, y, color, tieneRelleno, canvasId,
                        numero(objeto, "grandaria"));
            } else if (tipo.equals("Circulo")) {
                figura = new Circulo(tipo, id, x, y, color, tieneRelleno, canvasId,
                        numero(objeto, "radios"));
            } else if (tipo.equals("Triangulo")) {
                figura = new Triangulo(tipo, id, x, y, color, tieneRelleno, canvasId,
                        numero(objeto, "x1"), numero(objeto, "y1"),
                        numero(objeto, "x2"), numero(objeto, "y2"),
                        numero(objeto, "x3"), numero(objeto, "y3"));
            } else {
                figura = new Estrella(tipo, id, x, y, color, tieneRelleno, canvasId);
            }

            figurasNuevas.add(figura);

            desde = fin + 1;
        }

        figuraRepo.reemplazarFiguras(canvasId, figurasNuevas);
    }

    String valor(String objeto, String nombre) {
        String buscado = "\"" + nombre + "\":";
        int inicio = objeto.indexOf(buscado);
        if (inicio == -1) {
            return "";
        }

        inicio += buscado.length();
        int fin = objeto.indexOf(",", inicio);
        if (fin == -1) {
            fin = objeto.length();
        }

        return objeto.substring(inicio, fin).replace("\"", "").trim();
    }




    private int numero(String objeto, String nombre) {
        return (int) Double.parseDouble(valor(objeto, nombre));
    }

}
