package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Canvas;
import com.paint.paintPrimeraPractica.model.Figura;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CanvasRepoImpl implements CanvasRepo{
    private final List<Canvas> canvasList= new ArrayList<>();
    public CanvasRepoImpl(){
        canvasList.add(new Canvas(1, "dibujo", new ArrayList<>(), 1));
        canvasList.add(new Canvas(2, "Paisaje", new ArrayList<>(), 1));
        canvasList.add(new Canvas(3, "Figuras", new ArrayList<>(), 2));
    }
    @Override
    public Canvas buscarCanvas(int id) {
        return  canvasList.stream().filter(i->i.getId()==id).findFirst().orElse(null);
    }

    @Override
    public List<Canvas> buscarTodosCanvas() {
        return new ArrayList<>(canvasList);
    }

    @Override
    public void agregarCanvas(int id, String nombre, List<Figura> figuras, int idUser) {
        Canvas canvas=new Canvas(id,nombre,figuras,idUser);
        canvasList.add(canvas);


    }

    @Override
    public void eliminarCanvas(int id) {
        for (int i = 0; i < canvasList.size(); i++) {
            if (canvasList.get(i).getId()==id){
                canvasList.remove(i);
            }
        }
    }
}
