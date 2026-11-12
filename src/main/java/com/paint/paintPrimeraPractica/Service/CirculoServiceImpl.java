package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Respository.CirculoRepo;
import com.paint.paintPrimeraPractica.model.Figura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CirculoServiceImpl implements CirculoService {
    @Autowired
    CirculoRepo circuloRepo;
    int idCont=1;

    @Override
    public Figura buscarCirculo(int id) {
        return circuloRepo.buscarCirculo(id);
    }

    @Override
    public void agregarCirculo(String tipo, int x, int y, String color, boolean tieneRelleno, int canvasId, int radios) {
         circuloRepo.agregarCirculo(tipo,idCont,x,y,color,tieneRelleno,canvasId,radios);
    }

    @Override
    public List<Figura> buscarFigurasDelcanvas(int idCanvas) {
        return circuloRepo.buscarFigurasDelcanvas(idCanvas);
    }

    @Override
    public void eliminarFigura(int id) {
        circuloRepo.eliminarFigura(id);
    }

}
