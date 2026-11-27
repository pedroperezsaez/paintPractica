package com.paint.paintPrimeraPractica.controller;


import com.paint.paintPrimeraPractica.Service.CanvasService;
import com.paint.paintPrimeraPractica.model.Canvas;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CanvasController {
    @Autowired
    CanvasService canvasService;

    @GetMapping("/paint")
    public String mostrarPaint(Model model, HttpSession session) {
        int idUser = (int) session.getAttribute("idUser");
        model.addAttribute("todosLosCanvasUsuario", canvasService.buscarCanvasDelUsuario(idUser));
        model.addAttribute("todosLosCanvas", canvasService.buscarTodosCanvas());
        model.addAttribute("idUser", idUser);
        model.addAttribute("esEditor", false);
        model.addAttribute("figurasJson", "[]");
        return "paint";
    }


    @GetMapping("/paint/{id}")
    public String enviarCanvas(Model model, @PathVariable int id, HttpSession session) {
        int idUser = (int) session.getAttribute("idUser");
        Canvas canvas = canvasService.buscarCanvas(id);
        boolean esEditor = canvasService.buscarIdUser(id) == idUser;
        model.addAttribute("canvasSeleccionado", canvas);
        model.addAttribute("todosLosCanvasUsuario", canvasService.buscarCanvasDelUsuario(idUser));
        model.addAttribute("todosLosCanvas", canvasService.buscarTodosCanvas());
        model.addAttribute("nombreCanvas", canvasService.buscarNombre(id));
        model.addAttribute("idUser", idUser);
        model.addAttribute("esEditor", esEditor);
        model.addAttribute("figurasJson", canvasService.buscarFigurasJson(id));
        return "paint";
    }


    @PostMapping("/paint/eliminarCanvas/{id}")
    public String eliminarCanvas(@PathVariable int id, HttpSession session) {
        int idUser = (int) session.getAttribute("idUser");

        if (canvasService.buscarCanvas(id) == null || canvasService.buscarIdUser(id) != idUser) {
            return "redirect:/paint";
        }
        canvasService.eliminarCanvas(id);
        return "redirect:/paint";
    }

    @PostMapping("/paint/{id}/guardar")
    public String guardarCanvas(@PathVariable int id, @RequestParam String figuras,
                                @RequestParam String nombre, HttpSession session) {
        int idUser = (int) session.getAttribute("idUser");
        if (canvasService.buscarCanvas(id) == null || canvasService.buscarIdUser(id) != idUser) {
            return "redirect:/paint";
        }
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            nombre = "Nuevo Canvas " + id;
        }

        canvasService.guardarNombre(id, nombre);
        canvasService.guardarFiguras(id, figuras);
        canvasService.guardarFechaModificacion(id);


        return "redirect:/paint/" + id;
    }
    @GetMapping("/paint/mis-canvas")
    public String misCanvas(Model model, HttpSession session) {
        int idUser = (int) session.getAttribute("idUser");

        model.addAttribute("todosLosCanvasUsuario",
                canvasService.buscarCanvasDelUsuario(idUser));

        return "mis-canvas";
    }

    @GetMapping("/paint/otros-canvas")
    public String otrosCanvas(Model model) {

        model.addAttribute("todosLosCanvas",
                canvasService.buscarTodosCanvas());

        return "otros-canvas";
    }


    @GetMapping("/paint/nuevo")
    public String nuevoCanvas(HttpSession session) {
        int idUser = (int) session.getAttribute("idUser");
        int idCanvas = canvasService.agregarCanvas("Nuevo canvas", idUser, "");
        return "redirect:/paint/" + idCanvas;
    }


}
