package com.paint.paintPrimeraPractica.controller;

import com.paint.paintPrimeraPractica.Service.CanvasService;
import com.paint.paintPrimeraPractica.Service.LoginService;
import com.paint.paintPrimeraPractica.Service.UserService;
import com.paint.paintPrimeraPractica.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    CanvasService canvasService;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username,
                            @RequestParam String password, Model model, HttpSession session) {

        if (loginService.checkLogin(username, password)) {
            User user=userService.buscarUser(username);
            session.setAttribute("username", username);
            session.setAttribute("idUser", user.getId());

            int idCanvas = canvasService.agregarCanvas("Nuevo canvas",  user.getId(), "");
            return "redirect:/paint/" + idCanvas;
        }

        model.addAttribute("message", "Usuario o pass incorrectos");
        return "login";
    }

   /* @GetMapping("/paint")
    public String privada(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        int idUser=(int) session.getAttribute("idUser");

        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("todosLosCanvasUsuario", canvasService.buscarCanvasDelUsuario(idUser));
        model.addAttribute("todosLosCanvas", canvasService.buscarTodosCanvas());
        model.addAttribute("idUser", idUser);

        return "paint";
    }

    */

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}