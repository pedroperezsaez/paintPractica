package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.model.Canvas;
import com.paint.paintPrimeraPractica.model.Figura;

import java.util.List;

public interface CanvasService {
    public Canvas buscarCanvas(int id);
    public void agregarCanvas( String nombre, List<Figura> figuras, int idUser);
    public List<Canvas> buscarTodosCanvas();
    public void eliminarCanvas(int id);
    public List<Canvas> buscarCanvasDelUsuario(int idUser);
}
