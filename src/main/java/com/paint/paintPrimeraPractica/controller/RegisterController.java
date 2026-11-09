package com.paint.paintPrimeraPractica.controller;

import com.paint.paintPrimeraPractica.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class RegisterController {
    @Autowired
   RegisterService registerService;


    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/register")
    public String registerPost(@RequestParam String username, @RequestParam String password, Model model){
        if (registerService.checkRegister(username, password)){
            return "redirect:/";
        }
        model.addAttribute("message", "Usuario existe o datos incorrectos");
        return "register";
    }
}
