package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Canvas;

import java.util.List;

public interface CanvasRepo {
    Canvas buscarCanvas(int id);

    List<Canvas> buscarTodosCanvas();

    void agregarCanvas(int id, String nombre, int idUser, String figurasJson,
                       String fechaCreacion, String fechaModificacion);

    void eliminarCanvas(int id);

    void guardarFiguras(int id, String json);

    String buscarFigurasJson(int id);

    String buscarNombre(int id);

    int buscarIdUser(int id);

    void guardarNombre(int id, String nombre);

    void guardarFechaModificacion(int id, String fechaModificacion);


}
