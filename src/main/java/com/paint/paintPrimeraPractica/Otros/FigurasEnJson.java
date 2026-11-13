package com.paint.paintPrimeraPractica.Otros;

import com.paint.paintPrimeraPractica.model.*;

public class FigurasEnJson {

    public String circuloJson(Circulo circulo) {

        String jsonFiguras = "{\n" +
                "\"tipo\":\"" + circulo.getTipo() + "\",\n" +
                "\"id\":" + circulo.getId() + ",\n" +
                "\"x\":" + circulo.getX() + ",\n" +
                "\"y\":" + circulo.getY() + ",\n" +
                "\"color\":\"" + circulo.getColor() + "\",\n" +
                "\"tieneRelleno\":" + circulo.isTieneRelleno() + ",\n" +
                "\"canvasId\":" + circulo.getCanvasId() + ",\n" +
                "\"radios\":" + circulo.getRadios() + "\n" +
                "}";

        return jsonFiguras;
    }
    public String trianguloJson(Triangulo triangulo){
       String jsonFiguras = "{\n" +
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
       return jsonFiguras;
    }
    public String cuadradoJson(Cuadrado cuadrado){
      String  jsonFiguras = "{\n" +
                "\"tipo\":\"" + cuadrado.getTipo() + "\",\n" +
                "\"id\":" + cuadrado.getId() + ",\n" +
                "\"x\":" + cuadrado.getX() + ",\n" +
                "\"y\":" + cuadrado.getY() + ",\n" +
                "\"color\":\"" + cuadrado.getColor() + "\",\n" +
                "\"tieneRelleno\":" + cuadrado.isTieneRelleno() + ",\n" +
                "\"canvasId\":" + cuadrado.getCanvasId() + ",\n" +
                "\"grandaria\":" + cuadrado.getGrandaria() + "\n" +
                "}";
      return jsonFiguras;
    }

    public String EstrellaJson(Estrella estrella){
      String  jsonFiguras = "{\n" +
                "\"tipo\":\"" + estrella.getTipo() + "\",\n" +
                "\"id\":" + estrella.getId() + ",\n" +
                "\"x\":" + estrella.getX() + ",\n" +
                "\"y\":" + estrella.getY() + ",\n" +
                "\"color\":\"" + estrella.getColor() + "\",\n" +
                "\"tieneRelleno\":" + estrella.isTieneRelleno() + ",\n" +
                "\"canvasId\":" + estrella.getCanvasId() + "\n" +
                "}";
      return  jsonFiguras;
    }
    public String trazadoJson(Trazado trazado){
        String jsonFiguras = "{\n" +
                "\"tipo\":\"" + trazado.getTipo() + "\",\n" +
                "\"id\":" + trazado.getId() + ",\n" +
                "\"x\":" + trazado.getX() + ",\n" +
                "\"y\":" + trazado.getY() + ",\n" +
                "\"color\":\"" + trazado.getColor() + "\",\n" +
                "\"tieneRelleno\":" + trazado.isTieneRelleno() + ",\n" +
                "\"canvasId\":" + trazado.getCanvasId() + ",\n" +
                "\"posicionPuntos\":[";

        for (int j = 0; j < trazado.getPosicionPuntos().size(); j++) {
            PosicionPunto posicionPunto = trazado.getPosicionPuntos().get(j);
            jsonFiguras += "{\n" +
                    "\"x\":" + posicionPunto.getX() + ",\n" +
                    "\"y\":" + posicionPunto.getY() + "\n" +
                    "}";
            if (j < trazado.getPosicionPuntos().size() - 1) {
                jsonFiguras += ",";}
        }
        jsonFiguras += "]}";
        return jsonFiguras;
    }
}


