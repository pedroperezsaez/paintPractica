package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.model.Canvas;

import java.util.List;

public interface CanvasService {
    Canvas buscarCanvas(int id);

    int agregarCanvas(String nombre, int idUser, String figurasJson);

    List<Canvas> buscarTodosCanvas();

    void eliminarCanvas(int id);

    List<Canvas> buscarCanvasDelUsuario(int idUser);

    void guardarFiguras(int id, String json);

    String buscarFigurasJson(int id);

    String buscarNombre(int id);

    int buscarIdUser(int id);

    void guardarNombre(int id, String nombre);

    void guardarFechaModificacion(int id);

    String nombreNuevoCanvas();
}
