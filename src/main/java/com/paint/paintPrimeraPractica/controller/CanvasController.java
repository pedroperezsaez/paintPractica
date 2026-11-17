package com.paint.paintPrimeraPractica.controller;

import com.paint.paintPrimeraPractica.Respository.UserRepo;
import com.paint.paintPrimeraPractica.Service.CanvasService;
import com.paint.paintPrimeraPractica.Service.FigurasService;
import com.paint.paintPrimeraPractica.Service.UserService;
import com.paint.paintPrimeraPractica.model.Canvas;
import com.paint.paintPrimeraPractica.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.List;

@Controller
public class CanvasController {
    @Autowired
    CanvasService canvasService;
    @Autowired
    UserService userService;
    @Autowired
    FigurasService figurasService;
    @GetMapping("/paint")
    public String mostrarPaint(Model model, HttpSession session){
        int idUser=(int) session.getAttribute("idUser");
        model.addAttribute("todosLosCanvasUsuario", canvasService.buscarCanvasDelUsuario(idUser));
        model.addAttribute("todosLosCanvas", canvasService.buscarTodosCanvas());
        model.addAttribute("idUser", idUser);
        model.addAttribute("esEditor",false);
        model.addAttribute("figurasJson", "[]");
        return "paint";
    }

    @GetMapping("/paint/{id}")
    public String enviarCanvas(Model model, @PathVariable int id, HttpSession session) {
    int idUser=(int) session.getAttribute("idUser");
    Canvas canvas=canvasService.buscarCanvas(id);
    boolean esEditor=canvas.getIdUser()== idUser;
    model.addAttribute("canvasSeleccionado", canvas);
    model.addAttribute("todosLosCanvasUsuario",canvasService.buscarCanvasDelUsuario(idUser));
    model.addAttribute("todosLosCanvas",canvasService.buscarTodosCanvas());
        model.addAttribute("nombreCanvas", canvas.getNombre());
        model.addAttribute("figurasJson", figurasService.crearJson(id));
        model.addAttribute("idUser", idUser);
        model.addAttribute("esEditor", esEditor);
    return "paint";
    }

    @PostMapping("/paint/eliminarCanvas/{id}")
    public String eliminarCanvas(@PathVariable int id, HttpSession session ){
        int idUser = (int) session.getAttribute("idUser");
        Canvas canvas = canvasService.buscarCanvas(id);

        if (canvas.getIdUser() != idUser) {
            return "redirect:/paint";
        }

        canvasService.eliminarCanvas(id);
        return "redirect:/paint";
    }

    @PostMapping("/paint/{id}/guardar")
    public String guardarCanvas(@PathVariable int id, @RequestParam String figuras,
                                @RequestParam String nombre, HttpSession session){
        int idUser=(int) session.getAttribute("idUser");
        Canvas canvas =canvasService.buscarCanvas(id);
        if (canvas == null || canvas.getIdUser() != idUser) {
            return "redirect:/paint";
        }

        canvas.setNombre(nombre);
        figurasService.guardarFiguras(figuras, id);

        return "redirect:/paint/"+id;
    }


}
