package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Canvas;
import com.paint.paintPrimeraPractica.model.Figura;

import java.util.List;

public interface CanvasRepo {
    public Canvas buscarCanvas(int id);
    public List<Canvas> buscarTodosCanvas();
    public void agregarCanvas(int id, String nombre, List<Figura> figuras, int idUser);
    public  void  eliminarCanvas(int id);

}
