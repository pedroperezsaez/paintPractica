package com.paint.paintPrimeraPractica.Service;

import com.paint.paintPrimeraPractica.Respository.CanvasRepo;
import com.paint.paintPrimeraPractica.model.Canvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CanvasServiceImpl implements CanvasService {
    @Autowired
    CanvasRepo canvasRepo;
    int contador = 4;
    int contadorNombre = 1;

    @Override
    public Canvas buscarCanvas(int id) {
        return canvasRepo.buscarCanvas(id);
    }


    @Override
    public int agregarCanvas(String nombre, int idUser, String figurasJson) {
        int id = contador++;

        if (nombre.equals("Nuevo canvas")) {
            nombre = "Nuevo canvas " + contadorNombre;
            contadorNombre++;
        }

        String fecha = LocalDateTime.now().toString();
        fecha = fecha.substring(0, 16).replace("T", " ");
        canvasRepo.agregarCanvas(id, nombre, idUser, figurasJson, fecha, fecha);
        return id;
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
        return canvasRepo.buscarTodosCanvas().stream().filter(i -> i.getIdUser() == idUser).toList();
    }

    @Override
    public void guardarFiguras(int id, String json) {
        canvasRepo.guardarFiguras(id, json);
    }

    @Override
    public String buscarFigurasJson(int id) {
        return canvasRepo.buscarFigurasJson(id);
    }

    @Override
    public String buscarNombre(int id) {
        return canvasRepo.buscarNombre(id);
    }

    @Override
    public int buscarIdUser(int id) {
        return canvasRepo.buscarIdUser(id);
    }

    @Override
    public void guardarNombre(int id, String nombre) {
        canvasRepo.guardarNombre(id, nombre);
    }


    @Override
    public void guardarFechaModificacion(int id) {
        String fecha = LocalDateTime.now().toString();
        fecha = fecha.substring(0, 16).replace("T", " ");
        canvasRepo.guardarFechaModificacion(id, fecha);
    }

    public String nombreNuevoCanvas() {
        String nombre = "Nuevo canvas " + contadorNombre;
        contadorNombre++;
        return nombre;
    }
}
