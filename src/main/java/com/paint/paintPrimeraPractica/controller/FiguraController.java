package com.paint.paintPrimeraPractica.controller;

import com.paint.paintPrimeraPractica.Service.FigurasService;
import com.paint.paintPrimeraPractica.model.Figura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FiguraController {
    @Autowired
    FigurasService figurasService;

    @GetMapping(value="/figuras/{id}")
public String enviarJson(@PathVariable int id, Model model){
   List<Figura> figuras= figurasService.buscarTodasFigurasCanvas(id);
    model.addAttribute("figuras",figuras);

 return "/paint";

}
}
