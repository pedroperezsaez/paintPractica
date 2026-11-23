package com.paint.paintPrimeraPractica.Respository;

import com.paint.paintPrimeraPractica.model.Canvas;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CanvasRepoImpl implements CanvasRepo {
    private final List<Canvas> canvasList = new ArrayList<>();

    public CanvasRepoImpl() {
        canvasList.add(new Canvas(1, "dibujo", 1, "", "2026-11-20 09:00", "2026-11-20 09:00"));
        canvasList.add(new Canvas(2, "Paisaje", 1, "", "2026-11-20 10:00", "2026-11-20 10:00"));
        canvasList.add(new Canvas(3, "Figuras", 2, "", "2026-11-20 11:00", "2026-11-20 11:00"));
    }

    @Override
    public Canvas buscarCanvas(int id) {
        return canvasList.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<Canvas> buscarTodosCanvas() {
        return new ArrayList<>(canvasList);
    }

    @Override
    public void agregarCanvas(int id, String nombre, int idUser, String figurasJson,
                              String fechaCreacion, String fechaModificacion) {
        Canvas canvas = new Canvas(id, nombre, idUser, figurasJson, fechaCreacion, fechaModificacion);
        canvasList.add(canvas);
    }

    @Override
    public void eliminarCanvas(int id) {
        for (int i = 0; i < canvasList.size(); i++) {
            if (canvasList.get(i).getId() == id) {
                canvasList.remove(i);
            }
        }
    }

    @Override
    public void guardarFiguras(int id, String json) {
        buscarCanvas(id).setFigurasJson(json);
    }

    @Override
    public String buscarFigurasJson(int id) {
        Canvas canvas = buscarCanvas(id);

        return canvas.getFigurasJson();
    }

    @Override
    public String buscarNombre(int id) {
        Canvas canvas = buscarCanvas(id);

        return canvas.getNombre();
    }

    @Override
    public int buscarIdUser(int id) {
        Canvas canvas = buscarCanvas(id);
        return canvas.getIdUser();
    }

    @Override
    public void guardarNombre(int id, String nombre) {
        Canvas canvas = buscarCanvas(id);
        canvas.setNombre(nombre);
    }

    @Override
    public void guardarFechaModificacion(int id, String fechaModificacion) {
        Canvas canvas = buscarCanvas(id);
        canvas.setFechaModificacion(fechaModificacion);
    }
}
