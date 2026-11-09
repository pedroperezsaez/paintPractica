package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Respository.CanvasRepo;
import com.paint.paintPrimeraPractica.model.Canvas;
import com.paint.paintPrimeraPractica.model.Figura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CanvasServiceImpl implements CanvasService{
    @Autowired
    CanvasRepo canvasRepo;
    int contador=1;
    @Override
    public Canvas buscarCanvas(int id) {
        return canvasRepo.buscarCanvas(id);
    }

    @Override
    public void agregarCanvas( String nombre, List<Figura> figuras, int idUser) {


        canvasRepo.agregarCanvas(contador,nombre,figuras,idUser);
        contador++;

    }

    @Override
    public List<Canvas> buscarTodosCanvas() {
        return canvasRepo.buscarTodosCanvas();
    }

    @Override
    public void eliminarCanvas(int id) {
        canvasRepo.eliminarCanvas(id);
    }

    @Override
    public List<Canvas> buscarCanvasDelUsuario(int idUser) {
        return canvasRepo.buscarTodosCanvas().stream().filter(i->i.getIdUser()==idUser).toList();
    }
}
