package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Circulo;
import com.paint.paintPrimeraPractica.model.Figura;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CirculoRepoImpl implements CirculoRepo {
    private final List<Figura> figuraList=new ArrayList<>();
    
    @Override
    public Figura buscarCirculo(int id) {
        return  figuraList.stream().filter(i->i.getId()==id).findFirst().orElse(null);
    }


 

    @Override
    public void agregarCirculo(String tipo, int id, int x, int y, String color, boolean tieneRelleno, int canvasId, int radios) {
        Circulo circulo=new Circulo(tipo,id,x,y,color,tieneRelleno,canvasId,radios);
        figuraList.add(circulo);
    }

    @Override
    public List<Figura> buscarFigurasDelcanvas(int idCanvas) {
        return figuraList.stream().filter(i->i.getCanvasId()==idCanvas).toList();
    }

    @Override
    public void eliminarFigura(int id) {
        for (int i = 0; i < figuraList.size(); i++) {
            if (figuraList.get(i).getId()==id){
                figuraList.remove(i);
                break;
            }
        }

    }
}
