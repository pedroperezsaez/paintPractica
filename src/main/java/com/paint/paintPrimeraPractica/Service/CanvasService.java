package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.model.Canvas;

import java.util.List;

public interface CanvasService {
    public Canvas buscarCanvas(int id);
    public int agregarCanvas(String nombre, int idUser, String figurasJson);
    public List<Canvas> buscarTodosCanvas();
    public void eliminarCanvas(int id);
    public List<Canvas> buscarCanvasDelUsuario(int idUser);
    public void guardarFiguras(int id, String json);
    public String buscarFigurasJson(int id);
    public String buscarNombre(int id);
    public int buscarIdUser(int id);
    public void guardarNombre(int id, String nombre);

    public void guardarFechaModificacion(int id);

}
