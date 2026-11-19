package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Canvas;
import com.paint.paintPrimeraPractica.model.Figura;

import java.util.List;

public interface CanvasRepo {
    public Canvas buscarCanvas(int id);
    public List<Canvas> buscarTodosCanvas();
    public void agregarCanvas(int id, String nombre, List<Figura> figuras, int idUser,String figurasJson);
    public  void  eliminarCanvas(int id);
    public void guardarFiguras(int id, String json);
    public String buscarFigurasJson(int id);
    public String buscarNombre(int id);
    public int buscarIdUser(int id);
    public void guardarNombre(int id, String nombre);
    public void guardarHistorial(int id, int numObjetos);


}
