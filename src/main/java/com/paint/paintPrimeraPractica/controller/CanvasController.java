package com.paint.paintPrimeraPractica.controller;

import com.paint.paintPrimeraPractica.Respository.UserRepo;
import com.paint.paintPrimeraPractica.Service.CanvasService;
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
    @GetMapping("/paint/{id}")
    public String enviarCanvas(Model model, @PathVariable int id, HttpSession session) {
    int idUser=(int) session.getAttribute("idUser");
    Canvas canvas=canvasService.buscarCanvas(id);
    boolean esEditor=canvas.getIdUser()== idUser;
    model.addAttribute("canvasSeleccionado", canvas);
    model.addAttribute("todosLosCanvasUsuario",canvasService.buscarCanvasDelUsuario(idUser));
    model.addAttribute("todosLosCanvas",canvasService.buscarTodosCanvas());
    model.addAttribute("esEditor",esEditor);
    return "paint";
    }
}
