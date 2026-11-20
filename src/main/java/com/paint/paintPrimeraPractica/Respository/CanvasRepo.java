package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Canvas;

import java.util.List;

public interface CanvasRepo {
    public Canvas buscarCanvas(int id);
    public List<Canvas> buscarTodosCanvas();
    public void agregarCanvas(int id, String nombre, int idUser, String figurasJson,
                              String fechaCreacion, String fechaModificacion);
    public  void  eliminarCanvas(int id);
    public void guardarFiguras(int id, String json);
    public String buscarFigurasJson(int id);
    public String buscarNombre(int id);
    public int buscarIdUser(int id);
    public void guardarNombre(int id, String nombre);
    public void guardarFechaModificacion(int id, String fechaModificacion);




}
